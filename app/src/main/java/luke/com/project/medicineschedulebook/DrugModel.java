package luke.com.project.medicineschedulebook;

import java.util.ArrayList;

/**
 * Created by itsm02 on 2017. 2. 25..
 */

public class DrugModel {

    // 주사제
    public ArrayList<DrugModel2> aDrugList;

    // 경구투약제
    public ArrayList<DrugModel2> bDrugList;

    @Override
    public String toString() {
        return "DrugModel{" +
                "aDrugList=" + aDrugList +
                ", bDrugList=" + bDrugList +
                '}';
    }
}
