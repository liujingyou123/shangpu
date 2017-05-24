package com.finance.winport.map;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.home.adapter.TagAdapter;
import com.finance.winport.image.Batman;
import com.finance.winport.map.model.MapShopDetailResponse;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.dialog.BottomDialog;
import com.finance.winport.view.tagview.TagCloudLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by css_double on 17/5/4.
 */

public class ShopDetailDialog extends BottomDialog {


    @BindView(R.id.imv_cover)
    ImageView imvCover;
    @BindView(R.id.tv_scan_num)
    TextView tvScanNum;
    @BindView(R.id.tv_contact_num)
    TextView tvContactNum;
    @BindView(R.id.rl_cover)
    RelativeLayout rlCover;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_shopname)
    TextView tvShopname;
    @BindView(R.id.tv_aver_money)
    TextView tvAverMoney;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.tv_change_money)
    TextView tvChangeMoney;
    @BindView(R.id.tv_update_time)
    TextView tvUpdateTime;
    @BindView(R.id.line_tv)
    View lineTv;
    @BindView(R.id.tg_view)
    TagCloudLayout tgView;
    @BindView(R.id.ll_tag)
    LinearLayout llTag;
    @BindView(R.id.rl_data)
    RelativeLayout rlData;

    public ShopDetailDialog(@NonNull Context context, MapShopDetailResponse.DataBean ret) {
        super(context);
        setContentView(R.layout.shop_list_item);
        ButterKnife.bind(this);

        tvShopname.setText(ret.getAddress() + ret.getRentTypeName());
        tvShopname.setText(ret.getAddress() + ret.getRentTypeName());
        tvAddress.setText(ret.getDistrictName() + " " + ret.getBlockName());
        tvArea.setText(ret.getArea() + "㎡");
        tvAverMoney.setText(ret.getRent() + "元／月");
        tvDistance.setText(UnitUtil.mTokm(ret.getDistance()));
        tvChangeMoney.setText("转让费" + UnitUtil.formatNum(ret.getTransferFee()) + "万元");
        tvUpdateTime.setText(ret.getUpdateTime());
        tvScanNum.setText(ret.getVisitCount() + "");
        tvContactNum.setText(ret.getContactCount() + "");

        Batman.getInstance().fromNet(ret.getCoverImg(), imvCover);

        if (ret.getFeatureList() != null && ret.getFeatureList().size() > 0) {
            tgView.setAdapter(new TagAdapter(context, ret.getFeatureList()));
            llTag.setVisibility(View.VISIBLE);
        } else {
            llTag.setVisibility(View.GONE);
        }

    }
}
