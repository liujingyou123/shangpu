package com.finance.winport.tab.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.finance.winport.base.BaseResponse;
import com.finance.winport.dialog.LoadingDialog;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.tab.net.PersonManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xzw on 2017/5/12.
 * 下架旺铺
 */

public class OffShelfFragment extends BaseFragment {
    private static final int COUNT = 200;
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
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
    private String shopId;
    private LoadingDialog loading;
    private String tagId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            shopId = getArguments().getString("shopId");
        }
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
        loading = new LoadingDialog(context);
        confirm.setEnabled(false);
        tvFocusHouse.setText("下架旺铺");
        clear.setVisibility(View.GONE);
        remain.setText("0" + "/" + COUNT + "字");
        other.setFilters(new InputFilter[]{new InputFilter.LengthFilter(COUNT)});
        other.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int rm = s.length();
                if (s.length() > 0) {
                    clear.setVisibility(View.VISIBLE);
                } else {
                    clear.setVisibility(View.GONE);
                }
                if (rm > 0) {
                    remain.setText(rm + "/" + COUNT + "字");
                } else {
                    remain.setText("0" + "/" + COUNT + "字");
                }
            }
        });
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
                    tagId = position + "";
                    confirm.setEnabled(true);
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
                offShelfSHop();
                break;
        }
    }

    public void pushFragment(BaseFragment fragment) {
        String tag = fragment.getClass().getName();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.activity_open_enter, R.anim.activity_open_exit, R.anim.activity_close_enter, R.anim.activity_close_exit);
        ft.replace(R.id.rl_fragment_content, fragment, tag);
        ft.addToBackStack(tag);
        ft.commit();
    }

    //下架商铺
    private void offShelfSHop() {
        loading.show();
        HashMap<String, Object> params = new HashMap<>();
        params.put("shopId", shopId);
        params.put("tagId", tagId);
        params.put("undoContent", other.getText().toString());
        PersonManager.getInstance().offShelfSHop(params, new NetworkCallback<BaseResponse>() {
            @Override
            public void success(BaseResponse response) {
                if (getView() == null) return;
                loading.dismiss();
                pushFragment(new OffShelfResultFragment());
            }

            @Override
            public void failure(Throwable throwable) {
                if (getView() == null) return;
                loading.dismiss();
            }
        });
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
