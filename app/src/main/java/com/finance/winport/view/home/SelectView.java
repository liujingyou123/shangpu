package com.finance.winport.view.home;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/5/3.
 * 筛选
 */

public class SelectView extends LinearLayout {

    @BindView(R.id.rl_location_s)
    RelativeLayout rlLocationS;
    @BindView(R.id.rl_sort_s)
    RelativeLayout rlSortS;
    @BindView(R.id.rl_c_s)
    RelativeLayout rlCS;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_sort)
    TextView tvSort;
    @BindView(R.id.tv_c_s)
    TextView tvCS;

    public SelectView(Context context) {
        super(context);
        init(context);
    }

    public SelectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_select, this);
        ButterKnife.bind(this, this);
        this.setGravity(Gravity.CENTER);
        this.setBackgroundColor(Color.parseColor("#ffffff"));
        this.setOrientation(LinearLayout.VERTICAL);
    }

    @OnClick({R.id.rl_location_s, R.id.rl_sort_s, R.id.rl_c_s})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_location_s:
                break;
            case R.id.rl_sort_s:
                break;
            case R.id.rl_c_s:
                break;
        }
    }

    public void onLocationClick() {
        tvLocation.setSelected(true);

        if ("排序".equals(tvSort.getText().toString())) {
            tvSort.setSelected(false);
        }
    }

    public void onLocationArrowDown() {
        Drawable drawable = this.getResources().getDrawable(R.mipmap.screening_icon_arrow);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()-1);//必须设置图片大小，否则不显示
        tvLocation.setCompoundDrawables(null, null, drawable, null);
    }

    public void onLocationArrowUp() {
        Drawable drawable = this.getResources().getDrawable(R.mipmap.screening_icon_arrow_selected);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()-1);//必须设置图片大小，否则不显示
        tvLocation.setCompoundDrawables(null, null, drawable, null);
    }

    public void onSortArrowDown() {
        Drawable drawable = this.getResources().getDrawable(R.mipmap.screening_icon_arrow);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()-1);//必须设置图片大小，否则不显示
        tvSort.setCompoundDrawables(null, null, drawable, null);
    }

    public void onSortArrowUp() {
        Drawable drawable = this.getResources().getDrawable(R.mipmap.screening_icon_arrow_selected);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()-1);//必须设置图片大小，否则不显示
        tvSort.setCompoundDrawables(null, null, drawable, null);
    }

    public void onSortClick() {
        if ("位置".equals(tvLocation.getText().toString())) {
            tvLocation.setSelected(false);
        }
        tvSort.setSelected(true);
//        tvCS.setSelected(false);
    }

    public void onCsClick() {
//        tvLocation.setSelected(false);
//        tvSort.setSelected(false);
        tvCS.setSelected(true);
    }

    public void onNoneClick() {
        tvLocation.setSelected(false);
        tvSort.setSelected(false);
        tvCS.setSelected(false);
    }

    public void onLocationUnClick() {
        tvLocation.setSelected(false);
    }

    public void onSortUnClick() {
        tvSort.setSelected(false);
    }

    public void onCsUnClick() {
        tvCS.setSelected(false);
    }

    public void setQuYuText(String text) {
        tvLocation.setText(text);
    }

    public void setSortText(String text) {
        tvSort.setText(text);
    }

    public void setOnLocationClickListener(OnClickListener onLocationClickListener) {
        rlLocationS.setOnClickListener(onLocationClickListener);
    }

    public void setOnSortClickListener(OnClickListener onSortClickListener) {
        rlSortS.setOnClickListener(onSortClickListener);
    }

    public void setOnCsClickListener(OnClickListener onCsClickListener) {
        rlCS.setOnClickListener(onCsClickListener);
    }
}
