package com.finance.winport.service;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.home.ShopDetailActivity;
import com.finance.winport.image.Batman;
import com.finance.winport.mine.adapter.ServiceScheduleListAdapter;
import com.finance.winport.service.model.CalendarListResponse;
import com.finance.winport.service.model.FindServiceResponse;
import com.finance.winport.service.presenter.FindServiceHomePresenter;
import com.finance.winport.service.presenter.IFindServiceHomeView;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.BannerView.Banner;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import noman.weekcalendar.WeekCalendar;
import noman.weekcalendar.listener.OnDateClickListener;


/**
 *
 *
 */

public class ServiceFragment extends BaseFragment implements IFindServiceHomeView {


    @BindView(R.id.banner)
    Banner banner;
    Unbinder unbinder;
    @BindView(R.id.mListView)
    ListView mListView;
    @BindView(R.id.shop_img)
    ImageView shopImg;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.loan_more)
    TextView loanMore;
    @BindView(R.id.rent)
    TextView rent;
    @BindView(R.id.order)
    TextView order;
    @BindView(R.id.loan)
    TextView loan;
    WeekCalendar weekCalendar;

    View root;
    @BindView(R.id.cal)
    LinearLayout cal;
    Unbinder unbinder1;
    @BindView(R.id.old_user)
    LinearLayout oldUser;
    @BindView(R.id.head)
    TextView head;
    @BindView(R.id.head_line)
    View headLine;
    @BindView(R.id.visit_count)
    TextView visitCount;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.shop_area)
    LinearLayout shopArea;
    @BindView(R.id.loan_area)
    LinearLayout loanArea;
    @BindView(R.id.undo_count)
    TextView undoCount;
    private ServiceScheduleListAdapter adapter;

    private FindServiceHomePresenter mPresenter;
    private String id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (root == null) {

            root = inflater.inflate(R.layout.service_fragment, container, false);
            unbinder = ButterKnife.bind(this, root);
            init();
        }
        unbinder1 = ButterKnife.bind(this, root);
        return root;
    }


    private void getData() {
        if (mPresenter == null) {
            mPresenter = new FindServiceHomePresenter(this);
        }
        mPresenter.getFindServiceHome();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            Log.i("onHiddenChanged", "hidden为true onHiddenChanged 方法被调用");
//            root = LayoutInflater.from(getActivity()).inflate(R.layout.service_fragment,null);
        }
    }

    private void init() {

//        if()

        getData();


    }

    private void getCalendarData() {
        if (mPresenter == null) {
            mPresenter = new FindServiceHomePresenter(this);
        }
        mPresenter.getCalendarHome();
    }

    public void initOld(FindServiceResponse response) {

        getCalendarData();
        oldUser.setVisibility(View.VISIBLE);
        head.setVisibility(View.VISIBLE);
        headLine.setVisibility(View.VISIBLE);
        banner.stopAutoPlay();
        weekCalendar = new WeekCalendar(getContext(), getChildFragmentManager());
        LinearLayout.LayoutParams alpTab = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                UnitUtil.dip2px(context, 55));

        cal.addView(weekCalendar, alpTab);

//        banner.setBannerAnimation(ZoomOutSlideTransformer.class);
        weekCalendar.setFm(getChildFragmentManager());
        weekCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(DateTime dateTime) {

                Log.i("time", dateTime.toString());
            }
        });


        if (response.getData().getShopObject() == null) {
            shopArea.setVisibility(View.GONE);
        } else {

            address.setText(response.getData().getShopObject().getAddress());
            visitCount.setText("一周内" + response.getData().getShopObject().getVisitCount() + "位老板浏览了此店铺");
            Batman.getInstance().fromNet(response.getData().getShopObject().getCoverImg(), shopImg);
            id = response.getData().getShopObject().getId()+"";
        }
        if (response.getData().getLoadDTO() == null) {

            loanArea.setVisibility(View.GONE);
        } else {

            time.setText(response.getData().getLoadDTO().getApplyTime());
            money.setText(response.getData().getLoadDTO().getLoanLimit() + "万元  " + response.getData().getLoadDTO().getLoanMaturity() + "个月");
            status.setText(response.getData().getLoadDTO().getStatus());
        }
    }

    public void initNew() {
        banner.setVisibility(View.VISIBLE);
        banner.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
//        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
//        banner.stopAutoPlay();
    }

    @OnClick({R.id.rent, R.id.order, R.id.loan, R.id.loan_more, R.id.shop, R.id.shop_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rent:
                Intent rentIntent = new Intent(getActivity(), ShopRentActivity.class);
                startActivity(rentIntent);
                break;
            case R.id.order:
                Intent orderIntent = new Intent(getActivity(), ShopOrderActivity.class);
                startActivity(orderIntent);
                break;
            case R.id.loan:
                Intent loanIntent = new Intent(getActivity(), FindLoanActivity.class);
                startActivity(loanIntent);
                break;
            case R.id.loan_more:
                Intent intent = new Intent(getActivity(), LoanListActivity.class);
                startActivity(intent);
                break;
            case R.id.shop:
                Intent detailIntent = new Intent(getActivity(), ShopDetailActivity.class);
                detailIntent.putExtra("shopId",id);
                startActivity(detailIntent);
                break;
            case R.id.shop_more:
//                Intent intent = new Intent(getActivity(), LoanListActivity.class);
//                startActivity(intent);
                break;
        }
    }


    @Override
    public void showServiceHome(FindServiceResponse response) {

        if (response.getData().getIsNew() == 0) {
            initOld(response);
        } else {

            initNew();
        }
    }

    @Override
    public void showCalendar(CalendarListResponse response) {
        SpannableString builder1 = new SpannableString("老板，未来您有"+response.getData().getUndoScheduleCount() + "个安排");
        ForegroundColorSpan redSpan1 = new ForegroundColorSpan(Color.parseColor("#151515"));
        builder1.setSpan(redSpan1, 7, builder1.length()-3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        undoCount.setText(builder1);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = sdf.format(d);
        for (int i = 0; i < response.getData().getDateList().size(); i++) {

            if (nowDate.equals(response.getData().getDateList().get(i).getDateString())) {
                setAdapter(response.getData().getDateList().get(i).getScheduleList());
            }
        }

    }


    public void setAdapter(List<CalendarListResponse.DataBean.DateListBean.ScheduleListBean> list) {
        if (adapter == null) {
            adapter = new ServiceScheduleListAdapter(getActivity(), list);

            mListView.setAdapter(adapter);
            ViewGroup.LayoutParams parm = mListView.getLayoutParams();
            parm.height = UnitUtil.dip2px(getActivity(), 75 * list.size());

//            totalPage = (int) Math.ceil(adapter.getTotalCount() / (float) LIMIT);
        } else {
//            if (pageNumber == 1) {
//                adapter.refreshData(list, totalCount);
//            } else {
//                adapter.updateData(list, totalCount);
//            }

            adapter.notifyDataSetChanged();
        }
    }
}
