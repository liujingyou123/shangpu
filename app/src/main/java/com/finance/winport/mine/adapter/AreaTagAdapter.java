package com.finance.winport.mine.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.mine.model.CommitFocusRequest;
import com.finance.winport.mine.model.PersonalInfoResponse;
import com.finance.winport.util.UnitUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//import com.finance.businessman.repo.model.TagItem;

/**
 * Created by jge on 17/8/7.
 */
public class AreaTagAdapter extends BaseAdapter {
    private Context context;
    private List<PersonalInfoResponse.DataBean.Attention.Plate> list;

    public AreaTagAdapter(Context context, List<PersonalInfoResponse.DataBean.Attention.Plate> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return (list == null||list.size()==0) ? 1 : list.size()+1;
    }

    @Override
    public Object getItem(int position) {
        if (list == null) {
            return null;
        }
        if(position==list.size()){
            return null;
        }
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }




    public void update(List<PersonalInfoResponse.DataBean.Attention.Plate> list) {
        this.list = list;
        notifyDataSetChanged();
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.area_tag_item, viewGroup, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        try {
            if(list==null){
                holder.add.setVisibility(View.VISIBLE);
                holder.tag.setVisibility(View.GONE);
                holder.imvDel.setVisibility(View.GONE);
            }
            else if(list!=null&&position==list.size()){
                holder.add.setVisibility(View.VISIBLE);
                holder.tag.setVisibility(View.GONE);
                holder.imvDel.setVisibility(View.GONE);
            }else {
                holder.tag.setVisibility(View.VISIBLE);
                holder.imvDel.setVisibility(View.VISIBLE);
                holder.add.setVisibility(View.GONE);

                holder.tag.setText(list.get(position).plateName);
            }

            holder.imvDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    update(list);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }





    static class ViewHolder {
        @BindView(R.id.area_name)
        TextView tag;
        @BindView(R.id.imv_del)
        ImageView imvDel;
        @BindView(R.id.add)
        ImageView add;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
