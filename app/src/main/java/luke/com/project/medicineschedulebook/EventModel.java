package luke.com.project.medicineschedulebook;

/**
 * Created by itsm02 on 2017. 2. 23..
 */

public class EventModel {

    public String sDate;
    public String sDay;
    public String sWeek;
    public String sCreated;

    public EventModel(String sDate, String sDay, String sWeek, String sCreated) {
        this.sDate = sDate;
        this.sDay = sDay;
        this.sWeek = sWeek;
        this.sCreated = sCreated;
    }

    @Override
    public String toString() {
        return "EventModel{" +
                "sDate='" + sDate + '\'' +
                ", sDay='" + sDay + '\'' +
                ", sWeek='" + sWeek + '\'' +
                ", sCreated='" + sCreated + '\'' +
                '}';
    }

}
