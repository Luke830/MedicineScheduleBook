package luke.com.project.medicineschedulebook;

/**
 * Created by itsm02 on 2017. 2. 25..
 */

public class DrugMainModel {

    public DrugList drugListDay;
    public DrugList drugListWeek;

    @Override
    public String toString() {
        return "DrugMainModel{" +
                "drugListDay=" + drugListDay +
                ", drugListWeek=" + drugListWeek +
                '}';
    }
}
