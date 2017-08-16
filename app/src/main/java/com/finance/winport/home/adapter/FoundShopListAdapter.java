package com.finance.winport.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.home.model.FoundShopListResponse;
import com.finance.winport.image.Batman;
import com.finance.winport.service.model.LoanListResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jge on 17/8/3.
 */
public class FoundShopListAdapter extends BaseAdapter {
    protected Context context;

    private List<FoundShopListResponse.DataBeanX.DataBean> baseData;

    public FoundShopListAdapter(Context context, List<FoundShopListResponse.DataBeanX.DataBean> baseData) {
        this.baseData = baseData;
        this.context = context;
    }

    public FoundShopListAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return baseData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.found_shop_list_item, viewGroup, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.time.setText(baseData.get(position).getDateTime());
        holder.title.setText(baseData.get(position).getTitle());
        Batman.getInstance().fromNet(baseData.get(0).getImage(), holder.imgShop);

        return convertView;


    }


    static class ViewHolder {
        @BindView(R.id.img_shop)
        ImageView imgShop;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.title)
        TextView title;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
