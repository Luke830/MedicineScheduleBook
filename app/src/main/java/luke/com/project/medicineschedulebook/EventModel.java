package luke.com.project.medicineschedulebook;

/**
 * Created by itsm02 on 2017. 2. 23..
 */

public class EventModel {

    public String sDate;
    public String sBody;
    public String sCreated;

    public EventModel(String sDate, String sBody, String sCreated) {
        this.sDate = sDate;
        this.sBody = sBody;
        this.sCreated = sCreated;
    }

    @Override
    public String toString() {
        return "EventModel{" +
                "sDate='" + sDate + '\'' +
                ", sBody='" + sBody + '\'' +
                ", sCreated='" + sCreated + '\'' +
                '}';
    }
}
