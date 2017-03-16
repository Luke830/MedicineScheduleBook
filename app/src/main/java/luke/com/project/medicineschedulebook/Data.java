package luke.com.project.medicineschedulebook;

/**
 * Created by firefox on 2017. 3. 15..
 */

public class Data {
    public static String INTENT_SELECT_POS = "INTENT_SELECT_POS";
    public static String INTENT_DATE_MSG = "INTENT_DATE_MSG";
    public static String INTENT_SELECT_DATE = "INTENT_SELECT_DATE";
    public static String INTENT_SELECT_TITLE = "INTENT_SELECT_TITLE";

    public static String INTENT_SELECT_YEAR = "INTENT_SELECT_YEAR";
    public static String INTENT_SELECT_MONTH = "INTENT_SELECT_MONTH";
    public static String INTENT_SELECT_DAY = "INTENT_SELECT_DAY";

    public static enum DRUG_TYPE {
        // 항류마티스약, 골다공증약
        // 메토트랙세이트, 엔브렐, 휴미라, 심포니, 오렌시아
        A, B, C, D, E, F, G
    }

}
