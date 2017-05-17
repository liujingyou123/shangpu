package com.finance.winport.tab.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.image.Batman;
import com.finance.winport.tab.event.SelectImageEvent;
import com.finance.winport.view.imagepreview.ZoomImageView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xzw on 2017/5/12.
 * 头像浏览
 */

public class ScanHeadImageFragment extends BaseFragment {

    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.img)
    ZoomImageView img;
    String headUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            headUrl = getArguments().getString("head");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = LayoutInflater.from(context).inflate(R.layout.fragment_scan_head_image, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;
    }

    private void initView() {
        tvFocusHouse.setText("头像");
        tvFocusRight.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.icon_camera, 0, 0, 0);
        Batman.getInstance().fromNet(headUrl, img);
    }


    @OnClick(R.id.imv_focus_house_back)
    public void onBackClicked() {
        handleBack();
    }


    @OnClick(R.id.tv_focus_right)
    public void onRightClicked() {
        EventBus.getDefault().post(new SelectImageEvent());
        handleBack();
    }
}
