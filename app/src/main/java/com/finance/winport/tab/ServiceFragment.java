package com.finance.winport.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.service.FindLoanActivity;
import com.finance.winport.service.LoanListActivity;
import com.finance.winport.service.ShopOrderActivity;
import com.finance.winport.service.ShopRentActivity;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.BannerView.Banner;
import com.finance.winport.view.BannerView.ZoomOutSlideTransformer;

import org.joda.time.DateTime;

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

public class ServiceFragment extends BaseFragment {


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
    //    @BindView(R.id.weekCalendar)
    WeekCalendar weekCalendar;

    View root;
    @BindView(R.id.cal)
    LinearLayout cal;
    Unbinder unbinder1;

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


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            Log.i("onHiddenChanged", "hidden为true onHiddenChanged 方法被调用");
//            root = LayoutInflater.from(getActivity()).inflate(R.layout.service_fragment,null);
        }
    }

    private void init() {

        weekCalendar = new WeekCalendar(getContext(),getChildFragmentManager());
        LinearLayout.LayoutParams alpTab = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                UnitUtil.dip2px(context, 55));

        cal.addView(weekCalendar,alpTab);
        banner.start();
//        banner.setBannerAnimation(ZoomOutSlideTransformer.class);
        weekCalendar.setFm(getChildFragmentManager());
        weekCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(DateTime dateTime) {

                Log.i("time", dateTime.toString());
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @OnClick({R.id.rent, R.id.order, R.id.loan, R.id.loan_more})
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
        }
    }


}
