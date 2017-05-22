package com.finance.winport.trade;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.dialog.CommentDialog;
import com.finance.winport.trade.adapter.TradeCircleDetailAdapter;
import com.finance.winport.trade.model.TradeDetailResponse;
import com.finance.winport.trade.presenter.TradeCircleDetailPresener;
import com.finance.winport.trade.view.ITradeDetailView;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;
import com.finance.winport.view.refreshview.XPtrFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TradeCircleDetailActivity extends BaseActivity implements ITradeDetailView {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.btn_comment)
    TextView btnComment;
    @BindView(R.id.tv_praise_num)
    TextView tvPraiseNum;
    @BindView(R.id.tv_comment_num)
    TextView tvCommentNum;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;

    CommentDialog commentDialog;
    TradeCircleDetailAdapter adapter;
    @BindView(R.id.xpfl)
    XPtrFrameLayout xpfl;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;

    private TradeCircleDetailPresener mPresenter;
    private String topicId;
    private TradeDetailResponse.DataBean mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_communit_detail);
        ButterKnife.bind(this);

        initView();
        initData();
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent != null) {
            topicId = getIntent().getStringExtra("topicId");
        }

        if (mPresenter == null) {
            mPresenter = new TradeCircleDetailPresener(this);
        }

        mPresenter.getTradeCircleDetail(topicId);
    }

    private void initView() {
        tvFocusHouse.setText("详情");
        rv.setLayoutManager(new LinearLayoutManager(this));
        xpfl.setMode(PtrFrameLayout.Mode.NONE);
        xpfl.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
            }
        });
    }

    private void initData() {
        commentDialog = new CommentDialog(this);
        commentDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                String content = commentDialog.getContent();
                if (TextUtils.isEmpty(content)) {
                    btnComment.setText("请输入评论的内容");
                    btnComment.setTextColor(Color.parseColor("#999999"));
                } else {
                    btnComment.setText(commentDialog.getContent());
                    btnComment.setTextColor(Color.parseColor("#333333"));
                }
            }
        });

        commentDialog.setOkDoneListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.commentTopic(topicId, commentDialog.getContent());
            }
        });
    }

    @OnClick({R.id.imv_focus_house_back, R.id.btn_comment, R.id.tv_praise_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.btn_comment:
                commentDialog.show();
                break;
            case R.id.tv_praise_num:
                if (tvPraiseNum.isSelected()) {  //取消点赞
                    mPresenter.zanTopic(topicId);
                } else {
                    mPresenter.cancelzanTopic(topicId);
                }
                break;
        }
    }

    @Override
    public void showTradeDetail(TradeDetailResponse response) {
        this.mData = response.getData();
        if (response.getData().getLikeStatus() == 0) {
            tvPraiseNum.setSelected(false);
        } else {
            tvPraiseNum.setSelected(true);
        }
        tvPraiseNum.setText(response.getData().getPraiseNumber() + "");
        tvCommentNum.setText(response.getData().getCommentNumber() + "");

        adapter = new TradeCircleDetailAdapter(this, mData);
        rv.setAdapter(adapter);
    }

    @Override
    public void zanTopic(boolean isSuccess, String topId) {
        if (isSuccess) {
            tvPraiseNum.setSelected(true);
            mData.setPraiseNumber(mData.getPraiseNumber() + 1);
            tvPraiseNum.setText(mData.getPraiseNumber() + "");
        }
    }

    @Override
    public void cancelTopic(boolean isSuccess, String topId) {
        if (isSuccess) {
            tvPraiseNum.setSelected(false);
            mData.setPraiseNumber(mData.getPraiseNumber() - 1);
            tvPraiseNum.setText(mData.getPraiseNumber() + "");
        }
    }

    @Override
    public void commentTopic(boolean isSuccess) {
        commentDialog.dismiss();
        if (isSuccess) {
            mPresenter.getTradeCircleDetail(topicId);
        }
    }

    @Override
    public void deleteTopic(boolean isSuccess, String topId) {
        if (isSuccess) {
            finish();
        }
    }
}
