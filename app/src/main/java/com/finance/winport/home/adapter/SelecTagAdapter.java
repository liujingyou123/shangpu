package com.finance.winport.home.adapter;
/**
 * Created by liuworkmac on 17/5/19.
 */

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.finance.winport.R;
import com.finance.winport.home.model.Tag;
import com.finance.winport.log.XLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelecTagAdapter extends BaseAdapter {
    private Context mContext;
    private List<Tag> mData;
    private List<String> selectList = new ArrayList<>();

    public SelecTagAdapter(Context mContext, List<Tag> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (mData != null) {
            ret = mData.size();
        }
        return ret;
    }

    @Override
    public Tag getItem(int i) {
        Tag ret = null;
        if (mData != null) {
            ret = mData.get(i);
        }
        return ret;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item_seletag, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Tag tag = mData.get(i);
        if (tag != null) {
            String name = tag.getName();
            if (name.length() > 4) {
                viewHolder.cbTag.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            } else {
                viewHolder.cbTag.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            }
            viewHolder.cbTag.setText(tag.getName());

            if (isSelectById(mData.get(i).getId()+"")) {
                viewHolder.cbTag.setChecked(true);
            } else {
                viewHolder.cbTag.setChecked(false);
            }

            viewHolder.cbTag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                int index = i;

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        selectList.add(mData.get(index).getId() + "");
                    } else {
                        selectList.remove(mData.get(index).getId() + "");
                    }

                    XLog.list(selectList);
                }
            });
        }
        return view;
    }

    public List<String> getSelectList() {
        return selectList;
    }

    public boolean isSelectById(String id) {
        boolean ret = false;

        if (selectList != null && selectList.size() > 0) {
            for (int i = 0; i < selectList.size(); i++) {
                if (selectList.get(i).equals(id)) {
                    ret = true;
                    break;
                }
            }
        }


        return ret;
    }

    public void setSelectList(List<String> list) {
        selectList.clear();
        selectList.addAll(list);
    }

    public void clearSelectList() {
        if (selectList != null) {
            selectList.clear();
        }
    }

    static class ViewHolder {
        @BindView(R.id.cb_tag)
        CheckBox cbTag;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}