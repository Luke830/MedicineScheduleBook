package luke.com.project.medicineschedulebook;

/**
 * Created by itsm02 on 2017. 2. 25..
 */

public class DrugModel2 {

    // 약
    public Data.DRUG_TYPE type;
    // 약 투여 여부
    public boolean isDone;

    public DrugModel2(Data.DRUG_TYPE type, boolean isDone) {
        this.type = type;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "DrugModel2{" +
                "type=" + type +
                ", isDone=" + isDone +
                '}';
    }
}
