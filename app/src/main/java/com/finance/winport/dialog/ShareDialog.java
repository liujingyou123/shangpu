package com.finance.winport.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.finance.winport.R;
import com.finance.winport.home.ShopDetailActivity;
import com.finance.winport.log.XLog;
import com.finance.winport.util.ToastUtil;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.SocialRouter;
import com.umeng.socialize.handler.SinaSsoHandler;
import com.umeng.socialize.handler.UMSSOHandler;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.lang.reflect.Field;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/5/8.
 * 分享
 */

public class ShareDialog extends Dialog {
    private Context mContext;
    private String mUrl;
    private String mTitle;
    private String mDes;
    private String mImageUrl;

    public ShareDialog(@NonNull Context context) {
        super(context, R.style.customDialog);
        init(context);

    }

    private void init(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_shared_view, null);
        setContentView(view);
        ButterKnife.bind(this, view);

        setCanceledOnTouchOutside(true);

        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 175, mContext.getResources().getDisplayMetrics());
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.dialogWheel);
    }


    public void setUrl(String url) {
        this.mUrl = url;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setDes(String des) {
        this.mDes = des;
    }

    public void setImage(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    private void showShare(SHARE_MEDIA platform) {
        UMWeb web = new UMWeb(mUrl);
        web.setTitle("老板，这里有一个旺铺招租啦！");
        web.setDescription(mDes);
        UMImage umImage = null;
        if (!TextUtils.isEmpty(mImageUrl)) {
            umImage = new UMImage(mContext, mImageUrl);
        } else {
            umImage = new UMImage(mContext, R.drawable.default_image_logo);
        }
        web.setThumb(umImage);
//        new ShareAction((Activity) mContext).withMedia(web)
//                .setPlatform(platform)
//                .setCallback(umShareListener)
//                .share();

        try {
            UMShareAPI umShareAPI = UMShareAPI.get(getContext());
            Field socialRouterField = umShareAPI.getClass().getDeclaredField("router");
            socialRouterField.setAccessible(true);
            Object socialRouterObj = socialRouterField.get(umShareAPI);

            Field platformHandlersHandlerField = SocialRouter.class.getDeclaredField("platformHandlers");
            platformHandlersHandlerField.setAccessible(true);
            Object platformHandlersHandlerObj = platformHandlersHandlerField.get(socialRouterObj);
            Map<SHARE_MEDIA, UMSSOHandler> platformHandlers = (Map<SHARE_MEDIA, UMSSOHandler>) platformHandlersHandlerObj;
            platformHandlers.put(SHARE_MEDIA.SINA, new SinaSsoHandler() {
                @Override
                public boolean share(ShareContent content, UMShareListener listener) {
                    try {
                        return super.share(content, listener);
                    } catch (Exception e) {
                        e.printStackTrace();

                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.show(mContext, "分享失败");
                            }
                        });
                        return false;
                    }
                }
            });

            new ShareAction((Activity) mContext).withMedia(web)
                    .setPlatform(platform)
                    .setCallback(umShareListener)
                    .share();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.tv_weixin, R.id.tv_pengyou, R.id.tv_weibo, R.id.tv_qq})
    public void onViewClicked(View view) {
        ToastUtil.show(mContext, "正在分享...");
        switch (view.getId()) {
            case R.id.tv_weixin:
                MobclickAgent.onEvent(mContext, "shop_share_wechat");
                showShare(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.tv_pengyou:
                MobclickAgent.onEvent(mContext, "shop_share_friendcircle");
                showShare(SHARE_MEDIA.WEIXIN_CIRCLE);
                break;
            case R.id.tv_weibo:
                MobclickAgent.onEvent(mContext, "shop_share_weibo");
                showShare(SHARE_MEDIA.SINA);
                break;
            case R.id.tv_qq:
                MobclickAgent.onEvent(mContext, "shop_share_qq");
                showShare(SHARE_MEDIA.QQ);
                break;
        }
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            XLog.e("onStart");
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            XLog.e("onResult");
            ToastUtil.show(mContext, "分享成功");
            if (share_media == SHARE_MEDIA.QQ) {
                MobclickAgent.onEvent(mContext, "shop_share_qq_success");
            } else if (share_media == SHARE_MEDIA.WEIXIN) {
                MobclickAgent.onEvent(mContext, "shop_share_wechat_success");
            } else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
                MobclickAgent.onEvent(mContext, "shop_share_friendcircle");
            } else if (share_media == SHARE_MEDIA.SINA) {
                MobclickAgent.onEvent(mContext, "shop_share_weibo_success");
            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            XLog.e("onError");
            ToastUtil.show(mContext, throwable != null ? throwable.getMessage() : "分享失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            XLog.e("onCancel");
        }
    };
}
