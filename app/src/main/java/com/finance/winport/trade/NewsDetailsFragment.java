package com.finance.winport.trade;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.dialog.ShareDialog;
import com.finance.winport.trade.model.TradeDetails;
import com.finance.winport.trade.presenter.TradeSubDetailsPresenter;
import com.finance.winport.trade.view.ITradeSubDetailsView;
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
        presenter.getSubDetails("41", true);
    }


    @Override
    public void setDetails(TradeDetails info) {
        this.info = info;
        if (info != null) {
//            String data = "<body>\n" +
//                    "  \n" +
//                    "  <div id =\"flash_container\" class=\"noPrint\">    \n" +
//                    "  </div>\n" +
//                    "  \n" +
//                    "  <div class=\"container-fluid\">  \n" +
//                    "      \n" +
//                    "<div class=\"row-fluid article_row_fluid\">\n" +
//                    "    <div class=\"span8 contant article_detail_bg\">\n" +
//                    "        <h1>阿里要在日本再造一个支付宝</h1>\n" +
//                    "        <div class=\"article_meta\">\n" +
//                    "            <div style=\"margin-bottom: 5px;\">\n" +
//                    "            <span class=\"timestamp\">时间&nbsp;2017-08-16 12:48:36\n" +
//                    "            </span>\n" +
//                    "            <span class=\"from\">\n" +
//                    "                <i class=\"icon-globe\"></i>\n" +
//                    "                    <a class=\"cut cut28 from\" href=\"/sites/MJNVfm\" target=\"_blank\">36氪\n" +
//                    "                    </a>\n" +
//                    "            </span>\n" +
//                    "            </div>\n" +
//                    "            <div class=\"source\">\n" +
//                    "                <i style=\"float:left;\">原文</i>&nbsp; \n" +
//                    "                <a class=\"cut cut70\" href=\"http://36kr.com/p/5088324.html?utm_source=tuicool&amp;utm_medium=referral\" style=\"display:inline-block;\">http://36kr.com/p/5088324.html</a>\n" +
//                    "            </div>\n" +
//                    "            <div>\n" +
//                    "                <span>主题</span>\n" +
//                    "                <a href=\"/topics/10100004\" target='_blank' >\n" +
//                    "                    <span class=\"new-label\">支付宝</span>\n" +
//                    "                </a>\n" +
//                    "            </div>\n" +
//                    "        </div>\n" +
//                    "        <div class=\"article_body\" id=\"nei\">\n" +
//                    "            <p> <img src=\"http://img0.tuicool.com/AVNbi2V.jpg!web\" class=\"alignCenter\" /> </p> \n" +
//                    "<p> 本文 <a href=\"http://www.yicai.com/news/5331457.html\" target=\"_blank\" rel=\"nofollow,noindex\">来自第一财经</a> ，36氪经授权转载。 </p> \n" +
//                    "<p>对于访日的中国游客来说，在日本店铺使用支付宝进行结算并不算是一件新鲜事，但是阿里显然不满足于此，它希望日本当地的住民也能享受到手机支付的便捷。</p> \n" +
//                    "<p>据日经新闻报道，阿里巴巴集团将于2018年春季在日本推出基于智能手机的电子结算服务。利用智能手机应用程序中存放的现金即可购物。将面向日本人提供与中国“支付宝”同样构造的服务，力争3年内赢得1千万用户。</p> \n" +
//                    "<p>新服务将由阿里巴巴旗下的金融公司蚂蚁金服日本分公司提供。利用店铺生成、或消费者的智能手机应用程序显示的二维码进行结算，二维码写入了支付账户和转账账户等的信息。</p> \n" +
//                    "<p>支付宝目前可以在罗森、电器店和百货商场等3万多家日本国内店铺使用，预计到2017年底，支付宝加盟店铺将增加至约5万家。 阿里巴巴在日本推出的新手机支付服务将以这些店铺为中心，充分利用这个因访日游客增加而扩张起来的支付宝加盟网络。除了结算以外，还将逐步增加生活相关的功能，列如实现电影票的预订和购买等。</p> \n" +
//                    "<p>由于支付宝只服务于拥有中国银行账户的用户，因此新的手机结算服务将使用新品牌名称。服务推出后，日本用户将也能在中国境内利用此结算服务。日本每年访问中国的人数大约为250万人次，出差和旅行时的便利性将随之提高。</p> \n" +
//                    "<p>对于以现金结算为主流的日本市场来说，手机支付市场的增长空间十分巨大。据野村综合研究所的调查，日本国内2017年电子货币的结算市场仅为5.6万亿日元，还有很大的增长空间。</p>\n" +
//                    "        </div>\n" +
//                    "        <div class=\"article_social\">\n" +
//                    "         <div class=\"article_like\">\n" +
//                    "    <div class=\"circle circle-like\" id=\"my_zan\" data_id=\"6BNZV3n\">  </div>\n" +
//                    "\n" +
//                    "</div>\n" +
//                    "</body>";
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
        }

    }

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
            public void onScroll(int scrollY) {
                hideBottomView();
            }

            @Override
            public void onScrollIdle(int scrollY) {
                showBottomView();
            }
        });
    }

    private void showBottomView() {
        bottom.setVisibility(View.VISIBLE);
    }

    private void hideBottomView() {
        bottom.setVisibility(View.GONE);
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
        if (shareDialog == null) {
            shareDialog = new ShareDialog(context);
        }
//        shareDialog.setDes();
//        shareDialog.setTitle();
//        shareDialog.setImage();
//        shareDialog.setUrl();
        shareDialog.show();
    }
}
