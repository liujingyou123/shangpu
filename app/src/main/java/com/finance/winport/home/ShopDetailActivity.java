package com.finance.winport.home;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.PositionScrollView;
import com.finance.winport.view.ScrollTabView;

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
    @BindView(R.id.stv)
    ScrollTabView stv;
    @BindView(R.id.title_view)
    View titleView;
    @BindView(R.id.rl_select)
    View seletView;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;

    private boolean isTouched = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        svAll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isTouched = true;
                return false;
            }
        });
        svAll.setOnScrollChangedListener(new PositionScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                updateView(t);

                if (!isTouched) {
                    return;
                }
                String text = "init";
                if (isScrolled(llNear)) {
                    text = "周边";
                    stv.setSelectPosition(0);
                } else if (isScrolled(llLinpu)) {
                    text = "邻铺";
                    stv.setSelectPosition(1);
                } else if (isScrolled(llMenmianshuju)) {
                    text = "门面";
                    stv.setSelectPosition(2);
                } else if (isScrolled(llJingyingfeiyong)) {
                    text = "费用";
                    stv.setSelectPosition(3);
                } else if (isScrolled(llPeitao)) {
                    text = "配套";
                    stv.setSelectPosition(4);
                }

                Log.e("cover is = ", text);

            }
        });

        stv.setOnSelectViewListener(new ScrollTabView.OnSelectViewListener() {
            @Override
            public void onSelectViewPosition(int position) {
                isTouched = false;
                int space = titleView.getHeight() + seletView.getHeight();
                if (position == 0) {
                    svAll.smoothScrollTo(0, llNear.getTop() - space + 1);
                } else if (position == 1) {
                    svAll.smoothScrollTo(0, llLinpu.getTop() - space + 1);

                } else if (position == 2) {
                    svAll.smoothScrollTo(0, llMenmianshuju.getTop() - space + 1);

                } else if (position == 3) {
                    svAll.smoothScrollTo(0, llJingyingfeiyong.getTop() - space + 1);

                } else if (position == 4) {
                    svAll.smoothScrollTo(0, llPeitao.getTop() - space + 1);

                }
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

    private void updateView(float scrollY) {

        int top = llNear.getTop();

        int titleView = UnitUtil.dip2px(context, 97);
        Log.e("updateView = top", top + "");
        Log.e("updateView = scrollY", scrollY + "");
        Log.e("updateView = titleView", titleView + "");

        if (scrollY >= 0) {
            float deltaY = top - titleView - scrollY;
            if (deltaY >= 0) {
                float fraction = deltaY / (top - titleView);
                float alpha = (255 * (1 - fraction));

                Log.e("updateView = fraction", fraction + "");
                Log.e("updateView = alpha", alpha + "");

                llTop.setAlpha(1 - fraction);
            } else {
                llTop.setAlpha(1);
            }
        } else {
            llTop.setAlpha(1);
        }
    }
}