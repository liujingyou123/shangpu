package com.finance.winport.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.aigestudio.wheelpicker.WheelPicker;
import com.finance.winport.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 16/11/28.
 */
public class SelectHouseTypeDialog extends Dialog implements WheelPicker.OnItemSelectedListener {

    @BindView(R.id.main_wv)
    WheelPicker mainWv;
    @BindView(R.id.main_wv2)
    WheelPicker mainWv2;
    @BindView(R.id.main_wv3)
    WheelPicker mainWv3;
    private Context mContext;

    private ArrayList<String> list1 = new ArrayList<>();
    private ArrayList<String> list2 = new ArrayList<>();
    private ArrayList<String> list3 = new ArrayList<>();

    private OnItemSelectListener mOnItemSelectListener;


    public SelectHouseTypeDialog(Context context) {
        super(context, R.style.customDialog);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_select_house_type, null);
        setContentView(view);
        ButterKnife.bind(this, view);

        setCanceledOnTouchOutside(true);

        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 206, mContext.getResources().getDisplayMetrics());
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.dialogWheel);
    }

    public void setList1(List<String> data1, List<String> data2, List<String> data3) {
        mainWv.setVisibility(View.GONE);
        mainWv2.setVisibility(View.GONE);
        mainWv3.setVisibility(View.GONE);

        if (data1 != null && data1.size() > 0) {
            mainWv.setVisibility(View.VISIBLE);
            list1.clear();
            list1.addAll(data1);
            mainWv.setData(list1);
        }

        if (data2 != null && data2.size() > 0) {
            mainWv2.setVisibility(View.VISIBLE);
            list2.clear();
            list2.addAll(data2);
            mainWv2.setData(list2);
        }

        if (data3 != null && data3.size() > 0) {
            mainWv3.setVisibility(View.VISIBLE);
            list3.clear();
            list3.addAll(data3);
            mainWv3.setData(list3);
        }
    }

    public void setList(List<String> data, List<String> data2, List<String> data3) {

        if (data == null || data2 == null || data3 == null) {
            return;
        }
        list1.clear();
        list1.addAll(data);
        mainWv.setData(list1);

        list2.clear();
        list2.addAll(data2);
        mainWv2.setData(list2);

        list3.clear();
        list3.addAll(data3);
        mainWv3.setData(list3);

    }

    public void setList(List<String> data, List<String> data2) {

        if (data == null || data2 == null ) {
            return;
        }
        list1.clear();
        list1.addAll(data);
        mainWv.setData(list1);



        list2.clear();
        list2.addAll(data2);
        mainWv2.setData(list2);



        mainWv3.setVisibility(View.GONE);

    }


    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.mOnItemSelectListener = onItemSelectListener;
    }

    @OnClick({R.id.tv_ok, R.id.tv_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_ok:
                dismiss();
                if (mOnItemSelectListener != null) {
                    int indexone = -1;
                    int indextwo = -1;
                    int indexthree = -1;

                    if (mainWv.getVisibility() == View.VISIBLE) {
                        indexone = mainWv.getCurrentItemPosition();
                    }

                    if (mainWv2.getVisibility() == View.VISIBLE) {
                        indextwo = mainWv2.getCurrentItemPosition();
                    }

                    if (mainWv3.getVisibility() == View.VISIBLE) {
                        indexthree = mainWv3.getCurrentItemPosition();
                    }
                    mOnItemSelectListener.onItemSelect(indexone, indextwo, indexthree);
                }
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {

//        if (picker == mainWv) {
//            year = num;
//            updateDayData();
//        }
    }


    public interface OnItemSelectListener {
        void onItemSelect(int indexone, int indextwo, int indexthree);
    }
}
