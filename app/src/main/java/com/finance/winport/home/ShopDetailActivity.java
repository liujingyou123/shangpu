package com.finance.winport.home;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.dialog.NoticeDialog;
import com.finance.winport.dialog.ShareDialog;
import com.finance.winport.home.adapter.SupportTagAdapter;
import com.finance.winport.home.adapter.TagDetailAdapter;
import com.finance.winport.home.model.ShopDetail;
import com.finance.winport.home.presenter.ShopDetailPresenter;
import com.finance.winport.home.view.IShopDetailView;
import com.finance.winport.image.GlideImageLoader;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.PositionScrollView;
import com.finance.winport.view.ScrollTabView;
import com.finance.winport.view.home.NearShop;
import com.finance.winport.view.home.RateView;
import com.finance.winport.view.imagepreview.ImagePreviewActivity;
import com.finance.winport.view.tagview.TagCloudLayout;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/5/4.
 * 商铺详情
 */

public class ShopDetailActivity extends BaseActivity implements IShopDetailView {

    @BindView(R.id.sv_all)
    PositionScrollView svAll;
    @BindView(R.id.ll_near)
    LinearLayout llNear;
    @BindView(R.id.ll_linpu)
    LinearLayout llLinpu;
    @BindView(R.id.ll_menmianshuju)
    LinearLayout llMenmianshuju;
    @BindView(R.id.ll_jingyingfeiyong)
    LinearLayout llJingyingfeiyong;
    @BindView(R.id.ll_peitao)
    LinearLayout llPeitao;
    @BindView(R.id.view_deliver_space)
    View viewDeliverSpace;
    @BindView(R.id.tv_zhoubian)
    TextView tvZhoubian;
    @BindView(R.id.tv_linpu)
    TextView tvLinpu;
    @BindView(R.id.tv_mienmian)
    TextView tvMienmian;
    @BindView(R.id.tv_yingyufeiyong)
    TextView tvYingyufeiyong;
    @BindView(R.id.tv_peitaosheshi)
    TextView tvPeitaosheshi;
    @BindView(R.id.stv)
    ScrollTabView stv;
    @BindView(R.id.title_view)
    View titleView;
    @BindView(R.id.rl_select)
    View seletView;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_collection)
    TextView tvCollection;
    @BindView(R.id.img_banner)
    Banner bannerView;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_yuyue)
    TextView tvYuyue;
    @BindView(R.id.tv_call)
    TextView tvCall;
    @BindView(R.id.view_bottom)
    LinearLayout viewBottom;
    @BindView(R.id.view_bottom_line)
    View viewBottomLine;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_shop_address)
    TextView tvShopAddress;
    @BindView(R.id.tv_scan)
    TextView tvScan;
    @BindView(R.id.tv_lianxi)
    TextView tvLianxi;
    @BindView(R.id.tv_jiucuo)
    TextView tvJiucuo;
    @BindView(R.id.tag)
    TagCloudLayout tag;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_name_price)
    TextView tvNamePrice;
    @BindView(R.id.imv_change)
    ImageView imvChange;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.view_line_one)
    View viewLineOne;
    @BindView(R.id.tv_name_zhuanprice)
    TextView tvNameZhuanprice;
    @BindView(R.id.tv_zhuanprice)
    TextView tvZhuanprice;
    @BindView(R.id.view_line_two)
    View viewLineTwo;
    @BindView(R.id.tv_name_area)
    TextView tvNameArea;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.tv_shop_more)
    TextView tvShopMore;
    @BindView(R.id.imv_back)
    ImageView imvBack;
    @BindView(R.id.view_shop_one)
    NearShop viewShopOne;
    @BindView(R.id.view_shop_two)
    NearShop viewShopTwo;
    @BindView(R.id.view_shop_three)
    NearShop viewShopThree;
    @BindView(R.id.view_shop_four)
    NearShop viewShopFour;
    @BindView(R.id.view_shop_five)
    NearShop viewShopFive;
    @BindView(R.id.view_space_linpu)
    View viewSpaceLinpu;
    @BindView(R.id.tv_cenggao)
    TextView tvCenggao;
    @BindView(R.id.tv_miankuan)
    TextView tvMiankuan;
    @BindView(R.id.tv_jinshen)
    TextView tvJinshen;
    @BindView(R.id.tv_dianfei)
    TextView tvDianfei;
    @BindView(R.id.tv_shuifei)
    TextView tvShuifei;
    @BindView(R.id.tv_ranqi)
    TextView tvRanqi;
    @BindView(R.id.tv_wuye)
    TextView tvWuye;
    @BindView(R.id.gv_support)
    GridView gvSupport;
    @BindView(R.id.tg_view)
    TagCloudLayout tgView;
    @BindView(R.id.view_space_jingyingfanwei)
    View viewSpaceJingyingfanwei;
    @BindView(R.id.ll_jingyingfanwei)
    LinearLayout llJingyingfanwei;
    @BindView(R.id.ll_jingyingfeiyongone)
    LinearLayout llJingyingfeiyongone;
    //    @BindView(R.id.tv_dianfeitwo)
//    TextView tvDianfeitwo;
//    @BindView(R.id.view_space_feiyong_one)
//    View viewSpaceFeiyongOne;
//    @BindView(R.id.tv_shuifei_two)
//    TextView tvShuifeiTwo;
//    @BindView(R.id.view_space_feiyong_two)
//    View viewSpaceFeiyongTwo;
//    @BindView(R.id.tv_ranqi_two)
//    TextView tvRanqiTwo;
    @BindView(R.id.ll_jingyingfeiyongtwo)
    LinearLayout llJingyingfeiyongtwo;
    //    @BindView(R.id.rl_title_root)
//    RelativeLayout rlTitleRoot;
//    @BindView(R.id.tv_feiyongnotice)
//    TextView tvFeiyongnotice;
    @BindView(R.id.view_space_jingyingfeiyong)
    View viewSpaceJingyingfeiyong;

    private boolean isTouched = false;
    private ShareDialog shareDialog;
    private NoticeDialog mNoticeDialog;

    private UMShareListener mShareListener;
    private ShareAction mShareAction;
    private String shopId;
    private ShopDetailPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetail);
        ButterKnife.bind(this);
        initData();
        init();
        shareInit();

    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            shopId = intent.getStringExtra("shopId");
        }

        if (mPresenter == null) {
            mPresenter = new ShopDetailPresenter(this);
        }

        shopId = "4";
        mPresenter.getShopDetail(shopId);
    }

    private void init() {
        llTop.setAlpha(0);
        svAll.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        svAll.setFocusable(true);
        svAll.setFocusableInTouchMode(true);
        svAll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                isTouched = true;
                return false;
            }
        });
        svAll.setOnScrollChangedListener(new PositionScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                updateView(t);

                if (!isTouched) {
                    return;
                }
                String text = "init";
                if (isScrolled(llNear)) {
                    text = "周边";
                    stv.setSelectPosition(0);
                } else if (isScrolled(llLinpu)) {
                    text = "邻铺";
                    stv.setSelectPosition(1);
                } else if (isScrolled(llMenmianshuju)) {
                    text = "门面";
                    stv.setSelectPosition(2);
                } else if (isScrolled(llJingyingfanwei)) {
                    text = "范围";
                    stv.setSelectPosition(3);
                } else if (isScrolled(llJingyingfeiyong)) {
                    text = "费用";
                    stv.setSelectPosition(4);
                } else if (isScrolled(llPeitao)) {
                    text = "配套";
                    stv.setSelectPosition(5);
                }

                Log.e("cover is = ", text);

            }
        });

        stv.setOnSelectViewListener(new ScrollTabView.OnSelectViewListener() {
            @Override
            public void onSelectViewPosition(int position) {
                isTouched = false;
                int space = titleView.getHeight() + seletView.getHeight();
                if (position == 0) {
                    svAll.smoothScrollTo(0, llNear.getTop() - space + 1);
                } else if (position == 1) {
                    svAll.smoothScrollTo(0, llLinpu.getTop() - space + 1);

                } else if (position == 2) {
                    svAll.smoothScrollTo(0, llMenmianshuju.getTop() - space + 1);

                } else if (position == 3) {
                    svAll.smoothScrollTo(0, llJingyingfanwei.getTop() - space + 1);

                } else if (position == 4) {
                    svAll.smoothScrollTo(0, llJingyingfeiyong.getTop() - space + 1);

                } else if (position == 5) {
                    svAll.smoothScrollTo(0, llPeitao.getTop() - space + 1);

                }
            }
        });


    }

    /**
     * 判断是否滑动到view
     *
     * @param view
     * @return
     */
    private boolean isScrolled(View view) {
        Rect coverrect = new Rect();
        viewDeliverSpace.getGlobalVisibleRect(coverrect);
        Rect viewOr = new Rect();
        view.getGlobalVisibleRect(viewOr);
        return coverrect.intersect(viewOr);
    }

    private void updateView(float scrollY) {

        int top = llNear.getTop();

        int titleView = UnitUtil.dip2px(context, 97);

        if (scrollY >= 0) {
            float deltaY = top - titleView - scrollY;
            if (deltaY >= 0) {
                float fraction = deltaY / (top - titleView);
                llTop.setAlpha(1 - fraction);
            } else {
                llTop.setAlpha(1);
            }
        } else {
//            llTop.setAlpha(1);
        }
    }

    @OnClick({R.id.imv_focus_house_back, R.id.imv_back, R.id.tv_share, R.id.tv_shop_more, R.id.tv_jiucuo, R.id.tv_yuyue, R.id.tv_call, R.id.tv_collection})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                break;
            case R.id.imv_back:
                finish();
                break;
            case R.id.tv_share:
                if (shareDialog == null) {
                    shareDialog = new ShareDialog(this);
                }
                shareDialog.show();
//                mShareAction.open();
                break;
            case R.id.tv_shop_more:
                Intent intent = new Intent(ShopDetailActivity.this, ShopMoreActivity.class);
                startActivity(intent);
                break;

            case R.id.tv_jiucuo:
                Intent intentjiucuo = new Intent(ShopDetailActivity.this, MisTakeActivity.class);
                startActivity(intentjiucuo);
                break;

            case R.id.tv_yuyue:
                Intent orderIntent = new Intent(ShopDetailActivity.this, OrderShopActivity.class);
                startActivity(orderIntent);
                break;
            case R.id.tv_call:

                if (mNoticeDialog == null) {
                    mNoticeDialog = new NoticeDialog(this);
                    mNoticeDialog.setMessage("房东电话：1111111111");
                    mNoticeDialog.setPositiveBtn("拨打");
                }
                if (!mNoticeDialog.isShowing()) {
                    mNoticeDialog.show();
                }
                break;
            case R.id.tv_collection:
                mPresenter.collectShop(shopId);
                break;

        }
    }

    private void shareInit() {
        mShareListener = new CustomShareListener(this);
        /*增加自定义按钮的分享面板*/
        mShareAction = new ShareAction(ShopDetailActivity.this).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
                SHARE_MEDIA.ALIPAY, SHARE_MEDIA.RENREN, SHARE_MEDIA.DOUBAN,
                SHARE_MEDIA.SMS, SHARE_MEDIA.EMAIL, SHARE_MEDIA.YNOTE,
                SHARE_MEDIA.EVERNOTE, SHARE_MEDIA.LAIWANG, SHARE_MEDIA.LAIWANG_DYNAMIC,
                SHARE_MEDIA.LINKEDIN, SHARE_MEDIA.YIXIN, SHARE_MEDIA.YIXIN_CIRCLE,
                SHARE_MEDIA.TENCENT, SHARE_MEDIA.FACEBOOK, SHARE_MEDIA.TWITTER,
                SHARE_MEDIA.WHATSAPP, SHARE_MEDIA.GOOGLEPLUS, SHARE_MEDIA.LINE,
                SHARE_MEDIA.INSTAGRAM, SHARE_MEDIA.KAKAO, SHARE_MEDIA.PINTEREST,
                SHARE_MEDIA.POCKET, SHARE_MEDIA.TUMBLR, SHARE_MEDIA.FLICKR,
                SHARE_MEDIA.FOURSQUARE, SHARE_MEDIA.MORE)
                .addButton("umeng_sharebutton_copy", "umeng_sharebutton_copy", "umeng_socialize_copy", "umeng_socialize_copy")
                .addButton("umeng_sharebutton_copyurl", "umeng_sharebutton_copyurl", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (snsPlatform.mShowWord.equals("umeng_sharebutton_copy")) {
                            Toast.makeText(ShopDetailActivity.this, "复制文本按钮", Toast.LENGTH_LONG).show();
                        } else if (snsPlatform.mShowWord.equals("umeng_sharebutton_copyurl")) {
                            Toast.makeText(ShopDetailActivity.this, "复制链接按钮", Toast.LENGTH_LONG).show();

                        } else {
                            UMWeb web = new UMWeb("http://bbs.umeng.com/");
                            web.setTitle("来自分享面板标题");
                            web.setDescription("来自分享面板内容");
                            web.setThumb(new UMImage(ShopDetailActivity.this, R.drawable.ic_camera));
                            new ShareAction(ShopDetailActivity.this).withMedia(web)
                                    .setPlatform(share_media)
                                    .setCallback(mShareListener)
                                    .share();
                        }
                    }
                });
    }

    @Override
    public void collectedShop(boolean isSuccess) {
        if (isSuccess) {
            tvCollection.setSelected(true);
        } else {
            tvCollection.setSelected(false);
        }
    }

    @Override
    public void getShopDetail(ShopDetail shopDetail) {
        ShopDetail.DataBean data = shopDetail.getData();
        tvName.setText("由 小二 " + data.getClerkName() + " 于" + data.getIssueShopTime() + " 实勘核实");
        tvShopAddress.setText(data.getAddress());
        tvScan.setText(data.getVisitCount() + "浏览");
        tvLianxi.setText(data.getContactCount() + "联系");
        tvPrice.setText(UnitUtil.limitNum(data.getRent(), 99999) + "(" + data.getRentWayName() + ")");

        String compactResidue = "";
        if (data.getCompactResidue() > 0) {
            compactResidue = "带租约";
        }

        tvZhuanprice.setText(UnitUtil.limitNum(data.getTransferFee(), 0) + compactResidue);
        tvArea.setText(UnitUtil.formatMNum(data.getArea()) + "㎡");

        if (data.getNearInfoList() != null && data.getNearInfoList().size() > 0) {
            viewSpaceLinpu.setVisibility(View.VISIBLE);
            llLinpu.setVisibility(View.VISIBLE);
            viewShopThree.setVisibility(View.VISIBLE);
            viewShopThree.setShopName("本店");
            viewShopThree.setIsOwnShop();
            for (int i = 0; i < data.getNearInfoList().size(); i++) {
                ShopDetail.DataBean.NearInfoListBean tmp = data.getNearInfoList().get(i);
                if (tmp.getNearSeat() == 0) {
                    viewShopOne.setVisibility(View.VISIBLE);
                    viewShopOne.setShopName(tmp.getName());
                } else if (tmp.getNearSeat() == 1) {
                    viewShopTwo.setVisibility(View.VISIBLE);
                    viewShopTwo.setShopName(tmp.getName());
                } else if (tmp.getNearSeat() == 2) {
                    viewShopFour.setVisibility(View.VISIBLE);
                    viewShopFour.setShopName(tmp.getName());
                } else if (tmp.getNearSeat() == 3) {
                    viewShopFive.setVisibility(View.VISIBLE);
                    viewShopFive.setShopName(tmp.getName());
                }
            }
        } else {
            viewSpaceLinpu.setVisibility(View.GONE);
            llLinpu.setVisibility(View.GONE);
        }

        tvCenggao.setText((data.getHeight() == 0 ? "--" : data.getHeight() + "m"));
        tvMiankuan.setText((data.getWidth() == 0 ? "--" : data.getWidth() + "m"));
        tvJinshen.setText((data.getDepth() == 0 ? "--" : data.getDepth() + "m"));

        if (data.getElectricRate() != 0 && data.getWaterRate() != 0 && data.getGasRate() != 0 && data.getPropertyRate() != 0) {
            viewSpaceJingyingfeiyong.setVisibility(View.VISIBLE);
            llJingyingfeiyongone.setVisibility(View.VISIBLE);
            llJingyingfeiyongtwo.setVisibility(View.GONE);

            tvDianfei.setText(data.getElectricRate() + "");
            tvShuifei.setText(data.getWaterRate() + "");
            tvRanqi.setText(data.getGasRate() + "");
            tvWuye.setText(data.getPropertyRate() + "");


        } else if (data.getElectricRate() == 0 && data.getWaterRate() == 0 && data.getGasRate() == 0 && data.getPropertyRate() == 0) {
            viewSpaceJingyingfeiyong.setVisibility(View.GONE);
            llJingyingfeiyong.setVisibility(View.GONE);
        } else {
            viewSpaceJingyingfeiyong.setVisibility(View.VISIBLE);
            llJingyingfeiyongone.setVisibility(View.GONE);
            llJingyingfeiyongtwo.setVisibility(View.VISIBLE);

            if (data.getElectricRate() != 0) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.weight = 1;
                RateView rv = new RateView(this);
                rv.setNum(data.getElectricRate() + "");
                rv.setNotice("电费(元/度)");
                llJingyingfeiyongtwo.addView(rv, lp);
            }

            if (data.getWaterRate() != 0) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.weight = 1;
                RateView rv = new RateView(this);
                rv.setNum(data.getWaterRate() + "");
                rv.setNotice("水费(元/吨)");
                if (llJingyingfeiyongtwo.getChildCount() > 0) {
                    View view = new View(this);
                    LinearLayout.LayoutParams lpspace = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT);
                    lpspace.setMargins(0, 30, 0, 30);
                    view.setBackgroundResource(R.color.color_line);
                    llJingyingfeiyongtwo.addView(view, lpspace);
                }
                llJingyingfeiyongtwo.addView(rv, lp);
            }

            if (data.getGasRate() != 0) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.weight = 1;
                RateView rv = new RateView(this);
                rv.setNum(data.getGasRate() + "");
                rv.setNotice("燃气费(元/㎡)");
                if (llJingyingfeiyongtwo.getChildCount() > 0) {
                    View view = new View(this);
                    LinearLayout.LayoutParams lpspace = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT);
                    lpspace.setMargins(0, 30, 0, 30);
                    view.setBackgroundResource(R.color.color_line);
                    llJingyingfeiyongtwo.addView(view, lpspace);
                }
                llJingyingfeiyongtwo.addView(rv, lp);
            }

            if (data.getPropertyRate() != 0) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.weight = 1;
                RateView rv = new RateView(this);
                rv.setNum(data.getPropertyRate() + "");
                rv.setNotice("物业费(元/㎡/月)");
                if (llJingyingfeiyongtwo.getChildCount() > 0) {
                    View view = new View(this);
                    LinearLayout.LayoutParams lpspace = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT);
                    lpspace.setMargins(0, 30, 0, 30);
                    view.setBackgroundResource(R.color.color_line);
                    llJingyingfeiyongtwo.addView(view, lpspace);
                }
                llJingyingfeiyongtwo.addView(rv, lp);
            }
        }


        SupportTagAdapter supportTagAdapter = new SupportTagAdapter(this, data.getSupportList());
        gvSupport.setAdapter(supportTagAdapter);

        if (data.getImageList() != null && data.getImageList().size() > 0) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < data.getImageList().size(); i++) {
                list.add(data.getImageList().get(i).getImgUrl());
            }
            showBaner(list);
        }

        if (data.getIndustryList() != null && data.getIndustryList().size() > 0) {
            viewSpaceJingyingfanwei.setVisibility(View.VISIBLE);
            llJingyingfanwei.setVisibility(View.VISIBLE);
            tgView.setAdapter(new TagDetailAdapter(this, data.getIndustryList()));
        } else {
            viewSpaceJingyingfanwei.setVisibility(View.GONE);
            llJingyingfanwei.setVisibility(View.GONE);
        }


    }


    private static class CustomShareListener implements UMShareListener {

        private WeakReference<ShopDetailActivity> mActivity;

        private CustomShareListener(ShopDetailActivity activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {

            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(mActivity.get(), platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                        && platform != SHARE_MEDIA.EMAIL
                        && platform != SHARE_MEDIA.FLICKR
                        && platform != SHARE_MEDIA.FOURSQUARE
                        && platform != SHARE_MEDIA.TUMBLR
                        && platform != SHARE_MEDIA.POCKET
                        && platform != SHARE_MEDIA.PINTEREST

                        && platform != SHARE_MEDIA.INSTAGRAM
                        && platform != SHARE_MEDIA.GOOGLEPLUS
                        && platform != SHARE_MEDIA.YNOTE
                        && platform != SHARE_MEDIA.EVERNOTE) {
                    Toast.makeText(mActivity.get(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                }

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.FLICKR
                    && platform != SHARE_MEDIA.FOURSQUARE
                    && platform != SHARE_MEDIA.TUMBLR
                    && platform != SHARE_MEDIA.POCKET
                    && platform != SHARE_MEDIA.PINTEREST

                    && platform != SHARE_MEDIA.INSTAGRAM
                    && platform != SHARE_MEDIA.GOOGLEPLUS
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
                Toast.makeText(mActivity.get(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
                if (t != null) {
                    com.umeng.socialize.utils.Log.d("throw", "throw:" + t.getMessage());
                }
            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

            Toast.makeText(mActivity.get(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }

    private void showBaner(final ArrayList<String> list) {
        bannerView.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        bannerView.setIndicatorGravity(BannerConfig.CENTER);
        bannerView.setImageLoader(new GlideImageLoader());

//        //TODO  测试用
//        final ArrayList<String> mlist = new ArrayList<>();
//        mlist.add("http://img5.imgtn.bdimg.com/it/u=611483611,2895064642&fm=23&gp=0.jpg");
//        mlist.add("http://img0.imgtn.bdimg.com/it/u=3597903479,3025957499&fm=23&gp=0.jpg");
//        mlist.add("http://img3.imgtn.bdimg.com/it/u=2110963888,887379731&fm=23&gp=0.jpg");
//        mlist.add("http://img2.imgtn.bdimg.com/it/u=3161191814,1771697536&fm=23&gp=0.jpg");

        bannerView.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intents = new Intent(context, ImagePreviewActivity.class);
                intents.putExtra("pics", list);
                intents.putExtra("index", position);
                context.startActivity(intents);
            }
        });


        bannerView.setImages(list);
        bannerView.start();

    }

}