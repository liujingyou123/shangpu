package com.finance.winport.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.View;

import com.aigestudio.wheelpicker.WheelPicker;
import com.finance.winport.R;
import com.finance.winport.view.dialog.BottomSelectDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by css_double on 17/5/2.
 */

public class ScrollSelectDialog extends BottomSelectDialog implements WheelPicker.OnItemSelectedListener, View.OnClickListener{
    @BindView(R.id.wp)
    WheelPicker wp;
    @BindView(R.id.wp2)
    WheelPicker wp2;

    private List<String> datas;
    private List<String> datas2;
    private Map<String, List<String>> datasMap;
    private String wp_select_item;
    private String wp2_select_item;
    private OnSelectListener listener;

    private int selectIdx_store;
    private int selectIdx2_store;

    private int selectIdx;
    private int selectIdx2;
    private boolean isOk;

    public ScrollSelectDialog(@NonNull Context context, List<String> datas, OnSelectListener listener) {
        super(context);
        this.listener = listener;
        this.datas = datas;
        init();
    }

    public ScrollSelectDialog(@NonNull Context context, List<String> datas, List<String> datas2, OnSelectListener listener) {
        super(context);
        this.listener = listener;
        this.datas = datas;
        this.datas2 = datas2;
        init();
    }

    public ScrollSelectDialog(@NonNull Context context, List<String> datas, Map<String, List<String>> datasMap, OnSelectListener listener) {
        super(context);
        this.listener = listener;
        this.datas = datas;
        this.datasMap = datasMap;
        init();
    }

    private void init(){
        setContentView(R.layout.dialog_scroll_select);
        ButterKnife.bind(this);
        initView();
        initListener();
        initData();

        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                isOk = false;

                handleData(true);

                wp.setSelectedItemPosition(selectIdx);
                wp_select_item = datas.get(selectIdx);

                if (wp2.isShown()){
                    if (datasMap == null){
                        wp2.setSelectedItemPosition(selectIdx2);
                        wp2_select_item = datas2.get(selectIdx2);
                    }else{
                        datas2 = datasMap.get(wp_select_item);
                        if (datas2 == null){
                            datas2 = new ArrayList<String>();
                            datas2.add("");
                        }

                        wp2.setData(datas2);
                        wp2.setSelectedItemPosition(selectIdx2);
                        wp2_select_item = datas2.get(selectIdx2);
                    }
                }
            }
        });

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (!isOk){
                    handleData(false);
                }
            }
        });
    }

    private void handleData(boolean isStore){
        if (isStore){
            selectIdx_store = selectIdx;
            selectIdx2_store = selectIdx2;
        }else{
            selectIdx = selectIdx_store;
            selectIdx2 = selectIdx2_store;
        }
    }

    private void initView(){
        if (datas2 == null && datasMap == null){
            wp2.setVisibility(View.GONE);
        }
    }

    private void initListener() {
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        wp.setOnItemSelectedListener(this);
        wp2.setOnItemSelectedListener(this);
    }

    private void initData() {
        if (datas == null){
            datas = new ArrayList<>();
        }

        if (datas.size() == 0){
            datas.add("");
        }

        wp.setData(datas);
        wp_select_item = datas.get(0);


        if (datas2 == null){
            if (datasMap != null){
                datas2 = datasMap.get(wp_select_item);

                if (datas2 == null){
                    datas2 = new ArrayList<>();
                    datas2.add("");
                }

                wp2.setData(datas2);
                wp2_select_item = datas2.get(0);
            }
        }else{
            if (datas2.size() == 0){
                datas2.add("");
            }

            wp2.setData(datas2);
            wp2_select_item = datas2.get(0);
        }
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        if (picker == wp) {
            selectIdx = position;
            wp_select_item = (String) data;

            if (datasMap != null){
                datas2 = datasMap.get(wp_select_item);
                if (datas2 == null){
                    datas2 = new ArrayList<>();
                    datas2.add("");
                }

                wp2.setData(datas2);
                wp2.setSelectedItemPosition(0);
                wp2_select_item = datas2.get(0);
            }
        } else {
            selectIdx2 = position;
            wp2_select_item = (String) data;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnCancel){
            isOk = false;
            System.out.println("cancel   " + wp_select_item + ",   " + wp2_select_item);
        }else{
            isOk = true;
            System.out.println("ok   " + wp_select_item + ",   " + wp2_select_item);
            if (listener != null){
                if (datas2 == null && datasMap == null){
                    listener.onSelect(wp_select_item);
                }else{
                    listener.onSelect(wp_select_item + "-" + wp2_select_item);
                }
            }
        }

        dismiss();
    }

    public interface OnSelectListener{
        void onSelect(String data);
    }
}
