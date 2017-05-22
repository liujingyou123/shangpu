package com.finance.winport.trade;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.finance.winport.R;
import com.finance.winport.aliyunoss.AliOss;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.net.LoadingNetSubscriber;
import com.finance.winport.permission.PermissionsManager;
import com.finance.winport.permission.PermissionsResultAction;
import com.finance.winport.trade.adapter.ChoicePhotoAdapter;
import com.finance.winport.trade.api.TradeService;
import com.finance.winport.trade.model.PublicTopic;
import com.finance.winport.util.ToastUtil;
import com.finance.winport.util.ToolsUtil;
import com.finance.winport.view.picker.Picker;
import com.finance.winport.view.picker.engine.GlideEngine;
import com.finance.winport.view.picker.utils.PicturePickerUtils;
import com.finance.winport.view.tagview.TagCloudLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
    @BindView(R.id.et_title)
    EditText etTitle;

    private int textSize;
    private ChoicePhotoAdapter mAdapter;
    private List<String> mData = new ArrayList<>();
    private int REQUEST_CODE_PHOTO = 200;

    private PublicTopic mPublicTopic = new PublicTopic();

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
                    selectImage(mAdapter.getLastCount());
                }
            }
        });

        mAdapter.setOnRemoveListener(new ChoicePhotoAdapter.OnRemoveListener() {
            @Override
            public void onRemoveItem(int position) {
                tvPhotoNum.setText("选择图片(" + mAdapter.getRelCount() + "/9)");
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
                if (mData == null || mData.size() == 0) {
                    publishTopic(null);
                } else {
                    uploadImage();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_PHOTO) {
            List<String> mSelected = PicturePickerUtils.obtainResult(this.getContentResolver(), data);
            mAdapter.addItems(mSelected);

            tvPhotoNum.setText("选择图片(" + mAdapter.getRelCount() + "/9)");
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

    private void uploadImage() {
        Observable.from(mData).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return AliOss.getInstance().putObjectFromByteArray(AliOss.DIR_SHOP_TOPIC, s);
            }
        }).collect(new Func0<List<String>>() {
            @Override
            public List<String> call() {
                return new ArrayList<String>();
            }
        }, new Action2<List<String>, String>() {
            @Override
            public void call(List<String> strings, String s) {
                if (strings != null) {
                    strings.add(s);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        publishTopic(strings);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        ToastUtil.show(EditNoteActivity.this, "上传图片失败!");
                    }
                });
    }

    private void publishTopic(List<String> images) {
        if (images != null) {
            mPublicTopic.imageList = images;
        }

        if (etTitle.getText() == null || TextUtils.isEmpty(etTitle.getText().toString())) {
            ToastUtil.show(this, "请输入帖子标题");
            return;
        }

        mPublicTopic.title = etTitle.getText().toString();
        if (etElse.getText() == null || TextUtils.isEmpty(etElse.getText().toString())) {
            ToastUtil.show(this, "请输入帖子内容");
            return;
        }
        mPublicTopic.content = etTitle.getText().toString();


        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).publishTopic(mPublicTopic), new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {

            }
        });
    }

}
