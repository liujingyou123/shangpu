package com.finance.winport.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;

import butterknife.BindView;

/**
 * Created by xzw on 2017/8/3.
 */

public class TagItemView extends RelativeLayout {

    @BindView(R.id.item)
    TextView item;
    @BindView(R.id.delete)
    ImageView delete;

    public TagItemView(Context context) {
        this(context, null);
    }

    public TagItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setClipChildren(false);
        LayoutInflater.from(getContext()).inflate(R.layout.tag_item_layout, this, true);
    }

    public void setItem(String name) {
        item.setText(name);
    }

    public void setOnDeleteClickListener(OnClickListener onDeleteClickListener) {
        delete.setOnClickListener(onDeleteClickListener);
    }

}
