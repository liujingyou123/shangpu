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
import com.finance.winport.log.XLog;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.util.ToolsUtil;
import com.finance.winport.util.UnitUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    private List<RegionResponse.Region> mRegionData = new ArrayList<>();
    private RegionAdapter regionAdapter;
    private BlockAdapter blockAdapter;
    private List<RegionResponse.Region.Block> mBlockData = new ArrayList<>();

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

        setQuyuOrDitieSelect(1);
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        if (regionAdapter == null) {
            regionAdapter = new RegionAdapter(context, mRegionData);
        }
        lsOne.setAdapter(regionAdapter);
        lsOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RegionResponse.Region region = (RegionResponse.Region) parent.getItemAtPosition(position);
                if (region != null) {
                    for (int i = 0; i < mRegionData.size(); i++) {
                        mRegionData.get(i).setChecked(false);
                    }
                    region.setChecked(true);
                    regionAdapter.notifyDataSetChanged();


                    if (position == 0) {  //全部
                        //TODO 全部
                        lsTwo.setVisibility(View.GONE);
                        setBlockData(null);
                        dismiss();

                        mRequest = new ShopRequset();
                        mRequest.districtId = region.getRegionId();
                        mRequest.districtName = region.getRegionName();
                        EventBus.getDefault().post(mRequest);
                    } else {
                        mRequest.districtId = region.getRegionId();
                        mRequest.districtName = region.getRegionName();

                        setBlockData(region);
                    }
                }


            }
        });

        if (blockAdapter == null) {
            blockAdapter = new BlockAdapter(context, mBlockData);
        }
        lsTwo.setAdapter(blockAdapter);
        lsTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RegionResponse.Region.Block ret = (RegionResponse.Region.Block) parent.getItemAtPosition(position);
                if (ret != null) {
                    for (int i = 0; i < mBlockData.size(); i++) {
                        mBlockData.get(i).setChecked(false);
                    }
                    ret.setChecked(true);
                    blockAdapter.notifyDataSetChanged();

                    mRequest.blockId = ret.getBlockId();
                    mRequest.blockName = ret.getBlockName();
                    EventBus.getDefault().post(mRequest);
                    dismiss();

                }
            }
        });
    }

    @Override
    public void showAsDropDown(View anchor) {
        initWindowAttribute(anchor);
        super.showAsDropDown(anchor);
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
                    RegionResponse.Region allRegion = new RegionResponse.Region();
                    allRegion.setRegionName("全部");
                    allRegion.setRegionId("-1");
                    mRegionData.add(allRegion);
                    mRegionData.addAll(response.getData());
                    regionAdapter.notifyDataSetChanged();
                }

            }

        });
    }

    /**
     * 设置区域 地铁选中状态
     *
     * @param index
     */
    private void setQuyuOrDitieSelect(int index) {
        if (index == 1) {
            tvQuyu.setSelected(true);
            tvDitie.setSelected(false);
        } else if (index == 2) {
            tvQuyu.setSelected(false);
            tvDitie.setSelected(true);
        }
    }

    private void setBlockData(RegionResponse.Region region) {
        if (region != null && region.getBlockList() != null) {
            lsTwo.setVisibility(View.VISIBLE);
            mBlockData.clear();
            RegionResponse.Region.Block block = new RegionResponse.Region.Block();
            block.setBlockName("全部");
            block.setBlockId("-1");
            mBlockData.add(block);
            mBlockData.addAll(region.getBlockList());
            blockAdapter.notifyDataSetChanged();
        } else {
            mBlockData.clear();
            blockAdapter.notifyDataSetChanged();
        }
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
