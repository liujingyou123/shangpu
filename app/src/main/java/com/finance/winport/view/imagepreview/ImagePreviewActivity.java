package com.finance.winport.view.imagepreview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by liuworkmac on 16/11/1.
 */
public class ImagePreviewActivity extends Activity {

    ViewPager idViewpager;
    TextView tvNum;
    private List<String> mData = new ArrayList<>();
    private ImagePreViewAdapter mAdapter;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagepreview);
        StatusBarUtil.setColor(this, Color.BLACK,0);
        initView();
        getIntentData();
        initAdapter();
    }

    private void initView() {
        idViewpager = (ViewPager) findViewById(R.id.id_viewpager);
        tvNum = (TextView) findViewById(R.id.tv_num);
    }

    private void initAdapter() {
        if (mAdapter == null) {
            mAdapter = new ImagePreViewAdapter(this, mData);
            idViewpager.setAdapter(mAdapter);
            idViewpager.setCurrentItem(index);
        } else {
            mAdapter.notifyDataSetChanged();
        }

        if (mData != null && mData.size() > 0) {
            tvNum.setText((index + 1)+"/"+mData.size());
        }

        idViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvNum.setText((position+1)+"/"+mData.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getIntentData() {
        Intent intent = getIntent();
        mData = intent.getStringArrayListExtra("pics");
        index = intent.getIntExtra("index", 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mData != null) {
            mData.clear();
        }
        mData = null;
        mAdapter = null;

        if (idViewpager != null) {
            idViewpager.removeAllViews();
        }
        idViewpager = null;
    }
}
