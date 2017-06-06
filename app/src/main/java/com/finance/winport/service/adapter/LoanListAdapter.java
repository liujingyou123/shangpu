package com.finance.winport.service.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.service.model.LoanListResponse;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jge on 17/5/5.
 */
public class LoanListAdapter extends BaseAdapter {
    protected Context context;

    private List<LoanListResponse.DataBeanX.DataBean> baseData;

    public LoanListAdapter(Context context, List<LoanListResponse.DataBeanX.DataBean> baseData) {
        this.baseData = baseData;
        this.context = context;
    }

    public LoanListAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return baseData.size();
    }

    @Override
    public Object getItem(int position) {
        return baseData.get(position);
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

        holder.loanMoneyValue.setText(baseData.get(position).getLoanLimit()+"万元");
        holder.loanDeadlineValue.setText(baseData.get(position).getLoanMaturity()+"");
        if(baseData.get(position).getStatus().equals("0")){

            holder.status.setText(baseData.get(position).getApplyTime()+" 申请 | 待受理");
        }else if(baseData.get(position).getStatus().equals("1")){
            holder.status.setText(baseData.get(position).getApplyTime()+" 申请 | 已受理");

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
        @BindView(R.id.status)
        TextView status;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
