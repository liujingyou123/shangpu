package com.finance.winport.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.home.model.Tag;
import com.finance.winport.tab.model.NameValue;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xzw on 2017/8/2.
 */

public class OffShelfDialog extends Dialog implements View.OnClickListener {

    @BindView(R.id.mListView)
    ListView mListView;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    private View.OnClickListener onOkClickListener;
    private View.OnClickListener onCancelClickListener;
    private OnItemClickListener onItemClickListener;

    public OffShelfDialog(@NonNull Context context) {
        super(context, R.style.noticeDialog);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_off_shelf, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        setCanceledOnTouchOutside(true);

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 280, context.getResources().getDisplayMetrics());
        lp.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 188, context.getResources().getDisplayMetrics());
        window.setAttributes(lp);

        mListView.setAdapter(new OffShelfAdapter(context, initData()));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick((NameValue) mListView.getAdapter().getItem(position), position);
                }
            }
        });
    }

    public OffShelfDialog setOnOkClickListener(View.OnClickListener onOkClickListener) {
        this.onOkClickListener = onOkClickListener;
        return this;
    }

    public OffShelfDialog setOnCancelClickListener(View.OnClickListener onCancelClickListener) {
        this.onCancelClickListener = onCancelClickListener;
        return this;
    }

    public OffShelfDialog setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ok:
                if (onOkClickListener != null) {
                    onOkClickListener.onClick(v);
                }
                break;
            case R.id.tv_cancel:
                if (onCancelClickListener != null) {
                    onCancelClickListener.onClick(v);
                }
                break;
        }
        dismiss();
    }

    class OffShelfAdapter extends BaseAdapter {
        List<NameValue> data;
        Context context;
        int selection = -1;

        public OffShelfAdapter(Context context, List<NameValue> data) {
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void setSelection(int selection) {
            this.selection = selection;
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.off_shelf_item, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (selection == position) {
                holder.reason.setSelected(true);
            } else {
                holder.reason.setSelected(false);
            }
            holder.reason.setText(data.get(position).name);
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.reason)
            TextView reason;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(NameValue item, int position);
    }

    public List<NameValue> initData() {
        List<NameValue> data = new ArrayList<>();
        data.add(new NameValue("旺铺已出租", "1"));
        data.add(new NameValue("我不想寻租了", "2"));
        return data;
    }
}
