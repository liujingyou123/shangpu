package com.finance.winport.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.home.adapter.SupportTagAdapter;
import com.finance.winport.home.model.ShopDetail;
import com.finance.winport.log.XLog;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.home.ItemView;
import com.finance.winport.view.home.NearShopView;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/5/8.
 * 更多
 */

public class ShopMoreActivity extends BaseActivity {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rl_title_root)
    RelativeLayout rlTitleRoot;
    @BindView(R.id.tv_jingying)
    ItemView tvJingying;
    @BindView(R.id.tv_dianpu)
    ItemView tvDianpu;
    @BindView(R.id.tv_tese)
    ItemView tvTese;
    @BindView(R.id.tv_jingyingyetai)
    ItemView tvJingyingyetai;
    @BindView(R.id.tv_kehu)
    ItemView tvKehu;
    @BindView(R.id.tv_phone)
    ItemView tvPhone;
    @BindView(R.id.tv_mianji)
    ItemView tvMianji;
    @BindView(R.id.tv_shiyongmianji)
    ItemView tvShiyongmianji;
    @BindView(R.id.tv_louceng)
    ItemView tvLouceng;
    @BindView(R.id.tv_miankuan)
    ItemView tvMiankuan;
    @BindView(R.id.tv_jinshen)
    ItemView tvJinshen;
    @BindView(R.id.tv_cenggao)
    ItemView tvCenggao;
    @BindView(R.id.tv_dianfei)
    ItemView tvDianfei;
    @BindView(R.id.tv_shuifei)
    ItemView tvShuifei;
    @BindView(R.id.tv_ranqifei)
    ItemView tvRanqifei;
    @BindView(R.id.tv_wuyefei)
    ItemView tvWuyefei;
    @BindView(R.id.tv_yuezujin)
    ItemView tvYuezujin;
    @BindView(R.id.tv_yajin)
    ItemView tvYajin;
    @BindView(R.id.tv_zhuanrangfei)
    ItemView tvZhuanrangfei;
    @BindView(R.id.tv_shengyuhetong)
    ItemView tvShengyuhetong;
    @BindView(R.id.gv_support_more)
    GridView gvSupportMore;
    @BindView(R.id.ll_linpuxinxi)
    LinearLayout llLinpuxinxi;
    @BindView(R.id.tv_peittao_notice)
    TextView tvPeittaoNotice;
    @BindView(R.id.tv_yingyunfeiyong)
    TextView tvYingyufeiyong;
    @BindView(R.id.ll_yingyunfeiyong)
    LinearLayout llYingYunFeiyong;
    @BindView(R.id.customer_info)
    TextView customerInfo;
    @BindView(R.id.line)
    View line;
    private ShopDetail mShopDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopmore);
        ButterKnife.bind(this);
        initData();
        showView();
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mShopDetail = (ShopDetail) intent.getSerializableExtra("shop");
        }
    }

    private void showView() {
        if (mShopDetail == null) {
            return;
        }
        ShopDetail.DataBean data = mShopDetail.getData();
        if (data == null) {
            return;
        }
        tvFocusHouse.setText(data.getAddress());
        tvJingying.setLableTwo(data.getOperateStatusName());
        tvDianpu.setLableTwo(data.getShopName());

        if (data.getFeatureList() != null && data.getFeatureList().size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < data.getFeatureList().size(); i++) {
                sb.append(data.getFeatureList().get(i).getName()).append("、");
            }
            tvTese.setLableTwo(sb.toString().substring(0, sb.length() - 1));
        }

        if (data.getIndustryList() != null && data.getIndustryList().size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < data.getIndustryList().size(); i++) {
                sb.append(data.getIndustryList().get(i).getName()).append("、");
            }
            tvJingyingyetai.setLableTwo(sb.toString().substring(0, sb.length() - 1));
        }

        if (0 == data.getIsShow()) {
            tvKehu.setVisibility(View.GONE);
            tvPhone.setVisibility(View.GONE);
            customerInfo.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        } else {
            tvKehu.setVisibility(View.VISIBLE);
            tvPhone.setVisibility(View.VISIBLE);
            customerInfo.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
        }
        tvKehu.setLableTwo(data.getContacter());
        tvPhone.setLableTwo(data.getContactTel());
        tvMianji.setLableTwo(UnitUtil.formatMNum(data.getArea()) + "㎡");
        tvLouceng.setLableTwo(data.getFloor() + "/" + data.getTotalFloor() + "层");
        tvMiankuan.setLableTwo(UnitUtil.formatDNum(data.getWidth()) + "m");
        tvJinshen.setLableTwo(UnitUtil.formatDNum(data.getDepth()) + "m");
        tvCenggao.setLableTwo(UnitUtil.formatDNum(data.getHeight()) + "m");

        if (data.getSupportList() != null && data.getSupportList().size() > 0) {
            tvPeittaoNotice.setVisibility(View.VISIBLE);
            gvSupportMore.setVisibility(View.VISIBLE);
            SupportTagAdapter supportTagAdapter = new SupportTagAdapter(this, data.getSupportList());
            gvSupportMore.setAdapter(supportTagAdapter);
        } else {
            tvPeittaoNotice.setVisibility(View.GONE);
            gvSupportMore.setVisibility(View.GONE);
        }
        if (data.getElectricRate() == 0 && data.getWaterRate() == 0 && data.getGasRate() == 0 && data.getPropertyRate() == 0) {
            tvYingyufeiyong.setVisibility(View.GONE);
            llYingYunFeiyong.setVisibility(View.GONE);
        } else {
            tvYingyufeiyong.setVisibility(View.VISIBLE);
            llYingYunFeiyong.setVisibility(View.VISIBLE);
            tvDianfei.setLableTwo(UnitUtil.formatDNum(data.getElectricRate()) + "元/度");
            tvShuifei.setLableTwo(UnitUtil.formatDNum(data.getWaterRate()) + "元/吨");
            tvRanqifei.setLableTwo(UnitUtil.formatDNum(data.getGasRate()) + "元/立方");
            tvWuyefei.setLableTwo(UnitUtil.formatDNum(data.getPropertyRate()) + "元/㎡/月");
        }

        tvYuezujin.setLableTwo(UnitUtil.limitNum(data.getRent(), 99999) + "/月");
        tvYajin.setLableTwo(UnitUtil.limitNum(data.getDeposit(), 99999));
        if (data.getIsFace() == 1) {
            tvZhuanrangfei.setLableTwo("面议");
        } else {
            tvZhuanrangfei.setLableTwo(UnitUtil.limitNum(data.getTransferFee(), 0));
        }
        tvShengyuhetong.setLableTwo(data.getCompactResidue() + "个月");

        if (data.getNearInfoList() != null && data.getNearInfoList().size() > 0) {
            llLinpuxinxi.setVisibility(View.VISIBLE);
            Collections.sort(data.getNearInfoList());

            for (int i = 0; i < data.getNearInfoList().size(); i++) {
                ShopDetail.DataBean.NearInfoListBean listBean = data.getNearInfoList().get(i);
                XLog.e("nearSeat = " + listBean.getNearSeat());

                NearShopView nearShopView = new NearShopView(this);
                nearShopView.setShopName(listBean.getName());
                nearShopView.setShopYeTai(listBean.getIndustryName());

                if (listBean.getNearSeat() == 0) {
                    nearShopView.setLocation("左1");
                } else if (listBean.getNearSeat() == 1) {
                    nearShopView.setLocation("左2");
                } else if (listBean.getNearSeat() == 2) {
                    nearShopView.setLocation("右1");
                } else if (listBean.getNearSeat() == 3) {
                    nearShopView.setLocation("右2");
                }

                if (llLinpuxinxi.getChildCount() > 1) {
                    View view = new View(this);
                    LinearLayout.LayoutParams lpSpace = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, UnitUtil.dip2px(this, 10));
                    llLinpuxinxi.addView(view, lpSpace);
                }
                llLinpuxinxi.addView(nearShopView);
            }
        }

    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }
}
