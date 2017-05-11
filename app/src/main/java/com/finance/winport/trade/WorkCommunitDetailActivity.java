package com.finance.winport.trade;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.dialog.CommentDialog;
import com.finance.winport.trade.adapter.WorkCommunitDetailListAdapter;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;
import com.finance.winport.view.refreshview.XPtrFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkCommunitDetailActivity extends BaseActivity {

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
    WorkCommunitDetailListAdapter adapter;
    List<String> datas = new ArrayList<>();
    @BindView(R.id.xpfl)
    XPtrFrameLayout xpfl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_communit_detail);
        ButterKnife.bind(this);

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WorkCommunitDetailListAdapter(this, datas);
        rv.setAdapter(adapter);

        xpfl.setPullToRefresh(false);
        xpfl.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                System.out.println("上拉加载");
                //刷新结束
//                frame.refreshComplete();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                System.out.println("下拉刷新");
                //刷新结束
//                frame.refreshComplete();
            }
        });

        initData();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            datas.add("" + i);
        }

        adapter.notifyDataSetChanged();

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
                tvPraiseNum.setSelected(!tvPraiseNum.isSelected());
                break;
        }
    }

}
