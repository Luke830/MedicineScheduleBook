package luke.com.project.medicineschedulebook;

import java.util.Calendar;

/**
 * Created by firefox on 2017. 2. 22..
 */

public class DateModel {

    public boolean isDayOfWeek;
    public boolean isEnableClick;
    public String day;
    public Calendar calendar;
    public int textColor;
    public boolean isEvent;

    //    public EventModel eventModel;
    public String drugDay;
    public String drugWeek;

    public boolean isToday;
    public DrugMainModel drugMainModel;

//    public int backgroundColor;

    public DateModel() {
    }

//    public DateModel(String day, int color, Calendar calendar) {
//        this.day = day;
//        this.calendar = calendar;
//        this.textColor = color;
//    }

    public DateModel(String day, int textColor, boolean isDayOfWeek, boolean isClick, Calendar calendar) {
//        this.backgroundColor = backgroundColor;
        this.isDayOfWeek = isDayOfWeek;
        this.isEnableClick = isClick;
        this.day = day;
        this.calendar = calendar;
        this.textColor = textColor;
    }

    public DateModel(String day, int textColor, Calendar calendar) {
//        this.backgroundColor = backgroundColor;
        this.isDayOfWeek = false;
        this.isEnableClick = true;
        this.day = day;
        this.calendar = calendar;
        this.textColor = textColor;
    }

    @Override
    public String toString() {
        return "DateModel{" +
                "isDayOfWeek=" + isDayOfWeek +
                ", isEnableClick=" + isEnableClick +
                ", day='" + day + '\'' +
                ", calendar=" + calendar +
                ", textColor=" + textColor +
                ", isEvent=" + isEvent +
                ", drugDay='" + drugDay + '\'' +
                ", drugWeek='" + drugWeek + '\'' +
                ", isToday=" + isToday +
                ", drugMainModel=" + drugMainModel +
                '}';
    }
}
