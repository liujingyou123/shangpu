package com.finance.winport.tab;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.account.LoginActivity;
import com.finance.winport.account.event.LoginEvent;
import com.finance.winport.account.event.LoginOutEvent;
import com.finance.winport.account.model.UserInfo;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.image.Batman;
import com.finance.winport.image.BatmanCallBack;
import com.finance.winport.mine.MyNoticeActivity;
import com.finance.winport.mine.MyServiceActivity;
import com.finance.winport.mine.PersonalInfoActivity;
import com.finance.winport.mine.SettingsActivity;
import com.finance.winport.mine.SuggestActivity;
import com.finance.winport.mine.model.PersonalInfoResponse;
import com.finance.winport.mine.presenter.IPersonalInfoView;
import com.finance.winport.mine.presenter.PersonalInfoPresenter;
import com.finance.winport.tab.model.Lunar;
import com.finance.winport.tab.model.UnReadMsg;
import com.finance.winport.tab.model.WinportCounts;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.tab.net.PersonManager;
import com.finance.winport.trade.MyPostListActivity;
import com.finance.winport.util.Constant;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.util.StringUtil;
import com.finance.winport.view.StopWatchTextView;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;


/**
 *
 *
 */

public class MineFragment extends BaseFragment implements IPersonalInfoView {
    @BindView(R.id.mine_winport)
    StopWatchTextView mineWinport;
    @BindView(R.id.mine_appoint)
    StopWatchTextView mineAppoint;
    @BindView(R.id.mine_collection)
    StopWatchTextView mineCollection;
    @BindView(R.id.mine_scan)
    StopWatchTextView mineScan;
    @BindView(R.id.day)
    TextView day;
    @BindView(R.id.solar)
    TextView solar;
    @BindView(R.id.lunar)
    TextView lunar;
    @BindView(R.id.yi)
    TextView yi;
    @BindView(R.id.ji)
    TextView ji;
    @BindView(R.id.mine_schedule)
    TextView mineService;
    @BindView(R.id.hot_line)
    TextView hotLine;
    @BindView(R.id.tv_focus_right)
    ImageView ivFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.shop_img)
    ImageView shopImg;
    @BindView(R.id.modify)
    TextView modify;
    @BindView(R.id.schedule_list)
    RelativeLayout scheduleList;
    @BindView(R.id.setting)
    RelativeLayout setting;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.personal_sign)
    TextView personalSign;
    Unbinder unbinder;
    private PersonalInfoPresenter mPresenter;
    private PersonalInfoResponse info;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onLoginOutEvent(LoginOutEvent event) {
        if (event != null) {
            init();
        }
    }

    @Subscribe
    public void onLoginEvent(LoginEvent event) {
        if (event != null) {
            init();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.mine_fragment, container, false);
        unbinder = ButterKnife.bind(this, root);
        initView();
        init();
        asyncRelevant();
        return root;
    }

    private void initView() {
        yi.setVisibility(View.GONE);
        ji.setVisibility(View.GONE);
        hotLine.setText(Constant.SERVICE_PHONE);
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new PersonalInfoPresenter(this);
        }
        mPresenter.getPersonalInfo();

    }

    private void init() {
        if (isLogin()) {
//            String number = SharedPrefsUtil.getUserInfo().data.userPhone;
//            if (StringUtil.isCellPhone(number)) {
//                phone.setText(number.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
//            } else {
//                phone.setText("");
//            }
            setHeadImage(SharedPrefsUtil.getUserInfo().data.headPortrait);
            modify.setVisibility(View.VISIBLE);
//            personalSign.setText("老板很懒，暂未设置签名");
            getData();
        } else {
            phone.setText("未登录");
            personalSign.setText("点击登录帐号");
            modify.setVisibility(View.GONE);
            shopImg.setImageResource(R.mipmap.icon_default_head);
            mineWinport.setText("--");
            mineAppoint.setText("--");
            mineCollection.setText("--");
            mineScan.setText("--");
            mineService.setText("");
        }
    }

    private void setHuangLi(Lunar.DataBean data) {
        day.setText(data.day);
        solar.setText(data.year + "-" + data.month);
        lunar.setText(data.lunarmonth + data.lunarday);
        Observable.from(data.huangli.yi).take(5).subscribe(new Subscriber<String>() {
            StringBuilder sb = new StringBuilder();

            @Override
            public void onCompleted() {
                yi.setText(sb.toString());
                yi.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                sb.append(s + " ");
            }
        });

        Observable.from(data.huangli.ji).take(5).subscribe(new Subscriber<String>() {
            StringBuilder sb = new StringBuilder();

            @Override
            public void onCompleted() {
                ji.setText(sb.toString());
                ji.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                sb.append(s + " ");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private boolean isLogin() {
        UserInfo info = SharedPrefsUtil.getUserInfo();
        return info != null;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            retryRelevant();
            if (hotLine.getText().toString().trim().length() <= 0) {
                hotLine.setText(Constant.SERVICE_PHONE);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        asyncRelevant();
    }

    private boolean isMsgOk;
    private boolean isLunarOk;
    private boolean isWinportCountsOk;
    private boolean isDataOk;

    private void retryRelevant() {
        if (!isMsgOk && isLogin()) {
            getUnReadMsg();
        }
        if (!isLunarOk) {
            getLunar();
        }
        if (!isWinportCountsOk && isLogin()) {
            getWinportCounts();
        }
        if (!isDataOk && isLogin()) {
            getData();
        }

    }

    //获取个人中心相关数据
    private void asyncRelevant() {
        if (isLogin()) {
            getUnReadMsg();
            getWinportCounts();
            getData();
            modify.setVisibility(View.VISIBLE);
        } else {
            init();
        }
        getLunar();
    }

    //获取未读消息
    private void getUnReadMsg() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("receiveType", 0);//0:客户 1：业务员
        PersonManager.getInstance().getUnReadMsg(params, new NetworkCallback<UnReadMsg>() {
            @Override
            public void success(UnReadMsg response) {
                if (getView() == null) return;
                if (response != null && response.isSuccess()) {
                    ivFocusRight.setActivated(response.data);
                    isMsgOk = true;
                }
            }

            @Override
            public void failure(Throwable throwable) {
                isMsgOk = false;
            }
        });
    }

    //我的发布、约看、收藏、浏览、未来日程 总数统计接口
    private void getWinportCounts() {
        HashMap<String, Object> params = new HashMap<>();
        PersonManager.getInstance().getWinportCounts(params, new NetworkCallback<WinportCounts>() {
            @Override
            public void success(WinportCounts response) {
                if (response != null && response.isSuccess()) {
                    setWinportCounts(response);
                    isWinportCountsOk = true;
                }
            }

            @Override
            public void failure(Throwable throwable) {
                isWinportCountsOk = false;
            }
        });
    }

    //获取农历
    private void getLunar() {
        HashMap<String, Object> params = new HashMap<>();
        PersonManager.getInstance().getLunar(params, new NetworkCallback<Lunar>() {
            @Override
            public void success(Lunar response) {
                if (response != null && response.isSuccess()) {
                    setHuangLi(response.data);
                    isLunarOk = true;
                }
            }

            @Override
            public void failure(Throwable throwable) {
                isLunarOk = true;
            }
        });
    }

    //设置发布、约看、收藏、浏览、日程数
    private void setWinportCounts(WinportCounts response) {
        if (response == null || response.data == null) return;
        if (!TextUtils.equals(mineWinport.getText().toString().trim(), response.data.issuerCount + "")) {
            mineWinport.setShowNumber(response.data.issuerCount);
        }
        if (!TextUtils.equals(mineAppoint.getText().toString().trim(), response.data.visitCount + "")) {
            mineAppoint.setShowNumber(response.data.visitCount);
        }
        if (!TextUtils.equals(mineCollection.getText().toString().trim(), response.data.collectedCount + "")) {
            mineCollection.setShowNumber(response.data.collectedCount);
        }
        if (!TextUtils.equals(mineScan.getText().toString().trim(), response.data.browseCount + "")) {
            mineScan.setShowNumber(response.data.browseCount);
        }
//        if (response.data.scheduleCount > 0) {
//            String sc = getString(R.string.mine_schedule, response.data.scheduleCount + "");
//            SpannableString sp = new SpannableString(sc);
//            int start = sc.indexOf(response.data.scheduleCount + "");
//            int end = start + (response.data.scheduleCount + "").length();
//            sp.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            mineService.setText(sp);
//        }

    }


    @OnClick({R.id.tv_focus_right, R.id.schedule_list, R.id.info_layout
            , R.id.ll_mine_winport, R.id.ll_mine_collection, R.id.ll_mine_appoint
            , R.id.ll_mine_scan, R.id.post, R.id.suggest})
    public void onRequiredLoginClicked(View view) {
//        if (!isLogin()) {// not login
//            toLogin();
//            return;
//        }
        switch (view.getId()) {
            case R.id.tv_focus_right:
                MobclickAgent.onEvent(context, "message");
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                startActivity(new Intent(getActivity(), MyNoticeActivity.class));
                break;
            case R.id.schedule_list:
                MobclickAgent.onEvent(context, "my_service");
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                startActivity(new Intent(getActivity(), MyServiceActivity.class));
                break;
            case R.id.info_layout:
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                toPersonalInfo();
                break;
            case R.id.ll_mine_winport:
                MobclickAgent.onEvent(context, "my_shop");
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                Intent release = new Intent(context, WinportActivity.class);
                release.putExtra("type", TypeList.RELEASE);
                release.putExtra("title", "我发布的旺铺");
                startActivity(release);
                break;
            case R.id.ll_mine_appoint:
                MobclickAgent.onEvent(context, "my_shoporder");
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                Intent appoint = new Intent(context, WinportActivity.class);
                appoint.putExtra("type", TypeList.APPOINT);
                appoint.putExtra("title", "约看过的旺铺");
                startActivity(appoint);
                break;
            case R.id.ll_mine_collection:
                MobclickAgent.onEvent(context, "my_shopcollect");
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                Intent collection = new Intent(context, WinportActivity.class);
                collection.putExtra("type", TypeList.COLLECTION);
                collection.putExtra("title", "我的收藏");
                startActivity(collection);
                break;
            case R.id.ll_mine_scan:
                MobclickAgent.onEvent(context, "my_shopbrowse");
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                Intent scan = new Intent(context, WinportActivity.class);
                scan.putExtra("type", TypeList.SCAN);
                scan.putExtra("title", "最近浏览");
                startActivity(scan);
                break;
            case R.id.post:// 我的帖子
                MobclickAgent.onEvent(context, "my_post");
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                startActivity(new Intent(context, MyPostListActivity.class));
                break;
            case R.id.suggest:
                MobclickAgent.onEvent(context, "my_suggest");
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                startActivity(new Intent(context, SuggestActivity.class));
                break;
        }
    }

    @OnClick({R.id.setting, R.id.fierce_prediction})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.setting:
                MobclickAgent.onEvent(context, "my_system");
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            case R.id.fierce_prediction:
                MobclickAgent.onEvent(context, "my_luckyshopname");
                startActivity(new Intent(context, FiercePredictionActivity.class));
                break;
        }
    }

    // 登录
    private void toLogin() {
        startActivity(new Intent(context, LoginActivity.class));
    }

    // 个人信息
    private void toPersonalInfo() {
        startActivity(new Intent(context, PersonalInfoActivity.class).putExtra("info", info));
    }

    private void saveHeadInfo(String headUrl) {
        UserInfo info = SharedPrefsUtil.getUserInfo();
        if (info != null) {
            info.data.headPortrait = headUrl;
            SharedPrefsUtil.saveUserInfo(info);
        }
    }

    private void setHeadImage(String path) {
        Batman.getInstance().fromNet(path, new BatmanCallBack() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                shopImg.setImageBitmap(bitmap);
            }

            @Override
            public void onFailure(Exception error) {
            }
        });
    }

    @Override
    public void showPersonalInfo(PersonalInfoResponse response) {
        isDataOk = true;
        this.info = response;
        phone.setText(response.data.nickName == null ? "" : response.data.nickName);
        personalSign.setText(response.data.signature == null ? "" : response.data.signature);
        mineService.setText(getMyService(response.data.myService));
        String headPortrait = response.data.headPortrait;
        saveHeadInfo(headPortrait);
        setHeadImage(headPortrait);

    }

    private Spanned getMyService(String myService) {
        if (TextUtils.isEmpty(myService)) return null;
//        String num = myService.replaceAll("\\D", "");
//        String spannedNum = "<span style=\"color:#333333\">" + myService.replaceAll("\\D", "") + "</span>";
        String html = getString(R.string.my_service, myService);
        Spanned spannedStr = Html.fromHtml(html);
        return spannedStr;
    }
}
