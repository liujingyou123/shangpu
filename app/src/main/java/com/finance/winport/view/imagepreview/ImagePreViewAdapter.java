package com.finance.winport.view.imagepreview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.finance.winport.dialog.NoticeDialog;
import com.finance.winport.image.Batman;
import com.finance.winport.image.BatmanCallBack;
import com.finance.winport.util.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by liuworkmac on 16/11/1.
 */
public class ImagePreViewAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mData = new ArrayList<>();

    public ImagePreViewAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ZoomImageView imageView = new ZoomImageView(mContext.getApplicationContext());
        Batman.getInstance().fromNetWithFitCenter(mData.get(position), imageView);
        container.addView(imageView);

        imageView.setOnLongPressListener(new ZoomImageView.OnLongPressListener() {
            private int index = position;

            @Override
            public void onLongPress() {
                NoticeDialog dialog = new NoticeDialog(mContext);
                dialog.setOkClickListener(new NoticeDialog.OnPreClickListner() {
                    @Override
                    public void onClick() {
                        savePic(mData.get(index));
                    }
                });
                dialog.show();
            }
        });

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof View) {
            container.removeView((View) object);
        }
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private void savePic(String url) {

        Observable.just(url).map(new Func1<String, Bitmap>() {
            @Override
            public Bitmap call(String s) {
                return Batman.getInstance().getBitMap(mContext, s);
            }
        }).map(new Func1<Bitmap, Boolean>() {
            @Override
            public Boolean call(Bitmap bitmap) {
                return saveImageToGallery(mContext, bitmap);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean) {
                    ToastUtil.show(mContext, "图片已经保存到相册");
                }

            }
        });
    }

    public boolean saveImageToGallery(Context context, Bitmap bmp) {
        boolean ret = true;
        // 首先保存图片
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();//注意小米手机必须这样获得public绝对路径
        String fileName = "winport";
        File appDir = new File(file, fileName);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        String fileStr = System.currentTimeMillis() + ".jpg";
        File currentFile = new File(appDir, fileStr);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentFile);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (Exception e) {
            ret = false;
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                ret = false;
                e.printStackTrace();
            }
        }

        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(currentFile.getPath()))));

        return ret;

    }

}
