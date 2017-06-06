package com.finance.winport.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.dialog.ScrollSelectDialog;
import com.finance.winport.home.api.HomeServices;
import com.finance.winport.home.model.RegionResponse;
import com.finance.winport.mine.adapter.TagAdapter;
import com.finance.winport.mine.adapter.TagItem;
import com.finance.winport.mine.model.CommitFocusRequest;
import com.finance.winport.mine.model.IndustryListResponse;
import com.finance.winport.mine.presenter.IShopFocusView;
import com.finance.winport.mine.presenter.ShopFocusPresenter;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.util.SpUtil;
import com.finance.winport.util.ToolsUtil;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.tagview.TagCloudLayout;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ShopFocusActivity extends BaseActivity implements IShopFocusView {

    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.tag)
    TagCloudLayout tag;
    @BindView(R.id.area_tag)
    TagCloudLayout areaTag;
    @BindView(R.id.focus_content)
    TextView focusContent;
    @BindView(R.id.select_area)
    RelativeLayout selectArea;
    @BindView(R.id.district)
    TextView district;

    private TagAdapter tagAdapter;
    private TagAdapter industryTagAdapter;//经营业态

    private ShopFocusPresenter mPresenter;

    private List<TagItem> selectList = new ArrayList<>();
    private TagItem selectIndustry;

    private List<Integer> list1 = new ArrayList<>();

    //    private ArrayList<Integer> selectList = new ArrayList<>();
    private String industryName, blockName,districtName,industryId,districtId,blockId;
    private HashMap<String, List<String>> hashMap = new HashMap<>();
    private HashMap<String, List<RegionResponse.Region.Block>> hashMapBlock = new HashMap<>();
    ScrollSelectDialog scrollDialog;
    List<String> list = new ArrayList<String>();
    List<RegionResponse.Region> regionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_focus);
        ButterKnife.bind(this);
        setTagList();
        getData();
        getDistrict();
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new ShopFocusPresenter(this);
        }
        mPresenter.getIndustryList();
    }

    private void commit(){
        CommitFocusRequest request = new CommitFocusRequest();
        request.setList(list1);
        request.setBlockId(blockId);
        request.setBlockName(blockName);
        request.setDistrictId(districtId);
        request.setDistrictName(districtName);
        request.setIndustryId(industryId);
        request.setIndustryName(industryName);
        request.setCityId("310000");
        request.setCityName("上海市");
        if (mPresenter == null) {
            mPresenter = new ShopFocusPresenter(this);
        }
        mPresenter.commitFocus(request);
    }

    private void setTagList() {

        industryName = getIntent().getStringExtra("industryName");
        blockName = getIntent().getStringExtra("blockName");
        districtName = getIntent().getStringExtra("districtName");
        industryId = getIntent().getStringExtra("industryId");
        districtId = getIntent().getStringExtra("districtId");
        blockId = getIntent().getStringExtra("blockId");
        list1 = getIntent().getIntegerArrayListExtra("areaList");
        StringBuilder s = new StringBuilder();
        List<String> content = new ArrayList<>();
        if(!TextUtils.isEmpty(blockName)){

            content.add(blockName);
        }
        if(!TextUtils.isEmpty(industryName)){

            content.add(industryName);
        }
        if(list1!=null){

            for (int i = 0; i < list1.size(); i++) {

                switch (list1.get(i)) {
                    case 1:
                        content.add("20㎡以下");
                        break;
                    case 2:
                        content.add("20-50㎡");
                        break;
                    case 3:
                        content.add("50-100㎡");
                        break;
                    case 4:
                        content.add("100-200㎡");
                        break;
                    case 5:
                        content.add("200-500㎡");
                        break;
                    case 6:
                        content.add("500-1000㎡");
                        break;
                    case 7:
                        content.add("1000㎡以上");
                        break;
                }
            }

        }
        else {
            list1 = new ArrayList<>();
        }

        for (int i = 0; i < content.size(); i++) {
            if(i==0){

                s.append(content.get(i));
            }else if(i==2||i==5){
                s.append("-" + content.get(i)+ "\n");
            }else {
                s.append("-" + content.get(i));
            }
        }
        if(!TextUtils.isEmpty(blockName)&&!TextUtils.isEmpty(districtName)){

            district.setText(districtName+"-"+blockName);
        }


        focusContent.setText(s.toString());
        if(s.toString().length()>0){

            focusContent.setTextColor(Color.parseColor("#ffa73b"));
        }


//        focusContent.setText("江湾镇-餐饮类-20~50㎡\n500~1000㎡");

        List<TagItem> arealist = new ArrayList<>();
        String[] textColor = {"#646464", "#ff7725"};
        String[] bgColor = {"#f0f0f0", "#ffffff"};
        String[] strokeColor = {"#646464", "#ff7725"};
        for (int i = 1; i < 8; i++) {


            TagItem item = new TagItem();
            item.setTextColor(textColor);
            item.setBgColor(bgColor);
            item.setStrokeColor(strokeColor);
            item.setTagId(i + "");
            switch (i) {
                case 1:
                    item.setTagName("20㎡以下");
                    break;
                case 2:
                    item.setTagName("20-50㎡");
                    break;
                case 3:
                    item.setTagName("50-100㎡");
                    break;
                case 4:
                    item.setTagName("100-200㎡");
                    break;
                case 5:
                    item.setTagName("200-500㎡");
                    break;
                case 6:
                    item.setTagName("500-1000㎡");
                    break;
                case 7:
                    item.setTagName("1000㎡以上");
                    break;
            }

            arealist.add(item);
        }
        if (tagAdapter == null) {
            tagAdapter = new TagAdapter(context, arealist);
            areaTag.setAdapter(tagAdapter);
        } else {
            tagAdapter.update(arealist);
        }

//        list1.add(0);
//        list1.add(2);
//        list1.add(5);
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < arealist.size(); j++) {
                if (arealist.get(j).getTagId().equals(list1.get(i) + "")) {
                    tagAdapter.setMultiItemSelect(arealist.get(j), true);
                    selectList.add(arealist.get(j));
                }
            }
        }
        areaTag.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                TagItem item = (TagItem) tagAdapter.getItem(position);
                if (item != null) {
                    if (item.isSelected()) {
                        tagAdapter.setMultiItemSelect(item, false);
                        selectList.remove(selectList.indexOf(item));
                        list1.remove((Integer) Integer.parseInt(item.getTagId()));
//                        StringBuilder s = new StringBuilder();
//                        for (int i = 0; i <selectList.size() ; i++) {
//                            if(i==0){
//
//                                if(selectList.size()==1){
//
//                                    s.append(selectList.get(i).getTagName());
//                                }
//                                else{
//                                    s.append(selectList.get(i).getTagName()+"\n");
//                                }
//                            }
//                            else if(selectList.size()<5){
//                                s.append("-"+selectList.get(i).getTagName());
//                            }else if(i==3){
//                                s.append("-"+selectList.get(i).getTagName()+"\n");
//                            }
//                        }
//                        focusContent.setText("江湾镇-餐饮类-"+s.toString());
                    } else {
                        tagAdapter.setMultiItemSelect(item, true);
                        selectList.add(item);
                        list1.add(Integer.parseInt(item.getTagId()));
//                        StringBuilder s = new StringBuilder();
//                        for (int i = 0; i <selectList.size() ; i++) {
//                            if(i==0){
//
//                                if(selectList.size()==1){
//
//                                    s.append(selectList.get(i).getTagName());
//                                }
//                                else{
//                                    s.append(selectList.get(i).getTagName()+"\n");
//                                }
//                            }
//                            else if(selectList.size()<5){
//                                s.append("-"+selectList.get(i).getTagName());
//                            }else if(i==3){
//                                s.append("-"+selectList.get(i).getTagName()+"\n");
//                            }else{
//                                s.append("-"+selectList.get(i).getTagName());
//                            }
//                        }
//                        focusContent.setText("江湾镇-餐饮类-"+s.toString());
                    }
                }
            }
        });


    }

//    @OnClick(R.id.imv_focus_house_back)
//    public void onViewClicked() {
//        handleBack();
//    }

    /**
     * 楼层
     */
    private void showLouCeng() {
        if (scrollDialog == null) {

            scrollDialog = new ScrollSelectDialog(ShopFocusActivity.this, list, hashMap, new ScrollSelectDialog.OnSelectListener() {
                @Override
                public void onSelect(String data) {


                    try {

                        if(TextUtils.isEmpty(data)){
                            return;
                        }
                        district.setText(data);
                        districtName = data.split("-")[0];
                        blockName = data.split("-")[1];
                        for (int i = 0; i <list.size() ; i++) {
                            if(districtName.equals(regionList.get(i).getRegionName())){
                                districtId = regionList.get(i).getRegionId();
                            }
                        }
                        List<RegionResponse.Region.Block> blockList = new ArrayList<>();
                        blockList = hashMapBlock.get(districtName);
                        for (int j = 0; j <blockList.size() ; j++) {
                            if(blockName.equals(blockList.get(j).getBlockName())){
                                blockId = blockList.get(j).getBlockId();
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }



                }
            });
        }
        scrollDialog.show();
    }

    @OnClick({R.id.imv_focus_house_back, R.id.select_area, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                handleBack();
                break;
            case R.id.select_area:
                showLouCeng();
                break;
            case R.id.commit:
                MobclickAgent.onEvent(ShopFocusActivity.this, "shopfollow_confirm");
                commit();
                break;
        }
    }

    @Override
    public void shopIndustryList(IndustryListResponse response) {
        List<TagItem> list = new ArrayList<>();
        String[] textColor = {"#646464", "#ff7725"};
        String[] bgColor = {"#f0f0f0", "#ffffff"};
        String[] strokeColor = {"#646464", "#ff7725"};
        for (int i = 0; i < response.getData().size(); i++) {
            TagItem item = new TagItem();
            item.setTextColor(textColor);
            item.setBgColor(bgColor);
            item.setStrokeColor(strokeColor);
            item.setTagId(response.getData().get(i).getIndustryId() + "");
            item.setTagName(response.getData().get(i).getIndustryName());
            list.add(item);
        }
        if (industryTagAdapter == null) {
            industryTagAdapter = new TagAdapter(context, list);
            tag.setAdapter(industryTagAdapter);
        } else {
            industryTagAdapter.update(list);
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTagId().equals(industryId)) {
                industryTagAdapter.setSingleItemSelect(i);
            }
        }


        tag.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
                                     @Override
                                     public void itemClick(int position) {
                                         TagItem item = (TagItem) industryTagAdapter.getItem(position);
                                         if (item != null) {
                                             if (!item.isSelected()) {// 未选择状态

                                                 industryTagAdapter.setSingleItemSelect(position);
                                                 industryName = item.getTagName();
                                                 industryId = item.getTagId();

                                             } else {//已选择状态
                                                 industryTagAdapter.setSingleItemUnSelect(position);
                                                 industryId = "";
                                                 industryName = "";
                                             }
                                         }
                                     }
                                 }

        );
    }

    @Override
    public void commitFocus(BaseResponse response) {
        SpUtil.getInstance().setStringData(SharedPrefsUtil.getUserInfo().data.userPhone, "1");
        finish();
    }


    private void getDistrict() {
        HashMap<String, String> map = new HashMap<>();
        map.put("cityId", "310000");

        ToolsUtil.subscribe(ToolsUtil.createService(HomeServices.class).getDistrict(map), new NetSubscriber<RegionResponse>() {
            @Override
            public void response(RegionResponse response) {

                try {
                    regionList = response.getData();
                    for (int i = 0; i < response.getData().size(); i++) {
                        List<String> list1 = new ArrayList<String>();
                        list.add(response.getData().get(i).getRegionName());

                        for (int j = 0; j < response.getData().get(i).getBlockList().size(); j++) {
                            list1.add(response.getData().get(i).getBlockList().get(j).getBlockName());
                        }
                        hashMap.put(response.getData().get(i).getRegionName(), list1);
                        hashMapBlock.put(response.getData().get(i).getRegionName(),response.getData().get(i).getBlockList());
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        });
    }

}
