package com.finance.winport.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.finance.winport.R;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xzw on 16/11/18.
 */

public class HeaderTextView extends RelativeLayout {
    TextView header;
    TextView content;
    View divider;
    private boolean editable;

    public HeaderTextView(Context context) {
        this(context, null);
    }

    public HeaderTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (getBackground() == null) {
            setBackgroundColor(Color.WHITE);
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HeaderTextView);
        editable = a.getBoolean(R.styleable.HeaderTextView_editable, false);
        init(context);
        String headerText = a.getString(R.styleable.HeaderTextView_header_text);
        int headerTextColor = a.getColor(R.styleable.HeaderTextView_header_text_color, Color.BLACK);
        int headerTextSize = a.getDimensionPixelSize(R.styleable.HeaderTextView_header_text_size, 15);
        int headerWidth = a.getDimensionPixelSize(R.styleable.HeaderTextView_header_width, LayoutParams.WRAP_CONTENT);
        int headerHeight = a.getDimensionPixelSize(R.styleable.HeaderTextView_header_height, LayoutParams.WRAP_CONTENT);
        if (headerText != null) {
            header.setText(headerText);
        }
        header.setTextColor(headerTextColor);
        if (headerTextSize > 0) {
            header.setTextSize(TypedValue.COMPLEX_UNIT_PX, headerTextSize);
        }
        if (headerWidth > 0) {
            header.setWidth(headerWidth);
        }
        if (headerHeight > 0) {
            header.setHeight(headerHeight);
        }

        String text = a.getString(R.styleable.HeaderTextView_text);
        String textHint = a.getString(R.styleable.HeaderTextView_text_hint);
        int textHintColor = a.getColor(R.styleable.HeaderTextView_text_hint_color, Color.BLACK);
        int textColor = a.getColor(R.styleable.HeaderTextView_text_color, Color.BLACK);
        int textSize = a.getDimensionPixelSize(R.styleable.HeaderTextView_text_size, 15);
        int width = a.getLayoutDimension(R.styleable.HeaderTextView_content_width, "content_width");
        int height = a.getLayoutDimension(R.styleable.HeaderTextView_content_height, "content_height");
        int contentPaddingRight = a.getDimensionPixelSize(R.styleable.HeaderTextView_content_padding_right, 0);
        int contentPaddingLeft = a.getDimensionPixelSize(R.styleable.HeaderTextView_content_padding_left, 0);
        Drawable contentBackground = a.getDrawable(R.styleable.HeaderTextView_content_background);
        Drawable drawableRight = a.getDrawable(R.styleable.HeaderTextView_htv_drawable_right);
        Drawable dividerColor = a.getDrawable(R.styleable.HeaderTextView_htv_divider_color);
        int dividerHeight = a.getDimensionPixelSize(R.styleable.HeaderTextView_htv_divider_height, 1);
        boolean dividerStretch = a.getBoolean(R.styleable.HeaderTextView_htv_divider_stretch, false);
        boolean dividerVisible = a.getBoolean(R.styleable.HeaderTextView_htv_divider_visible, false);
        boolean singleLine = a.getBoolean(R.styleable.HeaderTextView_singleLine,false);
        a.recycle();
        if (dividerVisible) {
            initDivider(context);
            divider.setBackgroundDrawable(dividerColor);
            divider.setVisibility(dividerVisible ? VISIBLE : GONE);
            LayoutParams rl = (LayoutParams) divider.getLayoutParams();
            rl.height = dividerHeight;
            if (dividerStretch) {
                rl.addRule(ALIGN_PARENT_LEFT);
            } else {
                rl.addRule(ALIGN_LEFT, content.getId());
            }
            divider.requestLayout();
        }
        if (contentBackground != null) {
            content.setBackgroundDrawable(contentBackground);
        }
        if (drawableRight != null) {
            content.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableRight, null);
        }
        if (text != null) {
            content.setText(text);
        }
        if (textHint != null) {
            content.setHint(textHint);
        }
        content.setHintTextColor(textHintColor);
        content.setTextColor(textColor);
        if (textSize > 0) {
            content.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }
        if (width > 0) {
            content.setWidth(width);
        } else {
            if (width == ViewGroup.LayoutParams.MATCH_PARENT) {
                ViewGroup.LayoutParams lp = content.getLayoutParams();
                lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
                content.requestLayout();
            } else if (width == ViewGroup.LayoutParams.WRAP_CONTENT) {
                ViewGroup.LayoutParams lp = content.getLayoutParams();
                lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                content.requestLayout();
            }
        }
        if (height > 0) {
            content.setHeight(height);
        } else {
            if (height == ViewGroup.LayoutParams.MATCH_PARENT) {
                ViewGroup.LayoutParams lp = content.getLayoutParams();
                lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
                content.requestLayout();
            } else if (height == ViewGroup.LayoutParams.WRAP_CONTENT) {
                ViewGroup.LayoutParams lp = content.getLayoutParams();
                lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                content.requestLayout();
            }
        }
        content.setPadding(contentPaddingLeft, content.getPaddingTop(), contentPaddingRight, content.getPaddingBottom());
        if (getGravity() == Gravity.CENTER_VERTICAL) {
            LayoutParams rlh = (LayoutParams) header.getLayoutParams();
            rlh.addRule(CENTER_VERTICAL);
            header.requestLayout();
            LayoutParams rlc = (LayoutParams) content.getLayoutParams();
            rlc.addRule(CENTER_VERTICAL);
            content.requestLayout();
        }
        if (singleLine){
            content.setSingleLine();
        }
    }

    public void setText(CharSequence content) {
        this.content.setText(content);
    }

    public void setHint(CharSequence content) {
        this.content.setHint(content);
    }

    public void setHeader(CharSequence header) {
        this.header.setText(header);
    }

    public String getText() {
        return content.getText().toString();
    }

    public TextView getView() {
        return content;
    }

    public void setSelection(int index) {
        if (editable) {
            ((EditText) content).setSelection(index);
        }
    }

    public void setInputType(int type) {
        content.setInputType(type);
    }

    public void setFilters(InputFilter[] filters) {
        content.setFilters(filters);
    }

    public void addTextChangedListener(TextWatcher textWatcher) {
        content.addTextChangedListener(textWatcher);
    }

    public void removeTextChangedListener(TextWatcher textWatcher) {
        content.removeTextChangedListener(textWatcher);
    }

    public void setOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        content.setOnFocusChangeListener(onFocusChangeListener);
    }

    public void setDrawableRightVisible(boolean visible) {
        Drawable[] drawables = content.getCompoundDrawables();
        Drawable dr = drawables[2];
        if (dr != null) {
            Rect bounds = dr.getBounds();
            int offset = (content.getHeight() + dr.getIntrinsicHeight());
            dr.setBounds(bounds.left, visible ? bounds.top : -offset, bounds.right, visible ? bounds.bottom : -offset);
            content.invalidate();
        }
    }

    private void init(Context context) {
        //init header
        header = new TextView(context);
        header.setGravity(Gravity.CENTER_VERTICAL);
        header.setSingleLine(true);
        header.setId(generateId());
        LayoutParams rlh = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rlh.addRule(ALIGN_PARENT_LEFT);
//        rlh.addRule(CENTER_VERTICAL);
        header.setLayoutParams(rlh);

        //init content
        if (!editable) {
            content = new TextView(context);
        } else {
            content = new EditText(context);
            content.setPadding(getPaddingLeft(), (getPaddingTop() + getPaddingBottom()) / 2, getPaddingRight(), (getPaddingTop() + getPaddingBottom()) / 2);
        }
        content.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
//        content.setSingleLine(true);
        content.setId(generateId());
        LayoutParams rlc = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rlc.addRule(RIGHT_OF, header.getId());
        content.setLayoutParams(rlc);
        addView(header);
        addView(content);
        //init divider
//        initDivider(context);
    }

    //init divider
    private void initDivider(Context context) {
        divider = new View(context);
        divider.setId(generateId());
        LayoutParams rld = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rld.addRule(ALIGN_PARENT_BOTTOM);
        rld.addRule(ALIGN_LEFT, content.getId());
        divider.setLayoutParams(rld);
        addView(divider);
    }


    private int generateId() {
        if (Build.VERSION.SDK_INT >= 17) {
            return generateViewId();
        } else {
            final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
            for (; ; ) {
                final int result = sNextGeneratedId.get();
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
                if (sNextGeneratedId.compareAndSet(result, newValue)) {
                    return result;
                }
            }
        }
    }

}
