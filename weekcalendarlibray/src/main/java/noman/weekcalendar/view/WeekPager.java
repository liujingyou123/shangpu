package noman.weekcalendar.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;

import com.squareup.otto.Subscribe;

import org.joda.time.DateTime;

import noman.weekcalendar.NowDays;
import noman.weekcalendar.R;
import noman.weekcalendar.adapter.PagerAdapter;
import noman.weekcalendar.eventbus.BusProvider;
import noman.weekcalendar.eventbus.Event;
import noman.weekcalendar.fragment.WeekFragment;

/**
 * Created by gokhan on 7/28/16.
 */
public class WeekPager extends ViewPager {
    private PagerAdapter adapter;
    private int pos;
    private boolean check;
    public static int NUM_OF_PAGES;
//    private TypedArray typedArray;
    private NowDays nowDays;
    Context context;
    FragmentManager fm;
    public WeekPager(Context context) {
        super(context);
        this.context = context;
        initialize();
    }
    private AttributeSet mAttrs;
    public WeekPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mAttrs = attrs;
        initialize();
    }
    public WeekPager(Context context, FragmentManager fm) {
        super(context);
        this.context = context;
        this.fm = fm;
        initialize();
    }
    public  void setTagDate(NowDays nowDays){
        this.nowDays = nowDays;
        adapter.setTagDate(nowDays);
    }
    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        post(new Runnable() {
            @Override
            public void run() {
                //Force rerendering so the week is drawn again when you return to the view after
                // back button press.
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initialize() {
            NUM_OF_PAGES = 3000;
        setId(idCheck());
        if (!isInEditMode()) {
            initPager(new DateTime());
            BusProvider.getInstance().register(this);

        }
    }

    private void initPager(DateTime dateTime) {
        pos = NUM_OF_PAGES / 2;
        adapter = new PagerAdapter(fm, dateTime);
//        adapter = new PagerAdapter(((AppCompatActivity) context).getSupportFragmentManager(), dateTime);
        adapter.setTagDate(nowDays);
        setAdapter(adapter);
        addOnPageChangeListener(new ViewPager
                .SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (!check)
                    if (position < pos)
                        adapter.swipeBack();
                    else if (position > pos)
                        adapter.swipeForward();
                pos = position;
                check = false;

            }

        });
        setOverScrollMode(OVER_SCROLL_NEVER);
        setCurrentItem(pos);
            setBackgroundColor(Color.parseColor("#ffffff"));
        if (WeekFragment.selectedDateTime == null)
            WeekFragment.selectedDateTime = new DateTime();
    }

    @Subscribe
    public void setCurrentPage(Event.SetCurrentPageEvent event) {
        check = true;
        if (event.getDirection() == 1)
            adapter.swipeForward();
        else
            adapter.swipeBack();
        setCurrentItem(getCurrentItem() + event.getDirection());

    }

    @Subscribe
    public void reset(Event.ResetEvent event) {
        WeekFragment.selectedDateTime = new DateTime(WeekFragment.CalendarStartDate);
        //WeekFragment.CalendarStartDate = new DateTime();
        initPager(WeekFragment.CalendarStartDate);
    }

    @Subscribe
    public void setSelectedDate(Event.SetSelectedDateEvent event) {
        Log.e("88888","8888");
        WeekFragment.selectedDateTime = event.getSelectedDate();
        initPager(event.getSelectedDate());
    }

    @Subscribe
    public void setStartDate(Event.SetStartDateEvent event) {
        WeekFragment.CalendarStartDate = event.getStartDate();
        WeekFragment.selectedDateTime = event.getStartDate();
        initPager(event.getStartDate());
    }

    private int idCheck() {
        int id = 0;
        while (true) {
            if (findViewById(++id) == null) break;
        }
        return id;
    }
}
