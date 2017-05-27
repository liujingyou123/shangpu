package com.finance.winport.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.baidu.mapapi.map.Text;
import com.finance.winport.R;
import com.finance.winport.home.adapter.SelecTagAdapter;
import com.finance.winport.home.api.HomeServices;
import com.finance.winport.home.model.ShopRequset;
import com.finance.winport.home.model.Tag;
import com.finance.winport.home.model.TagResponse;
import com.finance.winport.log.XLog;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.util.ToolsUtil;
import com.finance.winport.view.tagview.TagCloudLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 16/9/1.
 * 筛选弹框
 */
public class SelectionDialog extends Dialog implements DialogInterface.OnDismissListener {

    @BindView(R.id.sv)
    ScrollView sv;
    @BindView(R.id.cb_rent_price)
    CheckBox cbRentPrice;
    @BindView(R.id.cb_rent_price1)
    CheckBox cbRentPrice1;
    @BindView(R.id.cb_rent_price2)
    CheckBox cbRentPrice2;
    @BindView(R.id.ll_rent_one)
    LinearLayout llRentOne;
    @BindView(R.id.ll_rent_two)
    LinearLayout llRentTwo;
    @BindView(R.id.cb_rent_price3)
    CheckBox cbRentPrice3;
    @BindView(R.id.cb_rent_price4)
    CheckBox cbRentPrice4;
    @BindView(R.id.cb_rent_price5)
    CheckBox cbRentPrice5;
    @BindView(R.id.cb_price)
    CheckBox cbPrice;
    @BindView(R.id.cb_price1)
    CheckBox cbPrice1;
    @BindView(R.id.cb_price2)
    CheckBox cbPrice2;
    @BindView(R.id.ll_price_one)
    LinearLayout llPriceOne;
    @BindView(R.id.cb_price3)
    CheckBox cbPrice3;
    @BindView(R.id.cb_price4)
    CheckBox cbPrice4;
    @BindView(R.id.cb_price5)
    CheckBox cbPrice5;
    @BindView(R.id.ll_price_two)
    LinearLayout llPriceTwo;
    @BindView(R.id.cb_area)
    CheckBox cbArea;
    @BindView(R.id.cb_area1)
    CheckBox cbArea1;
    @BindView(R.id.cb_area2)
    CheckBox cbArea2;
    @BindView(R.id.ll_area_one)
    LinearLayout llAreaOne;
    @BindView(R.id.cb_area3)
    CheckBox cbArea3;
    @BindView(R.id.cb_area4)
    CheckBox cbArea4;
    @BindView(R.id.cb_area5)
    CheckBox cbArea5;
    @BindView(R.id.ll_area_two)
    LinearLayout llAreaTwo;
    @BindView(R.id.cb_area6)
    CheckBox cbArea6;
    @BindView(R.id.tc_feature)
    TagCloudLayout tcFeature;
    @BindView(R.id.tc_support)
    TagCloudLayout tcSupport;
    @BindView(R.id.et_width)
    EditText etWidth;
    private Context mContext;
    private SelecTagAdapter featureAdapter;
    private SelecTagAdapter supportAdapter;
    private List<String> rent = new ArrayList<>(); //租金
    private List<String> price = new ArrayList<>(); //转让费
    private List<String> area = new ArrayList<>(); //面积
    private List<Tag> featureData = new ArrayList<>();
    private List<Tag> supportData = new ArrayList<>();

    private ShopRequset requset = new ShopRequset();
    private OnSelectListener mOnSelectListener;

    public SelectionDialog(Context context) {
        super(context, R.style.customDialog);
        init(context);

    }

    private void init(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_selection, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        setCanceledOnTouchOutside(true);

        Window window = getWindow();
        window.setType(WindowManager.LayoutParams.TYPE_TOAST);
        window.setGravity(Gravity.RIGHT);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, mContext.getResources().getDisplayMetrics());
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.dialogSelect);

        sv.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        sv.setFocusable(true);
        sv.setFocusableInTouchMode(true);
        sv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });

        initRentClickListener();
        initPriceClickListener();
        initAreaClickListener();

        getTagList("1");
        getTagList("2");

        setOnDismissListener(this);
    }

    /**
     * @param type 1:特色  2:配套
     */
    private void getTagList(final String type) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("tagType", type);
        ToolsUtil.subscribe(ToolsUtil.createService(HomeServices.class).getTagList(hashMap), new NetSubscriber<TagResponse>() {
            @Override
            public void response(TagResponse response) {
                if ("1".equals(type)) {
                    featureData.addAll(response.getData());
                    featureAdapter = new SelecTagAdapter(mContext, response.getData());
                    tcFeature.setAdapter(featureAdapter);
                } else if ("2".equals(type)) {
                    supportData.addAll(response.getData());
                    supportAdapter = new SelecTagAdapter(mContext, response.getData());
                    tcSupport.setAdapter(supportAdapter);
                }


            }
        });


    }

    private void initRentClickListener() {
        cbRentPrice.setOnCheckedChangeListener(onllRentCheckedChangeListener);
        cbRentPrice1.setOnCheckedChangeListener(onllRentCheckedChangeListener);
        cbRentPrice2.setOnCheckedChangeListener(onllRentCheckedChangeListener);
        cbRentPrice3.setOnCheckedChangeListener(onllRentCheckedChangeListener);
        cbRentPrice4.setOnCheckedChangeListener(onllRentCheckedChangeListener);
        cbRentPrice5.setOnCheckedChangeListener(onllRentCheckedChangeListener);
    }

    private void initPriceClickListener() {
        cbPrice.setOnCheckedChangeListener(onllPriceCheckedChangeListener);
        cbPrice1.setOnCheckedChangeListener(onllPriceCheckedChangeListener);
        cbPrice2.setOnCheckedChangeListener(onllPriceCheckedChangeListener);
        cbPrice3.setOnCheckedChangeListener(onllPriceCheckedChangeListener);
        cbPrice4.setOnCheckedChangeListener(onllPriceCheckedChangeListener);
        cbPrice5.setOnCheckedChangeListener(onllPriceCheckedChangeListener);
    }

    private void initAreaClickListener() {
        cbArea.setOnCheckedChangeListener(onllAreaCheckedChangeListener);
        cbArea1.setOnCheckedChangeListener(onllAreaCheckedChangeListener);
        cbArea2.setOnCheckedChangeListener(onllAreaCheckedChangeListener);
        cbArea3.setOnCheckedChangeListener(onllAreaCheckedChangeListener);
        cbArea4.setOnCheckedChangeListener(onllAreaCheckedChangeListener);
        cbArea5.setOnCheckedChangeListener(onllAreaCheckedChangeListener);
        cbArea6.setOnCheckedChangeListener(onllAreaCheckedChangeListener);

    }

    CompoundButton.OnCheckedChangeListener onllRentCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int indextRentOne = llRentOne.indexOfChild(buttonView);
            if (indextRentOne > -1) {
                if (isChecked) {
                    rent.add((indextRentOne + 1) + "");
                } else {
                    rent.remove((indextRentOne + 1) + "");
                }
                XLog.list(rent);
                return;
            }

            int indexRentTwo = llRentTwo.indexOfChild(buttonView);
            if (indexRentTwo > -1) {
                if (isChecked) {
                    rent.add((indexRentTwo + 4) + "");
                } else {
                    rent.remove((indexRentTwo + 4) + "");
                }
            }
            XLog.list(rent);
        }
    };

    CompoundButton.OnCheckedChangeListener onllPriceCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int indextRentOne = llPriceOne.indexOfChild(buttonView);
            if (indextRentOne > -1) {
                if (isChecked) {
                    price.add((indextRentOne + 1) + "");
                } else {
                    price.remove((indextRentOne + 1) + "");
                }
                XLog.list(price);
                return;
            }

            int indexRentTwo = llPriceTwo.indexOfChild(buttonView);
            if (indexRentTwo > -1) {
                if (isChecked) {
                    price.add((indexRentTwo + 4) + "");
                } else {
                    price.remove((indexRentTwo + 4) + "");
                }
            }
            XLog.list(price);
        }
    };

    CompoundButton.OnCheckedChangeListener onllAreaCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int indextRentOne = llAreaOne.indexOfChild(buttonView);
            if (indextRentOne > -1) {
                if (isChecked) {
                    area.add((indextRentOne + 1) + "");
                } else {
                    area.remove((indextRentOne + 1) + "");
                }
                XLog.list(area);
                return;
            }

            int indexRentTwo = llAreaTwo.indexOfChild(buttonView);
            if (indexRentTwo > -1) {
                if (isChecked) {
                    area.add((indexRentTwo + 4) + "");
                } else {
                    area.remove((indexRentTwo + 4) + "");
                }
                XLog.list(area);
                return;
            }

            if (buttonView.getId() == cbArea6.getId()) {
                if (isChecked) {
                    area.add("7");
                } else {
                    area.remove("7");
                }
            }
            XLog.list(area);
        }
    };

    @OnClick({R.id.btn_reset, R.id.btn_done,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_reset:
                setResetData();
                break;
            case R.id.btn_done:
                setRequestData();
                dismiss();
                break;
        }
    }

    private void setRequestData() {
        requset.rentList = rent;
        requset.transferList = price;
        requset.areaList = area;

        if (etWidth.getText() != null && !TextUtils.isEmpty(etWidth.getText().toString())) {
            requset.width = etWidth.getText().toString();
        }

        requset.featureTagList = featureAdapter.getSelectList();
        requset.supportTagList = supportAdapter.getSelectList();
    }

    private void setResetData() {
        requset = new ShopRequset();
        setCheckedFalse(llRentOne);
        setCheckedFalse(llRentTwo);
        setCheckedFalse(llPriceOne);
        setCheckedFalse(llPriceTwo);
        setCheckedFalse(llAreaOne);
        setCheckedFalse(llAreaTwo);
        cbArea6.setChecked(false);
        etWidth.setText(null);

        List<Tag> mFdata = new ArrayList<>(featureData);
        featureAdapter = new SelecTagAdapter(mContext, mFdata);
        tcFeature.setAdapter(featureAdapter);


        List<Tag> mSdata = new ArrayList<>(supportData);
        supportAdapter = new SelecTagAdapter(mContext, mSdata);
        tcSupport.setAdapter(supportAdapter);
    }

    private void setCheckedFalse(ViewGroup viewGroup) {
        int size = viewGroup.getChildCount();
        for (int i = 0; i < size; i++) {
            View childView = viewGroup.getChildAt(i);
            if (childView != null && childView instanceof CheckBox) {
                ((CheckBox) childView).setChecked(false);
            }
        }
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.mOnSelectListener = onSelectListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (mOnSelectListener != null) {
            mOnSelectListener.onSelect(requset);
        }
    }

    public interface OnSelectListener {
        void onSelect(ShopRequset request);
    }


    public void initAreaData(List<String> mArea) {
        if (mArea != null && mArea.size() > 0) {
            for (int i = 0; i < mArea.size(); i++) {
                int areaId = Integer.parseInt(mArea.get(i));
                if (areaId < 4) {
                    ((CheckBox) llAreaOne.getChildAt(areaId - 1)).setChecked(true);
                } else if (areaId < 7) {
                    ((CheckBox) llAreaTwo.getChildAt(areaId - 1)).setChecked(true);
                } else if (areaId == 7) {
                    cbArea6.setChecked(true);
                }
            }
            requset.areaList = area;
        }
    }
}
