package com.finance.winport.trade;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.dialog.ShareDialog;
import com.finance.winport.dialog.TradeBottomPopup;
import com.finance.winport.trade.model.TradeDetails;
import com.finance.winport.trade.presenter.TradeSubDetailsPresenter;
import com.finance.winport.trade.view.ITradeSubDetailsView;
import com.finance.winport.util.H5Util;
import com.finance.winport.util.TextViewUtil;
import com.finance.winport.util.ToastUtil;
import com.finance.winport.view.CustomWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 资讯详情...
 */
public class NewsDetailsFragment extends BaseFragment implements ITradeSubDetailsView {

    private final int DURATION = 200;
    Unbinder unbinder;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.title)
    TextView tvTitle;
    @BindView(R.id.type)
    TextView tvType;
    @BindView(R.id.from)
    TextView from;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.scan_count)
    TextView scanCount;
    @BindView(R.id.web)
    CustomWebView web;
    @BindView(R.id.share)
    ImageView share;
    @BindView(R.id.praise)
    TextView praise;
    @BindView(R.id.down_praise)
    TextView downPraise;
    @BindView(R.id.bottom)
    LinearLayout bottom;
    String contentId;
    String title;
    TradeType type;
    TradeSubDetailsPresenter presenter;
    TradeDetails info;
    String content;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TradeSubDetailsPresenter(this);
        if (getArguments() != null) {
            contentId = getArguments().getString("id");
            title = getArguments().getString("title");
            type = (TradeType) getArguments().getSerializable("type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news_details, container, false);
        unbinder = ButterKnife.bind(this, root);
        initView();
        asyncData();
        return root;
    }

    private void asyncData() {
        presenter.getSubDetails(contentId, true);
    }


    @Override
    public void setDetails(TradeDetails info) {
        this.info = info;
        if (info != null) {
            web.loadData(info.data.content, "text/html; charset=UTF-8", null);
            tvTitle.setText(info.data.title);
            if (info.data.tagList != null && info.data.tagList.size() > 0 && info.data.tagList.get(0) != null) {
                tvType.setText(info.data.tagList.get(0).tagName);
            }
            if (TextUtils.isEmpty(info.data.source)) {
                from.setVisibility(View.GONE);
            } else {
                from.setText(info.data.source);
                from.setVisibility(View.VISIBLE);
            }
            scanCount.setText(info.data.viewCount + "浏览");
            date.setText(info.data.dateTime);
            praise.setText(info.data.goodCount + "");
            downPraise.setText(info.data.badCount + "");
//            initContent(info.data.content);
        }

    }

//    private void initContent(String contentStr) {
//        content = Html.fromHtml(contentStr).toString().replaceAll("\\n", "");
//        if (content.length() >= 30) {
//            content = content.substring(0, 30) + "...";
//        } else {
//            content = content + "...";
//        }
//    }

    @Override
    public void praise(boolean success) {
        if (info != null) {
            info.data.goodCount++;
            praise.setText(info.data.goodCount + "");
            praise.setEnabled(false);
        }
    }

    @Override
    public void downPraise(boolean success) {
        if (info != null) {
            info.data.badCount++;
            downPraise.setText(info.data.badCount + "");
            downPraise.setEnabled(false);
        }
    }

    @Override
    public void showError(String errorMsg) {
        ToastUtil.show(context, errorMsg);
    }


    private void initView() {
        tvFocusHouse.setText(title + "详情");
        web.setOnScrollListener(new CustomWebView.OnScrollListener() {
            @Override
            public void onScroll(int deltaY) {
//                Log.d("Tag", "onScroll");
                if (deltaY < 0) {
                    showBottomView();
                } else if (deltaY > 0) {
                    hideBottomView();
                }
            }

            @Override
            public void onScrollIdle(int scrollY) {
            }

            @Override
            public void onScrollEdge(int deltaY) {
//                Log.d("Tag", "onScrollEdge");
                showBottomView();
            }
        });

    }

    ObjectAnimator showAnim;
    ObjectAnimator hideAnim;

    private void showBottomView() {
        if (bottom.getVisibility() == View.GONE) {
            bottom.setVisibility(View.VISIBLE);
            if (showAnim != null && showAnim.isRunning()) return;
            showAnim = ObjectAnimator.ofFloat(bottom, "translationY", bottom.getHeight(), 0);
            showAnim.setDuration(DURATION);
            showAnim.start();
        } else if (hideAnim != null && hideAnim.isRunning()) {
            hideAnim = null;
            bottom.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showBottomView();
                }
            }, DURATION);
        }

    }


    private void hideBottomView() {
        if (bottom.getVisibility() == View.VISIBLE) {
            if (hideAnim != null && hideAnim.isRunning()) return;
            hideAnim = ObjectAnimator.ofFloat(bottom, "translationY", 0, bottom.getHeight());
            hideAnim.setDuration(DURATION);
            hideAnim.start();
            hideAnim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    bottom.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.imv_focus_house_back, R.id.share, R.id.praise, R.id.down_praise})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                handleBack();
                break;
            case R.id.share:
                share();
                break;
            case R.id.praise:
                TextViewUtil.startScaleAnim(view);
                if (info != null) {
                    presenter.praise(info.data.contentId);
                }
                break;
            case R.id.down_praise:
                TextViewUtil.startScaleAnim(view);
                if (info != null) {
                    presenter.downPraise(info.data.contentId);
                }
                break;
        }
    }


    ShareDialog shareDialog;

    private void share() {
        if (info != null) {
            if (shareDialog == null) {
                shareDialog = new ShareDialog(context);
            }
            shareDialog.setDes(info.data.desc);
            shareDialog.setTitle(info.data.title);
            shareDialog.setImage(info.data.image);
            shareDialog.setUrl(H5Util.getIpTradeDetail(info.data.contentId));
            shareDialog.show();
        }
    }
}
