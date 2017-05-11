package com.finance.winport.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.finance.winport.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/5/11.
 */

public class NoticeDelDialog extends Dialog {
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.tv_message2_two)
    TextView tvMessage2Two;
    private Context mContext;
    private NoticeDialog.OnPreClickListner mOnOkPreClickListner;

    public NoticeDelDialog(@NonNull Context context) {
        super(context, R.style.noticeDialog);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_notice_del, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        setCanceledOnTouchOutside(true);

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 280, mContext.getResources().getDisplayMetrics());
        lp.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 162, mContext.getResources().getDisplayMetrics());
        window.setAttributes(lp);
    }

    @OnClick({R.id.tv_ok, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_ok:
                if (mOnOkPreClickListner != null) {
                    mOnOkPreClickListner.onClick();
                }
                dismiss();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }

    public void setMessage(String msg) {
        tvMessage.setText(msg);
    }

    public void setPositiveBtn(String msg) {
        tvOk.setText(msg);
    }

    public void setOkClickListener(NoticeDialog.OnPreClickListner onPreClickListner) {
        this.mOnOkPreClickListner = onPreClickListner;
    }

    public interface OnPreClickListner {
        void onClick();
    }
}
