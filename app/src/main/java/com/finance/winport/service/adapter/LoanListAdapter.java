package com.finance.winport.service.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xzw on 16/8/26.
 */
public class LoanListAdapter extends BaseAdapter {
    protected Context context;

    //    public LoanListAdapter(Context context, List<MessageListResponse.DataBean> baseData) {
//        this.baseData = baseData;
//        this.context = context;
//    }
    public LoanListAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return 10;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.loan_list_item, viewGroup, false);
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

        @BindView(R.id.loan_money)
        TextView loanMoney;
        @BindView(R.id.loan_money_value)
        TextView loanMoneyValue;
        @BindView(R.id.loan_deadline)
        TextView loanDeadline;
        @BindView(R.id.loan_deadline_value)
        TextView loanDeadlineValue;
        @BindView(R.id.center)
        RelativeLayout center;
        @BindView(R.id.bottom_divider)
        View bottomDivider;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }




}
