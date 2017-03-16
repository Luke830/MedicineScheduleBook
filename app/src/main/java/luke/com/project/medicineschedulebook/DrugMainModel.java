package luke.com.project.medicineschedulebook;

import java.util.ArrayList;

/**
 * Created by itsm02 on 2017. 2. 25..
 */

public class DrugMainModel {

    public boolean isDay;
    public boolean isWeek;

    public ArrayList<DrugModel> dayDrugList;
    public ArrayList<DrugModel> weekDrugList;

    @Override
    public String toString() {
        return "DrugMainModel{" +
                "isDay=" + isDay +
                ", isWeek=" + isWeek +
                ", dayDrugList=" + dayDrugList +
                ", weekDrugList=" + weekDrugList +
                '}';
    }
}
