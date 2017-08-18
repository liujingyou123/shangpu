package com.finance.winport.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by xzw on 16/11/24.
 */

public class DrawableTopLeftTextView extends android.support.v7.widget.AppCompatTextView {
    public DrawableTopLeftTextView(Context context) {
        super(context);
    }

    public DrawableTopLeftTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableTopLeftTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        if (drawables != null) {
            Drawable drawableLeft = drawables[0];
            if (drawableLeft != null) {
                Paint.FontMetricsInt fontMetricsInt = getPaint().getFontMetricsInt();
                Rect bounds = new Rect();
                getPaint().getTextBounds((String) getText(), 0, length(), bounds);
//                int textVerticalSpace = (getHeight() - (fontMetricsInt.descent - fontMetricsInt.ascent)) / 2;
                int textVerticalSpace = Math.round(bounds.top - fontMetricsInt.top);
                int offset = (getHeight() - drawableLeft.getIntrinsicHeight()) / 2 - textVerticalSpace - getPaddingTop() / 2;
                Rect drawableBounds = drawableLeft.getBounds();
                drawableLeft.setBounds(0, -offset, drawableLeft.getIntrinsicWidth(), drawableLeft.getIntrinsicHeight() - offset);
            }
        }
        super.onDraw(canvas);
    }

    public void setDrawable(int id) {
        Drawable d = null;
        if (id > 0) {
            try {
                d = getResources().getDrawable(id);
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
        }
        if (d != null) {
            final SpannableString ss = new SpannableString("img  " + getText().toString());
            d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
            ss.setSpan(span, 0, "img".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            setText(ss);
        } else {
            setText(getText());
        }

    }
}
