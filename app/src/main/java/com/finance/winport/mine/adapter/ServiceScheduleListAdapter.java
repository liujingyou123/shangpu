package com.finance.winport.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.finance.winport.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jge on 17/5/5.
 */
public class ServiceScheduleListAdapter extends BaseAdapter {
    protected Context context;

    //    public LoanListAdapter(Context context, List<MessageListResponse.DataBean> baseData) {
//        this.baseData = baseData;
//        this.context = context;
//    }
    public ServiceScheduleListAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.service_schedule_list_item, viewGroup, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    private void readMessage(int messageId, final int position) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("messId", messageId);
//        ServiceExecutor.readMessage(params, new ResultCallBack<Response>() {
//            @Override
//            public void onSuccess(Response response) {
////                setCheckPosition(position);
//                EventBusManager.post(new NoticeRefreshEvent(true));
//            }
//
//            @Override
//            public void onFailure(String errorMsg) {
//            }
//        });
    }


    static class ViewHolder {
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.address)
        TextView address;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
