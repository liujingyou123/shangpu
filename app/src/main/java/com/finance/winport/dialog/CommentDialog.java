package com.finance.winport.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.view.dialog.BottomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by css_double on 17/5/11.
 */

public class CommentDialog extends BottomDialog {
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.et)
    EditText et;

    private Handler mhandler;

    public CommentDialog(@NonNull final Context context) {
        super(context);
        setContentView(R.layout.dialog_work_comment);
        ButterKnife.bind(this);

        mhandler = new Handler(Looper.myLooper());

        btnOk.setAlpha(0.5f);

        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                mhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((BaseActivity) context).showSoftInput(et);
                    }
                }, 10);
            }
        });

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    btnOk.setAlpha(0.5f);
                } else {
                    btnOk.setAlpha(1.0f);
                }
            }
        });
    }

    @OnClick({R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                et.setText("");
                dismiss();
                break;
        }
    }

    public String getContent() {
        return et.getText().toString();
    }

    public void setOkDoneListener(View.OnClickListener onClickListener) {
        btnOk.setOnClickListener(onClickListener);
    }

    public interface OnDoneClickListener {
        void onDoneClick();
    }
}
