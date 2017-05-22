package com.finance.winport.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

/**
 * Created by liuworkmac on 16/9/6.
 * checkBox 单选组件
 */
public class CheckGroup extends LinearLayout {

    private CheckBox checkedBox;

    public CheckGroup(Context context) {
        super(context);
        init();
    }

    public CheckGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CheckGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
//        setOrientation(LinearLayout.VERTICAL);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        getCheckedBox(this);
    }

    private void getCheckedBox(ViewGroup view) {
        int childCount = view.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View viewChild = view.getChildAt(i);
            if (viewChild != null && viewChild instanceof CheckBox) {
                ((CheckBox) viewChild).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            if (checkedBox != null && checkedBox != buttonView) {
                                checkedBox.setChecked(false);
                            }
                            checkedBox = null;
                            if (mOnCheckedBoxListener != null) {
                                mOnCheckedBoxListener.onCheckedBox((CheckBox) buttonView);
                            }
                            checkedBox = (CheckBox) buttonView;
                        } else {
                            if (checkedBox != null && checkedBox == buttonView) {
                                checkedBox = null;
                            }
                            if (mOnCheckedBoxListener != null) {
                                mOnCheckedBoxListener.onCheckedBox((CheckBox) buttonView);
                            }
                        }

                    }
                });
            } else if (viewChild instanceof ViewGroup) {
                getCheckedBox((ViewGroup) viewChild);
            }
        }
    }

    public void clearAllCheckedBox() {
        setclearAllCheckedBox(this);
        checkedBox = null;
    }

    /**
     * 设置所有CheckBox false
     */
    public void setclearAllCheckedBox(ViewGroup view) {
        int childCount = view.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View viewChild = view.getChildAt(i);
            if (viewChild != null && viewChild instanceof CheckBox) {
                ((CheckBox) viewChild).setChecked(false);
            } else if (viewChild instanceof ViewGroup) {
                setclearAllCheckedBox((ViewGroup) viewChild);
            }
        }
    }

    public CheckBox getCheckedBox() {
        return checkedBox;
    }

    private onCheckedBoxListener mOnCheckedBoxListener;

    public void setOnCheckedBoxListener(onCheckedBoxListener onCheckedBoxListener) {
        this.mOnCheckedBoxListener = onCheckedBoxListener;
    }

    public interface onCheckedBoxListener{
        void onCheckedBox(CheckBox checkBox);
    }
}
