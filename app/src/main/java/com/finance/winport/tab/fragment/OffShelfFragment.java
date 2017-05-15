package com.finance.winport.tab.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xzw on 2017/5/12.
 * 下架旺铺
 */

public class OffShelfFragment extends BaseFragment {

    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.mListView)
    ListView mListView;
    @BindView(R.id.other)
    EditText other;
    @BindView(R.id.clear)
    ImageView clear;
    @BindView(R.id.remain)
    TextView remain;
    @BindView(R.id.rl_other)
    RelativeLayout rlOther;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = LayoutInflater.from(context).inflate(R.layout.fragment_off_shelf, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;
    }

    private void initView() {
        tvFocusHouse.setText("下架旺铺");
        initListView();
    }

    private void initListView() {
        mListView.setAdapter(new OffShelfAdapter(context, initData()));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getAdapter() != null && parent.getAdapter() instanceof OffShelfAdapter) {
                    OffShelfAdapter adapter = (OffShelfAdapter) parent.getAdapter();
                    adapter.setSelection(position);
                    String s = (String) adapter.getItem(position);
                    if (TextUtils.equals("其他", s)) {
                        rlOther.setVisibility(View.VISIBLE);
                    } else {
                        rlOther.setVisibility(View.GONE);
                    }
                }
            }
        });
    }


    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        handleBack();
    }

    @OnClick({R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm:

                break;
        }
    }


    @OnClick(R.id.clear)
    public void onClearClicked() {
        other.setText("");
    }

    class OffShelfAdapter extends BaseAdapter {
        List<String> data;
        Context context;
        int selection = -1;

        public OffShelfAdapter(Context context, List<String> data) {
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
            holder.reason.setText(data.get(position));
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

    public List<String> initData() {
        List<String> data = new ArrayList<>();
        data.add("旺铺已出租");
        data.add("我不想寻租了");
        data.add("其他");
        return data;
    }
}
