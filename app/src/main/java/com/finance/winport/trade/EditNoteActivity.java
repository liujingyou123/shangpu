package com.finance.winport.trade;

import android.Manifest;
import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.Transition;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.aliyunoss.AliOss;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.dialog.NoticeDialog;
import com.finance.winport.net.LoadingNetSubscriber;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.permission.PermissionsManager;
import com.finance.winport.permission.PermissionsResultAction;
import com.finance.winport.trade.adapter.ChoicePhotoAdapter;
import com.finance.winport.trade.api.TradeService;
import com.finance.winport.trade.model.EventBusCircleData;
import com.finance.winport.trade.model.PublicTopic;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.util.ToastUtil;
import com.finance.winport.util.ToolsUtil;
import com.finance.winport.view.imagepreview.ImagePreviewActivity;
import com.finance.winport.view.picker.Picker;
import com.finance.winport.view.picker.engine.GlideEngine;
import com.finance.winport.view.picker.utils.PicturePickerUtils;
import com.finance.winport.view.tagview.TagCloudLayout;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func1;

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
    @BindView(R.id.btn_done)
    TextView btnDone;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.post_bottom)
    RelativeLayout postBottom;
    @BindView(R.id.post_info)
    LinearLayout postInfo;
    @BindView(R.id.container)
    FrameLayout container;

    private int textSize;
    private ChoicePhotoAdapter mAdapter;
    private List<String> mData = new ArrayList<>();
    private int REQUEST_CODE_PHOTO = 200;

    private PublicTopic mPublicTopic = new PublicTopic();
    private Subscription mSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editnote);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvFocusHouse.setText("发布帖子");
        tvFocusRight.setText("发帖秘籍");
        initLayoutAnimation();
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

        if (SharedPrefsUtil.getUserInfo().data.isNew == 1 && SharedPrefsUtil.isFirstPost()) {//新用户 第一次发帖
            SharedPrefsUtil.setFirstPost(false);
            //展示发帖秘籍
            postInfo.setVisibility(View.VISIBLE);
        }

        if (mAdapter == null) {
            mAdapter = new ChoicePhotoAdapter(this, mData);
            gvPhotos.setAdapter(mAdapter);
        }

        gvPhotos.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                if (mAdapter.isAddType(position)) {
                    selectImage(mAdapter.getLastCount());
                } else {
                    ArrayList<String> strings = new ArrayList<String>();
                    strings.addAll(mAdapter.getListData());
                    Intent intents = new Intent(context, ImagePreviewActivity.class);
                    intents.putExtra("pics", strings);
                    intents.putExtra("index", position);
                    context.startActivity(intents);
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

    private void initLayoutAnimation() {
        layoutIn = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_in);
        layoutOut = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_out);
        animatorIn = ObjectAnimator.ofInt(postInfo, "backgroundColor", START, END).setDuration(250);
        animatorOut = ObjectAnimator.ofInt(postInfo, "backgroundColor", END, START).setDuration(250);
        animatorIn.setEvaluator(new ArgbEvaluator());
        animatorOut.setEvaluator(new ArgbEvaluator());
    }

    @OnClick({R.id.imv_focus_house_back, R.id.btn_done, R.id.tv_focus_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                if (checkWithData()) {
                    showAlert();
                } else {
                    finish();
                }
                break;
            case R.id.tv_focus_right:
                MobclickAgent.onEvent(context,"circle_publish_guide");
                showPostInfo();
                break;
            case R.id.btn_done:
                MobclickAgent.onEvent(context, "circle_publish_release");
                if (checkAndSetData()) {
                    btnDone.setEnabled(false);
                    if (mAdapter.getListData() == null || mAdapter.getListData().size() == 0) {
                        publishTopic();
                    } else {
                        uploadImage();
                    }
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
        if (checkAndSetData()) {
            mSubscription = Observable.from(mAdapter.getListData()).map(new Func1<String, String>() {
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
            }).flatMap(new Func1<List<String>, Observable<BaseResponse>>() {
                @Override
                public Observable<BaseResponse> call(List<String> strings) {
                    if (strings != null) {
                        mPublicTopic.imageList = strings;
                    }
                    return ToolsUtil.createService(TradeService.class).publishTopic(mPublicTopic);
                }
            }).compose(ToolsUtil.<BaseResponse>applayScheduers()).subscribe(new LoadingNetSubscriber<BaseResponse>() {
                @Override
                public void response(BaseResponse response) {
                    btnDone.setEnabled(true);
                    if (response.isSuccess()) {
                        EventBusCircleData param = new EventBusCircleData();
                        param.canGetData = true;
                        EventBus.getDefault().post(param);
                        ToastUtil.show(EditNoteActivity.this, "发布成功");
                        EditNoteActivity.this.finish();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    btnDone.setEnabled(true);
                }

            });
        }


    }


    private boolean checkAndSetData() {
        if (etTitle.getText() == null || TextUtils.isEmpty(etTitle.getText().toString())) {
            ToastUtil.show(this, "请输入帖子标题");
            return false;
        }

        mPublicTopic.title = etTitle.getText().toString();
        if ((etElse.getText() == null || TextUtils.isEmpty(etElse.getText().toString())) && (mAdapter.getListData() == null || mAdapter.getListData().size() == 0)) {
            ToastUtil.show(this, "请输入帖子内容");
            return false;
        }

        if (etElse.getText() != null && !TextUtils.isEmpty(etElse.getText().toString())) {
            mPublicTopic.content = etElse.getText().toString();
        }
        return true;
    }

    private boolean checkWithData() {
        if (!TextUtils.isEmpty(etTitle.getText().toString())) {
            return true;
        }
        if (!TextUtils.isEmpty(etElse.getText().toString()) || (mAdapter.getListData() != null && mAdapter.getListData().size() > 0)) {
            return true;
        }

        return false;
    }


    private void publishTopic() {
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).publishTopic(mPublicTopic), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                btnDone.setEnabled(true);
                if (response.isSuccess()) {
                    EventBusCircleData param = new EventBusCircleData();
                    param.canGetData = true;
                    EventBus.getDefault().post(param);

                    ToastUtil.show(EditNoteActivity.this, "发布成功");
                    EditNoteActivity.this.finish();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                btnDone.setEnabled(true);
                ToastUtil.show(EditNoteActivity.this, "发布失败，请重试");

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    private static final int START = 0x00000000;
    private static final int END = 0xe6000000;
    LayoutAnimationController layoutIn;
    LayoutAnimationController layoutOut;
    ObjectAnimator animatorIn;
    ObjectAnimator animatorOut;

    private void showPostInfo() {
        hideSoftKeyboard();
        if (animatorIn.isRunning()) return;
        postInfo.setLayoutAnimation(layoutIn);
        postInfo.setVisibility(View.VISIBLE);
        animatorIn.start();
        postInfo.startLayoutAnimation();
    }

    private void hideInfo() {
        if (animatorOut.isRunning()) return;
        postInfo.setLayoutAnimation(layoutOut);
        postInfo.startLayoutAnimation();
        animatorOut.start();
        animatorOut.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                postInfo.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @OnClick(R.id.close)
    public void onViewClicked() {
        hideInfo();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && postInfo.getVisibility() == View.VISIBLE) {
            hideInfo();
            return true;
        } else {
            if (checkWithData()) {
                showAlert();
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
    }

    private void showAlert() {
        final NoticeDialog alert = new NoticeDialog(context);
        alert.setMessage("是否退出本次编辑");
        alert.setOkClickListener(new NoticeDialog.OnPreClickListner() {
            @Override
            public void onClick() {
                finish();
            }
        });
        alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                alert.dismiss();
            }
        });
        alert.show();
    }
}
