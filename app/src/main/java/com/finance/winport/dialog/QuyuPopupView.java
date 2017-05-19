package com.finance.winport.dialog;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.home.adapter.BlockAdapter;
import com.finance.winport.home.adapter.RegionAdapter;
import com.finance.winport.home.api.HomeServices;
import com.finance.winport.home.model.RegionResponse;
import com.finance.winport.home.model.ShopRequset;
import com.finance.winport.home.tools.QuyuDataManager;
import com.finance.winport.log.XLog;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.util.ToolsUtil;
import com.finance.winport.util.UnitUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/5/4.
 */

public class QuyuPopupView extends AnimPopup {

    private Context context;
    @BindView(R.id.tv_quyu)
    TextView tvQuyu;
    @BindView(R.id.tv_ditie)
    TextView tvDitie;
    @BindView(R.id.ls_one)
    ListView lsOne;
    @BindView(R.id.ls_two)
    ListView lsTwo;
    @BindView(R.id.ll_weizhi)
    LinearLayout llWeizhi;
    @BindView(R.id.background)
    View viewBg;

    private RegionAdapter regionAdapter;
    private BlockAdapter blockAdapter;

    private ShopRequset mRequest = new ShopRequset();

    public QuyuPopupView(Context context) {
        super(context);
        initView(context);
        getDistrict();
    }

    private void initView(Context context) {
        this.context = context;
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_quyu_view, null);
        ButterKnife.bind(this, contentView);
        setContentView(contentView);

        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        if (regionAdapter == null) {
            regionAdapter = new RegionAdapter(context);
        }
        lsOne.setAdapter(regionAdapter);
        lsOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                regionAdapter.setSelectPostion(position);
                RegionResponse.Region region = (RegionResponse.Region) parent.getItemAtPosition(position);

                if ("-1".equals(region.getRegionId())) { //全部
                    mRequest = new ShopRequset();
                    EventBus.getDefault().post(mRequest);
                    dismiss();
                } else {
                    setBlockData(region);
                    if (mRequest.districtId == region.getRegionId()) {
                        blockAdapter.setSelectId(mRequest.blockId);
                    }
                }


            }
        });

        if (blockAdapter == null) {
            blockAdapter = new BlockAdapter(context);
        }
        lsTwo.setAdapter(blockAdapter);
        lsTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                blockAdapter.setSelectPosition(position);
                RegionResponse.Region.Block ret = (RegionResponse.Region.Block) parent.getItemAtPosition(position);

                if ("-1".equals(ret.getBlockId())) { //全部
                    mRequest.districtId = ret.getRegionId();
                    mRequest.districtName = ret.getRegionName();
                    mRequest.blockId = null;
                    mRequest.blockName = null;
                } else {
                    mRequest.districtId = ret.getRegionId();
                    mRequest.districtName = ret.getRegionName();
                    mRequest.blockId = ret.getBlockId();
                    mRequest.blockName = ret.getBlockName();
                }

                EventBus.getDefault().post(mRequest);
                dismiss();

            }
        });
    }

    @Override
    public void showAsDropDown(View anchor) {
        initWindowAttribute(anchor);
        super.showAsDropDown(anchor);

        showInitSelect();
    }

    private void initWindowAttribute(View anchor) {
        int[] point = new int[2];
        anchor.getLocationOnScreen(point);

        XLog.e("pointY = " + point[1]);
        this.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.transparent));
        setWidth(context.getResources().getDisplayMetrics().widthPixels);
        int height = UnitUtil.getScreenHeightPixels(context) - point[1] - anchor.getHeight();

        setHeight(height);
        initAnim(UnitUtil.getScreenHeightPixels(context));
    }

    private void initAnim(int height) {
        XLog.e("initAnim height = " + height);
        AnimatorSet showSet = new AnimatorSet();
        AnimatorSet dismissSet = new AnimatorSet();

        ObjectAnimator stoa = ObjectAnimator.ofFloat(llWeizhi, "translationY", -height, 0);
        stoa.setDuration(200);
        ObjectAnimator saoa = ObjectAnimator.ofFloat(viewBg, "alpha", 0, 1);
        saoa.setDuration(300);
        showSet.playTogether(stoa, saoa);
        setShowAnim(showSet);

        ObjectAnimator dtoa = ObjectAnimator.ofFloat(llWeizhi, "translationY", 0, -height);
        dtoa.setDuration(300);
        ObjectAnimator daoa = ObjectAnimator.ofFloat(viewBg, "alpha", 1, 0);
        saoa.setDuration(300);
        dismissSet.playTogether(dtoa, daoa);
        setDismissAnim(dismissSet);
    }


    private void getDistrict() {
        HashMap<String, String> map = new HashMap<>();
        map.put("cityId", "310000");
        ToolsUtil.subscribe(ToolsUtil.createService(HomeServices.class).getDistrict(map), new NetSubscriber<RegionResponse>() {
            @Override
            public void response(RegionResponse response) {
                if (response != null && response.getData() != null) {
                    QuyuDataManager.getInstance().addRegion(response.getData());
                    regionAdapter.initData();
                }

            }

        });
    }

    private void showInitSelect() {
        if (mRequest.metroId != null || mRequest.stationId != null) { //上次选的是地铁
            tvQuyu.setSelected(false);
            tvDitie.setSelected(true);
        } else {  //上次选的是区域 或者 没选
            tvQuyu.setSelected(true);
            tvDitie.setSelected(false);

            if (mRequest.districtId != null) {
                regionAdapter.setSelectId(mRequest.districtId);
            } else {
                lsTwo.setVisibility(View.GONE);
            }

            if (mRequest.blockId != null) {
                lsTwo.setVisibility(View.VISIBLE);
                blockAdapter.initDatasWithReiionAndBlockId(mRequest.districtId, mRequest.blockId);
            }
        }
    }

    /**
     * 设置区域 地铁选中状态
     *
     * @param index
     */
    private void setQuyuOrDitieSelect(int index) {
        if (index == 1) {

        } else if (index == 2) {

        }
    }

    private void setBlockData(RegionResponse.Region region) {
        lsTwo.setVisibility(View.VISIBLE);
        blockAdapter.initDataAndNotify(region);
    }

    @OnClick({R.id.tv_quyu, R.id.tv_ditie})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_quyu:
                setQuyuOrDitieSelect(1);
                break;
            case R.id.tv_ditie:
                setQuyuOrDitieSelect(2);
                break;
        }
    }
}
