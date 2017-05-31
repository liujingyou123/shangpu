package com.finance.winport.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

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
        web.setTitle(mTitle);
        web.setDescription(mDes);
        UMImage umImage = null;
        if (!TextUtils.isEmpty(mImageUrl)) {
            umImage = new UMImage(mContext, mImageUrl);
        } else {
            umImage = new UMImage(mContext, R.drawable.default_image_logo);
        }
        web.setThumb(umImage);
        new ShareAction((Activity) mContext).withMedia(web)
                .setPlatform(platform)
                .setCallback(umShareListener)
                .share();
    }

    @OnClick({R.id.tv_weixin, R.id.tv_pengyou, R.id.tv_weibo, R.id.tv_qq})
    public void onViewClicked(View view) {
        ToastUtil.show(mContext, "正在分享...");
        switch (view.getId()) {
            case R.id.tv_weixin:
                showShare(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.tv_pengyou:
                showShare(SHARE_MEDIA.WEIXIN_CIRCLE);
                break;
            case R.id.tv_weibo:
                showShare(SHARE_MEDIA.SINA);
                break;
            case R.id.tv_qq:
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
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            XLog.e("onError");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            XLog.e("onCancel");
        }
    };
}
