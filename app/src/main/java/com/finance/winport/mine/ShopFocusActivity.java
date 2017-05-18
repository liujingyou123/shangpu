package com.finance.winport.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.dialog.SelectHouseTypeDialog;
import com.finance.winport.mine.adapter.TagAdapter;
import com.finance.winport.mine.adapter.TagItem;
import com.finance.winport.mine.model.IndustryListResponse;
import com.finance.winport.mine.presenter.IShopFocusView;
import com.finance.winport.mine.presenter.ShopFocusPresenter;
import com.finance.winport.service.presenter.ServicePresenter;
import com.finance.winport.view.tagview.TagCloudLayout;

import java.util.ArrayList;
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

    private TagAdapter tagAdapter;
    private TagAdapter industryTagAdapter;//经营业态

    private ShopFocusPresenter mPresenter;

    private List<TagItem> selectList = new ArrayList<>();
    private TagItem selectIndustry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_focus);
        ButterKnife.bind(this);
        setTagList();
        getData();
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new ShopFocusPresenter(this);
        }
        mPresenter.getIndustryList();
    }

    private void setTagList() {

        focusContent.setText("江湾镇-餐饮类-20~50㎡\n500~1000㎡");

        List<TagItem> list = new ArrayList<>();
        String[] textColor = {"#646464", "#ff7725"};
        String[] bgColor = {"#f0f0f0", "#ffffff"};
        String[] strokeColor = {"#646464", "#ff7725"};
        for (int i = 0; i < 7; i++) {


            TagItem item = new TagItem();
            item.setTextColor(textColor);
            item.setBgColor(bgColor);
            item.setStrokeColor(strokeColor);
            item.setTagId(i + "");
            switch (i) {
                case 0:
                    item.setTagName("20㎡以下");
                    break;
                case 1:
                    item.setTagName("20-50㎡");
                    break;
                case 2:
                    item.setTagName("50-100㎡");
                    break;
                case 3:
                    item.setTagName("100-200㎡");
                    break;
                case 4:
                    item.setTagName("200-500㎡");
                    break;
                case 5:
                    item.setTagName("500-1000㎡");
                    break;
                case 6:
                    item.setTagName("1000㎡以上");
                    break;
            }

            list.add(item);
        }
        if (tagAdapter == null) {
            tagAdapter = new TagAdapter(context, list);
            areaTag.setAdapter(tagAdapter);
        } else {
            tagAdapter.update(list);
        }

        areaTag.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                TagItem item = (TagItem) tagAdapter.getItem(position);
                if (item != null) {
                    if (item.isSelected()) {
                        tagAdapter.setMultiItemSelect(item, false);
                        selectList.remove(selectList.indexOf(item));
                        StringBuilder s = new StringBuilder();
                        for (int i = 0; i <selectList.size() ; i++) {
                            if(i==0){

                                if(selectList.size()==1){

                                    s.append(selectList.get(i).getTagName());
                                }
                                else{
                                    s.append(selectList.get(i).getTagName()+"\n");
                                }
                            }
                            else if(selectList.size()<5){
                                s.append("-"+selectList.get(i).getTagName());
                            }else if(i==3){
                                s.append("-"+selectList.get(i).getTagName()+"\n");
                            }
                        }
                        focusContent.setText("江湾镇-餐饮类-"+s.toString());
                    } else {
                        tagAdapter.setMultiItemSelect(item, true);
                        selectList.add(item);
                        StringBuilder s = new StringBuilder();
                        for (int i = 0; i <selectList.size() ; i++) {
                            if(i==0){

                                if(selectList.size()==1){

                                    s.append(selectList.get(i).getTagName());
                                }
                                else{
                                    s.append(selectList.get(i).getTagName()+"\n");
                                }
                            }
                            else if(selectList.size()<5){
                                s.append("-"+selectList.get(i).getTagName());
                            }else if(i==3){
                                s.append("-"+selectList.get(i).getTagName()+"\n");
                            }else{
                                s.append("-"+selectList.get(i).getTagName());
                            }
                        }
                        focusContent.setText("江湾镇-餐饮类-"+s.toString());
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
        List<String> its0 = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            its0.add("" + i + "层");
        }

        List<String> its1 = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            its1.add("" + i + "层");
        }


        final SelectHouseTypeDialog dialog = new SelectHouseTypeDialog(this);
        dialog.setList(its0, its1);

        dialog.setOnItemSelectListener(new SelectHouseTypeDialog.OnItemSelectListener() {
            @Override
            public void onItemSelect(int indexone, int indextwo, int indexthree) {



            }
        });

        dialog.show();
    }

    @OnClick({R.id.imv_focus_house_back, R.id.select_area})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                handleBack();
                break;
            case R.id.select_area:
                showLouCeng();
                break;
        }
    }

    @Override
    public void shopIndustryList(IndustryListResponse response) {
        List<TagItem> list = new ArrayList<>();
        String[] textColor = {"#646464", "#ff7725"};
        String[] bgColor = {"#f0f0f0", "#ffffff"};
        String[] strokeColor = {"#646464", "#ff7725"};
        for (int i = 0; i <response.getData().size() ; i++) {
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

        tag.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
                                         @Override
                                         public void itemClick(int position) {
                                             TagItem item = (TagItem) industryTagAdapter.getItem(position);
                                             if (item != null) {
                                                 if (!item.isSelected()) {// 未选择状态

                                                     industryTagAdapter.setSingleItemSelect(position);

                                                 } else {//已选择状态
                                                     industryTagAdapter.setSingleItemUnSelect(position);
                                                 }
                                             }
                                         }
                                     }

        );
    }

//    @OnClick(R.id.select_area)
//    public void onViewClicked() {
//    }
}
