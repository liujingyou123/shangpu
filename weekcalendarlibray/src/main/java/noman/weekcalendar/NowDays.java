package noman.weekcalendar;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangxy on 2017/3/4.
 */

public class NowDays implements Serializable{
    public List<SelectDays> list;
    public class SelectDays implements Serializable{
        public String year;
        public String month;
        public String day;
    }
}
