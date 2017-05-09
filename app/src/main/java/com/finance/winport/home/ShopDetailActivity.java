package com.finance.winport.home;

import android.content.Intent;
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
import com.finance.winport.dialog.ShareDialog;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.PositionScrollView;
import com.finance.winport.view.ScrollTabView;
import com.finance.winport.view.imagepreview.ImagePreviewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private ShareDialog shareDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        llTop.setAlpha(0);
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

    @OnClick({R.id.imv_focus_house_back, R.id.imv_back, R.id.tv_share, R.id.tv_shop_more, R.id.view_banner})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                break;
            case R.id.imv_back:
                finish();
                break;
            case R.id.tv_share:
                if (shareDialog == null) {
                    shareDialog = new ShareDialog(this);
                }
                shareDialog.show();
                break;
            case R.id.tv_shop_more:
                Intent intent = new Intent(ShopDetailActivity.this, ShopMoreActivity.class);
                startActivity(intent);
                break;
            case R.id.view_banner:
                ArrayList<String> list = new ArrayList<String>();
                list.add("http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
                list.add("http://pic.58pic.com/58pic/13/85/85/73T58PIC9aj_1024.jpg");
                list.add("http://pic41.nipic.com/20140518/18521768_133448822000_2.jpg");
                list.add("http://img02.tooopen.com/images/20140127/sy_54827852166.jpg");

                Intent intents = new Intent(context, ImagePreviewActivity.class);
                intents.putExtra("pics", list);
                intents.putExtra("index", 0);
                context.startActivity(intents);
                break;

        }
    }

}