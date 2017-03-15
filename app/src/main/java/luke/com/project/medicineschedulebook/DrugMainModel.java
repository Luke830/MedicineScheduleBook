package luke.com.project.medicineschedulebook;

import java.util.ArrayList;

/**
 * Created by itsm02 on 2017. 2. 25..
 */

public class DrugMainModel {

    public static enum DRUG_TYPE {
        // 항류마티스약, 골다공증약
        // 메토트랙세이트, 엔브렐, 휴미라, 심포니, 오렌시아
        A, B, C, D, E, F, G
    }

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
