package noman.weekcalendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import org.joda.time.DateTime;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import noman.weekcalendar.decorator.DayDecorator;
import noman.weekcalendar.decorator.DefaultDayDecorator;
import noman.weekcalendar.eventbus.BusProvider;
import noman.weekcalendar.eventbus.Event;
import noman.weekcalendar.listener.OnDateClickListener;
import noman.weekcalendar.listener.OnWeekChangeListener;
import noman.weekcalendar.view.WeekPager;

/**
 * Created by nor on 12/6/2015.
 */
public class WeekCalendar extends LinearLayout {
    private static final String TAG = "WeekCalendarTAG";
    private OnDateClickListener listener;
//    private TypedArray typedArray;
    private GridView daysName;
    private DayDecorator dayDecorator;
    private OnWeekChangeListener onWeekChangeListener;
    private static  boolean isRegist;
    private WeekPager weekPager;
    private NowDays nowDays;
    Context context;
    FragmentManager fm;
    public WeekCalendar(Context context,FragmentManager fm) {
        super(context);
        this.context = context;
        this.fm = fm;
        init(null);
    }

    public WeekCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);

    }

    public WeekCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);

    }
    public void setTagSeleted(NowDays days){
        this.nowDays = days;
        Log.e("send","-----");
        weekPager.setTagDate(nowDays);
//        EventBus.getDefault().post(nowDays);
    }
//    private NowDays nowDays;
//    public void setNowList(NowDays days){
//        nowDays = days;
//        Log.e("into2",days.hashCode()+"****");
//
//        BusProvider.getInstance().post(mList);
//    }
    private void init(AttributeSet attrs) {

        Log.e("init","init");
//        if (attrs != null) {
//            typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WeekCalendar);
            int selectedDateColor = Color.parseColor("#969696");
            int todayDateColor = Color.parseColor("#fd5e51");
            int daysTextColor = Color.parseColor("#969696");
            float daysTextSize = dip2px(context,14) ;
            int todayDateTextColor = Color.parseColor("#ffffff");
            setDayDecorator(new DefaultDayDecorator(getContext(),
                    selectedDateColor,
                    todayDateColor,
                    todayDateTextColor,
                    daysTextColor,
                    daysTextSize));
//        }
//
        setOrientation(VERTICAL);
        //隐藏指示器
        if (!true) {
            daysName = getDaysNames();
            addView(daysName, 0);
        }


        weekPager = new WeekPager(context, fm);
//        ViewGroup.LayoutParams params =  weekPager.getLayoutParams();
//        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
//        weekPager.setLayoutParams(params);
        addView(weekPager);
        if (nowDays != null){
            weekPager.setTagDate(nowDays);
        }


    }
    /***
     * Do not use this method
     * this is for receiving date,
     * use "setOndateClick" instead.
     */
    @Subscribe
    public void onDateClick(Event.OnDateClickEvent event) {
        if (listener != null)
            listener.onDateClick(event.getDateTime());
    }

    @Subscribe
    public void onDayDecorate(Event.OnDayDecorateEvent event) {
        if (dayDecorator != null) {
            dayDecorator.decorate(event.getView(), event.getDayTextView(), event.getDateTime(),
                    event.getFirstDay(), event.getSelectedDateTime());
        }
    }

    @Subscribe
    public void onWeekChange(Event.OnWeekChange event) {
        if (onWeekChangeListener != null) {
            onWeekChangeListener.onWeekChange(event.getFirstDayOfTheWeek(), event.isForward());
        }
    }

    public void setOnDateClickListener(OnDateClickListener listener) {
        this.listener = listener;
    }

    public void setDayDecorator(DayDecorator decorator) {
        this.dayDecorator = decorator;
    }

    public void setOnWeekChangeListener(OnWeekChangeListener onWeekChangeListener) {
        this.onWeekChangeListener = onWeekChangeListener;
    }
//    上面的指示器
    private GridView getDaysNames() {
        daysName = new GridView(getContext());
        daysName.setSelector(new StateListDrawable());
        daysName.setNumColumns(7);

        daysName.setAdapter(new BaseAdapter() {
            private String[] days = getWeekDayNames();

            public int getCount() {
                return days.length;
            }

            @Override
            public String getItem(int position) {
                return days[position];
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @SuppressLint("InflateParams")
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(R.layout.week_day_grid_item, null);
                }
                TextView day = (TextView) convertView.findViewById(R.id.daytext);
                day.setText(days[position]);
                    day.setTextColor(Color.parseColor("#ffffff"));
                    day.setTextSize(14);
                return convertView;
            }

            private String[] getWeekDayNames() {
                String[] names = DateFormatSymbols.getInstance().getShortWeekdays();
                List<String> daysName = new ArrayList<>(Arrays.asList(names));
                daysName.remove(0);
                daysName.add(daysName.remove(0));

                if (1 == 0)
                    for (int i = 0; i < daysName.size(); i++)
                        daysName.set(i, daysName.get(i).substring(0, 1));
                names = new String[daysName.size()];
                daysName.toArray(names);
//                Log.e("length",daysName+"");
                return names;

            }
        });
            daysName.setBackgroundColor(Color.parseColor("#3F51B5"));
        return daysName;
    }

    /**
     * Renders the days again. If you depend on deferred data which need to update the calendar
     * after it's resolved to decorate the days.
     */
    public void updateUi() {
        BusProvider.getInstance().post(new Event.OnUpdateUi());
    }

    public void moveToPrevious() {
        BusProvider.getInstance().post(new Event.UpdateSelectedDateEvent(-1));
    }

    public void moveToNext() {
        BusProvider.getInstance().post(new Event.UpdateSelectedDateEvent(1));
    }

    public void reset() {
        BusProvider.getInstance().post(new Event.ResetEvent());
    }

    public void setSelectedDate(DateTime selectedDate) {
        BusProvider.getInstance().post(new Event.SetSelectedDateEvent(selectedDate));
    }

    public void setStartDate(DateTime startDate) {
        BusProvider.getInstance().post(new Event.SetStartDateEvent(startDate));
    }


    public void setFm(FragmentManager fm){
        this.fm = fm;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isRegist){
        BusProvider.getInstance().register(this);
         isRegist = true;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BusProvider.getInstance().unregister(this);
        BusProvider.disposeInstance();
        if(isRegist){
        isRegist = false;}
      }

    public int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
