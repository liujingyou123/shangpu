package com.finance.winport.trade;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.permission.PermissionsManager;
import com.finance.winport.permission.PermissionsResultAction;
import com.finance.winport.trade.adapter.ChoicePhotoAdapter;
import com.finance.winport.util.ToastUtil;
import com.finance.winport.view.picker.Picker;
import com.finance.winport.view.picker.engine.GlideEngine;
import com.finance.winport.view.picker.utils.PicturePickerUtils;
import com.finance.winport.view.tagview.TagCloudLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/5/11.
 * 发布帖子
 */

public class EditNoteActivity extends BaseActivity {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.et_else)
    EditText etElse;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_photo_num)
    TextView tvPhotoNum;
    @BindView(R.id.gv_photos)
    TagCloudLayout gvPhotos;

    private int textSize;
    private ChoicePhotoAdapter mAdapter;
    private List<String> mData = new ArrayList<>();
    private int REQUEST_CODE_PHOTO = 200;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editnote);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvFocusHouse.setText("发布帖子");
        etElse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    textSize = s.length();
                }
                tvNum.setText(textSize + "/200字");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if (mAdapter == null) {
            mAdapter = new ChoicePhotoAdapter(this, mData);
            gvPhotos.setAdapter(mAdapter);
        }

        gvPhotos.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                if (mAdapter.isAddType(position)) {
                    selectImage(mAdapter.getRelCount());
                }
            }
        });


    }

    @OnClick({R.id.imv_focus_house_back, R.id.btn_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.btn_done:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_PHOTO) {
            List<String> mSelected = PicturePickerUtils.obtainResult(this.getContentResolver(), data);
            mAdapter.addItems(mSelected);
        }
    }

    private void selectImage(final int count) {
        String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission
                .WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this, permissions,
                new PermissionsResultAction() {
                    @Override
                    public void onGranted() {
                        Picker.from(EditNoteActivity.this)
                                .count(count)
                                .enableCamera(true)
                                .setEngine(new GlideEngine())
                                .forResult(REQUEST_CODE_PHOTO);
                    }

                    @Override
                    public void onDenied(String permission) {
                    }


                });

    }
}
