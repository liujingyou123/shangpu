package com.finance.winport.mine;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.mine.adapter.TagAdapter;
import com.finance.winport.mine.adapter.TagItem;
import com.finance.winport.service.fragment.ShopRentFragment;
import com.finance.winport.view.tagview.TagCloudLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShopFocusActivity extends BaseActivity {

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

    private TagAdapter tagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_focus);
        ButterKnife.bind(this);
        setTagList();
    }


    private void setTagList() {

        List<TagItem> list = new ArrayList<>();
        String[] textColor = {"#646464", "#ff7725"};
        String[] bgColor = {"#f0f0f0", "#ffffff"};
        String[] strokeColor = {"#646464", "#ff7725"};
        for (int i = 0; i < 7; i++) {


            TagItem item = new TagItem();
            item.setTextColor(textColor);
            item.setBgColor(bgColor);
            item.setStrokeColor(strokeColor);
            item.setTagId(i+"");
            item.setTagName("20㎡以下");
                list.add(item);
        }
        if (tagAdapter == null) {
            tagAdapter = new TagAdapter(context, list);
            areaTag.setAdapter(tagAdapter);
        } else {
            tagAdapter.update(list);
        }

//        for (int i = 0; i <list.size() ; i++) {
//            for (int j = 0; j<list1.size(); j++){
//                if(list.get(i).getPlotName().equals(list1.get(j).getTagName())){
//                    tagAdapter.setMultiItemSelect(list1.get(j), true);
//                }
//            }
//        }
    }

}
