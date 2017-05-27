package com.finance.winport.aliyunoss;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.finance.winport.home.api.HomeServices;
import com.finance.winport.home.model.AliTokenResponse;
import com.finance.winport.image.BatmanUtil;
import com.finance.winport.log.XLog;
import com.finance.winport.net.Ironman;
import com.finance.winport.util.ToolsUtil;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import retrofit2.Response;

/**
 * Created by liuworkmac on 16/9/21.
 * 阿里云存储
 */
public class AliOss {
    private OSS oss;
    //用户端目录
    public static final String DIR_CUSTOMER = "customer/headimage/";
    public static final String DIR_SECONDHOUSE_INNER = "house/inner/";
    public static final String DIR_SECONDHOUSE_TYPE = "house/type/";
    public static final String DIR_SECONDHOUSE_COMMUNITY = "house/community/";
    public static final String DIR_SIGN_DEAL = "entrustprotocol/"; //创建协议
    public static final String DIR_SHOP_TOPIC = "shop/customer/"; //旺铺发帖
    // 运行sample前需要配置以下字段为有效的值
    private static final String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    private static final String accessKeyId = "LTAI3Xqadr8ORvUr";
    private static final String accessKeySecret = "4PXmUDGcWJd7lZjv1dw8s3heFq0tA2";

    private static final String bucket = "wp-oss-file";

    private static AliOss INSTANCE = new AliOss();

    private AliOss() {
    }

    public static AliOss getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        XLog.e("init ");
//        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKeyId, accessKeySecret);
        OSSCredentialProvider credentialProvider1 = new OSSFederationCredentialProvider() {
            @Override
            public OSSFederationToken getFederationToken() {
                XLog.e("getFederationToken ");
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("osType", "android");
                try {
                    Response<AliTokenResponse> response = Ironman.getInstance().createService(HomeServices.class).getAliToken(hashMap).execute();
                    AliTokenResponse response1 = response.body();
                    AliTokenResponse.DataBean dataBean = response1.getData();
                    if (dataBean != null) {
//                        String time = ToolsUtil.dateToGMTStr(ToolsUtil.stringToDate(dataBean.getExpiration()));
//                        XLog.e("time = " +time);
//                        String time = "2017-05-26T17:35:31Z";
                        return new OSSFederationToken(dataBean.getAccessKeyId(), dataBean.getAccessKeySecret(), dataBean.getSecurityToken(), dataBean.getExpiration());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(1); // 失败后最大重试次数，默认2次
        OSSLog.enableLog();
        oss = new OSSClient(context.getApplicationContext(), endpoint, credentialProvider1, conf);


    }

    // 从本地文件上传，采用阻塞的同步接口
    public String putObjectFromLocalFile(String uploadFilePath) {

        if (TextUtils.isEmpty(uploadFilePath)) {
            return null;
        }

        String uploadObject = null;
        if (!TextUtils.isEmpty(uploadFilePath)) {
            uploadObject = uploadFilePath.substring(uploadFilePath.lastIndexOf("/") + 1);
        }
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucket, uploadObject, uploadFilePath);

        try {
            PutObjectResult putResult = oss.putObject(put);

            Log.d("PutObject", "UploadSuccess");

            Log.d("ETag", putResult.getETag());
            Log.d("RequestId", putResult.getRequestId());

            return oss.presignPublicObjectURL(bucket, uploadObject);
        } catch (ClientException e) {
            // 本地异常如网络异常等
            e.printStackTrace();
        } catch (ServiceException e) {
            // 服务异常
            Log.e("RequestId", e.getRequestId());
            Log.e("ErrorCode", e.getErrorCode());
            Log.e("HostId", e.getHostId());
            Log.e("RawMessage", e.getRawMessage());
        }
        return null;
    }

    // 从本地文件上传，使用非阻塞的异步接口
    public void asyncPutObjectFromLocalFile(String uploadFilePath) {

        if (TextUtils.isEmpty(uploadFilePath)) {
            return;
        }

        String uploadObject = null;
        if (!TextUtils.isEmpty(uploadFilePath)) {
            uploadObject = uploadFilePath.substring(uploadFilePath.lastIndexOf("/"));
        }

        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucket, uploadObject, uploadFilePath);

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");

                Log.d("ETag", result.getETag());
                Log.d("RequestId", result.getRequestId());
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }

    // 直接上传二进制数据，使用阻塞的同步接口
    public String putObjectFromByteArray(String uploadFilePath) {
        // 构造测试的上传数据

        if (TextUtils.isEmpty(uploadFilePath)) {
            return null;
        }

        String uploadObject = null;

        uploadObject = "house/IMG_" + System.currentTimeMillis() + ".jpg";

        byte[] uploadData = BatmanUtil.bitmapToBytes(uploadFilePath);
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucket, uploadObject, uploadData);

        try {
            PutObjectResult putResult = oss.putObject(put);

            Log.d("PutObject", "UploadSuccess");

            Log.d("ETag", putResult.getETag());
            Log.d("RequestId", putResult.getRequestId());
            String url = oss.presignPublicObjectURL(bucket, uploadObject);
            Log.d("Request url", url);


            return url;
        } catch (ClientException e) {
            // 本地异常如网络异常等
            e.printStackTrace();
        } catch (ServiceException e) {
            // 服务异常
            Log.e("RequestId", e.getRequestId());
            Log.e("ErrorCode", e.getErrorCode());
            Log.e("HostId", e.getHostId());
            Log.e("RawMessage", e.getRawMessage());
        }
        return null;
    }

    /**
     * 直接上传二进制数据，使用阻塞的同步接口
     *
     * @param virtualDir     文件对应的虚拟目录
     * @param uploadFilePath 本地文件路径
     */
    public String putObjectFromByteArray(String virtualDir, String uploadFilePath) {
        // 构造测试的上传数据

        if (TextUtils.isEmpty(uploadFilePath)) {
            return null;
        }

        String uploadObject = null;

        uploadObject = virtualDir + Calendar.getInstance().get(Calendar.YEAR) + "/" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "/" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/" + "IMG_" + System.currentTimeMillis() + ".jpg";

        byte[] uploadData = BatmanUtil.bitmapToBytes(uploadFilePath);
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucket, uploadObject, uploadData);

        try {
            PutObjectResult putResult = oss.putObject(put);

            Log.d("PutObject", "UploadSuccess");

            Log.d("ETag", putResult.getETag());
            Log.d("RequestId", putResult.getRequestId());
            String url = oss.presignPublicObjectURL(bucket, uploadObject);
            Log.d("Request url", url);


            return url;
        } catch (ClientException e) {
            // 本地异常如网络异常等
            e.printStackTrace();
        } catch (ServiceException e) {
            // 服务异常
            Log.e("RequestId", e.getRequestId());
            Log.e("ErrorCode", e.getErrorCode());
            Log.e("HostId", e.getHostId());
            Log.e("RawMessage", e.getRawMessage());
        }
        return null;
    }

}
