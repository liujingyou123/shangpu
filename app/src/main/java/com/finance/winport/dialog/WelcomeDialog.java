package com.finance.winport.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.finance.winport.R;
import com.finance.winport.mine.ShopFocusActivity;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/5/24.
 */

public class WelcomeDialog extends Dialog {
    private Context mContext;

    public WelcomeDialog(@NonNull Context context) {
        super(context, R.style.noticeDialog);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_welcome, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        setCanceledOnTouchOutside(true);

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, mContext.getResources().getDisplayMetrics());
        lp.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250, mContext.getResources().getDisplayMetrics());
        window.setAttributes(lp);
    }

    @OnClick({R.id.tv_cancel, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                MobclickAgent.onEvent(mContext, "shoplist_shopfollow_no");
                dismiss();
                break;
            case R.id.tv_ok:
                MobclickAgent.onEvent(mContext, "shoplist_shopfollow_go");
                Intent intent = new Intent(mContext, ShopFocusActivity.class);
                mContext.startActivity(intent);
                dismiss();
                break;
        }
    }
}
