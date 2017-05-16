package com.finance.winport.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.finance.winport.R;
import com.finance.winport.log.XLog;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/5/8.
 * 分享
 */

public class ShareDialog extends Dialog {
    private Context mContext;

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

    private void showShare(SHARE_MEDIA platform) {
        new ShareAction((Activity) mContext).setPlatform(platform)
                .withText("hello")
                .setCallback(umShareListener)
                .share();
    }

    @OnClick({R.id.tv_weixin, R.id.tv_pengyou, R.id.tv_weibo, R.id.tv_qq})
    public void onViewClicked(View view) {
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
