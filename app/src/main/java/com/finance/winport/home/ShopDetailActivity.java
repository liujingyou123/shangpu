package com.finance.winport.home;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.view.PositionScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuworkmac on 17/5/4.
 * 商铺详情
 */

public class ShopDetailActivity extends BaseActivity {

    @BindView(R.id.sv_all)
    PositionScrollView svAll;
    @BindView(R.id.ll_near)
    LinearLayout llNear;
    @BindView(R.id.ll_linpu)
    LinearLayout llLinpu;
    @BindView(R.id.ll_menmianshuju)
    LinearLayout llMenmianshuju;
    @BindView(R.id.ll_jingyingfeiyong)
    LinearLayout llJingyingfeiyong;
    @BindView(R.id.ll_peitao)
    LinearLayout llPeitao;
    @BindView(R.id.view_deliver_space)
    View viewDeliverSpace;
    @BindView(R.id.tv_zhoubian)
    TextView tvZhoubian;
    @BindView(R.id.tv_linpu)
    TextView tvLinpu;
    @BindView(R.id.tv_mienmian)
    TextView tvMienmian;
    @BindView(R.id.tv_yingyufeiyong)
    TextView tvYingyufeiyong;
    @BindView(R.id.tv_peitaosheshi)
    TextView tvPeitaosheshi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        svAll.setOnScrollChangedListener(new PositionScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                String text = "init";
                if (isScrolled(llNear)) {
                    text = "周边";
                    setSelectView(tvZhoubian);
                } else if (isScrolled(llLinpu)) {
                    text = "邻铺";
                    setSelectView(tvLinpu);

                } else if (isScrolled(llMenmianshuju)) {
                    text = "门面";
                    setSelectView(tvMienmian);

                } else if (isScrolled(llJingyingfeiyong)) {
                    text = "费用";
                    setSelectView(tvYingyufeiyong);
                } else if (isScrolled(llPeitao)) {
                    text = "配套";
                    setSelectView(tvPeitaosheshi);
                }

                Log.e("cover is = ", text);

            }
        });
    }

    /**
     * 判断是否滑动到view
     *
     * @param view
     * @return
     */
    private boolean isScrolled(View view) {
        Rect coverrect = new Rect();
        viewDeliverSpace.getGlobalVisibleRect(coverrect);
        Rect viewOr = new Rect();
        view.getGlobalVisibleRect(viewOr);
        return coverrect.intersect(viewOr);
    }

    /**
     * 设施选中到text
     */
    private void setSelectView(View view) {
        tvZhoubian.setSelected(false);
        tvLinpu.setSelected(false);
        tvMienmian.setSelected(false);
        tvYingyufeiyong.setSelected(false);
        tvPeitaosheshi.setSelected(false);

        view.setSelected(true);
    }
}