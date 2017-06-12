package com.finance.winport.trade;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.MainActivity;
import com.finance.winport.R;
import com.finance.winport.account.LoginActivity;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.dialog.CommentDialog;
import com.finance.winport.dialog.NoticeDelDialog;
import com.finance.winport.trade.adapter.TradeCircleDetailAdapter;
import com.finance.winport.trade.model.CommentResponse;
import com.finance.winport.trade.model.EventBusCommentNum;
import com.finance.winport.trade.model.TradeDetailResponse;
import com.finance.winport.trade.presenter.TradeCircleDetailPresener;
import com.finance.winport.trade.view.ITradeDetailView;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.util.SpUtil;
import com.finance.winport.util.ToastUtil;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;
import com.finance.winport.view.refreshview.XPtrFrameLayout;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

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
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;

    CommentDialog commentDialog;
    TradeCircleDetailAdapter adapter;
    @BindView(R.id.xpfl)
    XPtrFrameLayout xpfl;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.imv_right)
    ImageView imvRight;

    private TradeCircleDetailPresener mPresenter;
    private String topicId;
    private TradeDetailResponse.DataBean mData;
    private int pageNumber = 1;
    private String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_communit_detail);
        ButterKnife.bind(this);
        initData();

        initView();
        getData();
    }

    private void initData() {
        String fromType = null;
        Intent intent = getIntent();
        if (intent != null) {
            topicId = intent.getStringExtra("topicId");
            from = intent.getStringExtra("from");
            fromType = intent.getStringExtra("fromType");
        }

        if (mPresenter == null) {
            mPresenter = new TradeCircleDetailPresener(this);
        }

//        if (!TextUtils.isEmpty(fromType)) {
//            int commentNum = SpUtil.getInstance().getIntData("commentNum", 0);
//            --commentNum;
//            SpUtil.getInstance().setIntData("commentNum", commentNum);
//            EventBus.getDefault().post(new EventBusCommentNum());
//        }
    }

    private void getData() {

        mPresenter.getTradeCircleDetail(topicId);
        mPresenter.getComment(topicId, pageNumber + "");
    }

    private void initView() {
        tvFocusHouse.setText("详情");
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TradeCircleDetailAdapter(this, topicId, mPresenter);
        rv.setAdapter(adapter);
        xpfl.setMode(PtrFrameLayout.Mode.BOTH);
        xpfl.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                pageNumber++;
                mPresenter.getCommentMore(topicId, pageNumber + "");
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNumber = 1;
                mPresenter.getComment(topicId, pageNumber + "");
            }
        });

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
                MobclickAgent.onEvent(context, "post_comment_release");
                mPresenter.commentTopic(topicId, commentDialog.getContent());
            }
        });
    }

    @OnClick({R.id.imv_focus_house_back, R.id.btn_comment, R.id.tv_praise_num, R.id.imv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:

                if (!TextUtils.isEmpty(from)) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("tab", MainActivity.BUSINESS);
                    startActivity(intent);
                }
                finish();
                break;
            case R.id.btn_comment:
                MobclickAgent.onEvent(context, "post_comment");
                if (SharedPrefsUtil.getUserInfo() != null) {
                    commentDialog.show();
                } else {
                    Intent intent1 = new Intent(this, LoginActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.tv_praise_num:

                if (SharedPrefsUtil.getUserInfo() != null) {
                    if (tvPraiseNum.isSelected()) {  //取消点赞
                        mPresenter.cancelzanTopic(topicId);
                    } else {
                        mPresenter.zanTopic(topicId);
                    }
                } else {
                    Intent intent1 = new Intent(this, LoginActivity.class);
                    startActivity(intent1);
                }

                break;
            case R.id.imv_right:
                NoticeDelDialog dialog = new NoticeDelDialog(this);
                dialog.setOkClickListener(new NoticeDelDialog.OnPreClickListner() {
                    @Override
                    public void onClick() {
                        mPresenter.deleteTopic(topicId);
                    }
                });
                dialog.show();
                break;
        }
    }

    @Override
    public void showTradeDetail(TradeDetailResponse response) {
        llBottom.setVisibility(View.VISIBLE);
        xpfl.setVisibility(View.VISIBLE);
        llEmpty.setVisibility(View.GONE);
        this.mData = response.getData();
        if (response.getData().getLikeStatus() == 0) {
            tvPraiseNum.setSelected(false);
        } else {
            tvPraiseNum.setSelected(true);
        }

        if ("1".equals(mData.getCanBeDelete())) {
            imvRight.setVisibility(View.VISIBLE);
        } else {
            imvRight.setVisibility(View.GONE);
        }
        tvPraiseNum.setText(response.getData().getPraiseNumber() + "");
        tvCommentNum.setText(response.getData().getCommentNumber() + "");
        adapter.setTraddeDetail(response.getData());
    }

    @Override
    public void zanTopic(boolean isSuccess, String topId) {
        if (isSuccess) {
            ToastUtil.show(this, "点赞成功");
            tvPraiseNum.setSelected(true);
            mData.setPraiseNumber(mData.getPraiseNumber() + 1);
            tvPraiseNum.setText(mData.getPraiseNumber() + "");
        }
    }

    @Override
    public void cancelTopic(boolean isSuccess, String topId) {
        if (isSuccess) {
            ToastUtil.show(this, "已取消点赞");
            tvPraiseNum.setSelected(false);
            mData.setPraiseNumber(mData.getPraiseNumber() - 1);
            tvPraiseNum.setText(mData.getPraiseNumber() + "");
        }
    }

    @Override
    public void commentTopic(boolean isSuccess) {
        commentDialog.setContent("");
        commentDialog.dismiss();
        if (isSuccess) {
            ToastUtil.show(this, "评论成功");
            pageNumber = 1;
            mPresenter.getComment(topicId, pageNumber + "");

        } else {
            ToastUtil.show(this, "评论失败，请重试");
        }
    }

    @Override
    public void deleteTopic(boolean isSuccess, String topId) {
        if (isSuccess) {
            finish();
        }
    }

    @Override
    public void deleteComment(boolean isSuccess, String topId, String commentId) {
        if (isSuccess) {
            ToastUtil.show(this, "删除评论成功");
            List<CommentResponse.DataBean.Comment> comments = adapter.getComments();
            for (int i = 0; i < comments.size(); i++) {
                CommentResponse.DataBean.Comment comment = comments.get(i);
                if (commentId.equals(comment.getId())) {
                    comments.remove(comment);
                    adapter.notifyDataSetChanged();
                    break;
                }
            }
        }
    }

    @Override
    public void showComments(CommentResponse response) {
        adapter.setComments(response.getData().getData());
        xpfl.refreshComplete();
        if (response.getData().getData().size() < 10) {
            xpfl.setMode(PtrFrameLayout.Mode.REFRESH);
        } else {
            xpfl.setMode(PtrFrameLayout.Mode.BOTH);
        }
    }

    @Override
    public void showCommentsMore(CommentResponse response) {
        adapter.setMoreComments(response.getData().getData());
        xpfl.refreshComplete();

        if (response.getData().getData().size() < 10) {
            xpfl.setMode(PtrFrameLayout.Mode.REFRESH);
        } else {
            xpfl.setMode(PtrFrameLayout.Mode.BOTH);
        }
    }

    @Override
    public void showError(TradeDetailResponse response) {
        if (response != null && !response.isSuccess() && "20006".equals(response.getErrCode())) {
            llBottom.setVisibility(View.GONE);
            xpfl.setVisibility(View.GONE);
            llEmpty.setVisibility(View.VISIBLE);
            imvRight.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.clearData();
        }
        super.onDestroy();

    }
}
