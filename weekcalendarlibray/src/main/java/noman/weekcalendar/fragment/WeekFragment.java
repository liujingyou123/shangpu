package noman.weekcalendar.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import org.greenrobot.eventbus.EventBus;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.util.ArrayList;

import noman.weekcalendar.NowDays;
import noman.weekcalendar.R;
import noman.weekcalendar.eventbus.BusProvider;
import noman.weekcalendar.eventbus.Event;

/**
 * Created by nor on 12/4/2015.
 */
public class WeekFragment extends Fragment {
    public static String DATE_KEY = "date_key";
    public static String TAGDATE = "tag_date";
    private GridView gridView;
    private WeekAdapter weekAdapter;
    public static DateTime selectedDateTime = new DateTime();
    public static DateTime CalendarStartDate = new DateTime();
    private DateTime startDate;
    private DateTime endDate;
    private boolean isVisible;

    View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView==null){

            rootView = inflater.inflate(R.layout.fragment_week, container, false);
            gridView = (GridView) rootView.findViewById(R.id.gridView);
            init();
        }

        return rootView;
    }

    private void init() {
//        NowDays nowDays = (NowDays) getArguments().getSerializable("NowDays");
        Log.e("initweek","initweek");
        ArrayList<DateTime> days = new ArrayList<>();
        DateTime midDate = (DateTime) getArguments().getSerializable(DATE_KEY);
        mNowDays = (NowDays) getArguments().getSerializable(TAGDATE);
        if (midDate != null) {
            midDate = midDate.withDayOfWeek(DateTimeConstants.THURSDAY);
        }
        //Getting all seven days

        for (int i = -3; i <= 3; i++)
            days.add(midDate != null ? midDate.plusDays(i) : null);

        startDate = days.get(0);
        endDate = days.get(days.size() - 1);

        weekAdapter = new WeekAdapter(getActivity(), days);
        gridView.setAdapter(weekAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BusProvider.getInstance().post(new Event.OnDateClickEvent(weekAdapter.getItem
                        (position)));
                selectedDateTime = weekAdapter.getItem(position);
                BusProvider.getInstance().post(new Event.InvalidateEvent());
            }
        });
    }
    private NowDays mNowDays;
    @org.greenrobot.eventbus.Subscribe
    public void getList(NowDays nowDays){
        mNowDays = nowDays;
        weekAdapter.notifyDataSetChanged();
//        Log.e("get","********");
    }
//    @org.greenrobot.eventbus.Subscribe
//    public void getTagDate(Event.TagDateEven even){
//        this.mNowDays = even.getTagDate();
//        weekAdapter.notifyDataSetChanged();
//    }
    @Subscribe
    public void updateSelectedDate(Event.UpdateSelectedDateEvent event) {
        if (isVisible) {
            selectedDateTime = selectedDateTime.plusDays(event.getDirection());
            if (selectedDateTime.toLocalDate().equals(endDate.plusDays(1).toLocalDate())
                    || selectedDateTime.toLocalDate().equals(startDate.plusDays(-1).toLocalDate())) {
                if (!(selectedDateTime.toLocalDate().equals(startDate.plusDays(-1).toLocalDate()) &&
                        event.getDirection() == 1)
                        && !(selectedDateTime.toLocalDate().equals(endDate.plusDays(1)
                        .toLocalDate()) && event.getDirection() == -1))
                    BusProvider.getInstance().post(new Event.SetCurrentPageEvent(event.getDirection()));
            }
            BusProvider.getInstance().post(new Event.InvalidateEvent());
        }
    }


    @Subscribe
    public void invalidate(Event.InvalidateEvent event) {
        gridView.invalidateViews();
    }

    @Subscribe
    public void updateUi(Event.OnUpdateUi event) {
        weekAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        BusProvider.getInstance().register(this);
            EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        BusProvider.getInstance().unregister(this);
            EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        isVisible = isVisibleToUser;
        super.setUserVisibleHint(isVisibleToUser);
    }

    public class WeekAdapter extends BaseAdapter {
        private ArrayList<DateTime> days;
        private Context context;
        private DateTime firstDay;

        WeekAdapter(Context context, ArrayList<DateTime> days) {
            this.days = days;
            this.context = context;
        }

        @Override
        public int getCount() {
            return days.size();
        }

        @Override
        public DateTime getItem(int position) {
            return days.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint("InflateParams")
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.e("getview","getview");
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.grid_item, null);
                firstDay = getItem(0);
            }

            DateTime dateTime = getItem(position).withMillisOfDay(0);
            //日期数字
            TextView dayTextView = (TextView) convertView.findViewById(R.id.daytext);
            dayTextView.setText(String.valueOf(dateTime.getDayOfMonth()));
//            Log.e("-------",dateTime.toYearMonthDay()+"---"+ WeekFragment.selectedDateTime.toYearMonthDay());
            BusProvider.getInstance().post(new Event.OnDayDecorateEvent(convertView, dayTextView,
                    dateTime, firstDay, WeekFragment.selectedDateTime));

            View tag = convertView.findViewById(R.id.tag);
            tag.setVisibility(View.GONE);
            //红点标记
            if(mNowDays!=null){
                for(NowDays.SelectDays days: mNowDays.list){
                    if(days.day.equals(String.valueOf(dateTime.getDayOfMonth()))&&days.month.equals(String.valueOf(dateTime.getMonthOfYear()))
                            &&days.year.equals(String.valueOf(dateTime.getYear()))){
                        tag.setVisibility(View.VISIBLE);
                    }
                }
            }
            return convertView;
        }
    }


}
