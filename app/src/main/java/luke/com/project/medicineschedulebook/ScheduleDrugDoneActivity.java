package luke.com.project.medicineschedulebook;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.gson.Gson;

public class ScheduleDrugDoneActivity extends FragmentActivity {

    public int pos;

    public String drugDay;
    public String drugWeek;

    public String title;
    public String date;

    public int year, month, day;

    public DrugList drugListDay;
    public DrugList drugListWeek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_drug_done);

        pos = getIntent().getIntExtra(Data.INTENT_SELECT_POS, -1);

        drugDay = getIntent().getStringExtra(Data.INTENT_DRUG_DAY_MSG);
        drugWeek = getIntent().getStringExtra(Data.INTENT_DRUG_WEEK_MSG);

        title = getIntent().getStringExtra(Data.INTENT_SELECT_TITLE);
        date = getIntent().getStringExtra(Data.INTENT_SELECT_DATE);

        year = getIntent().getIntExtra(Data.INTENT_SELECT_YEAR, -1);
        month = getIntent().getIntExtra(Data.INTENT_SELECT_MONTH, -1);
        day = getIntent().getIntExtra(Data.INTENT_SELECT_DAY, -1);

        Kog.e(" date = " + date);

        if (drugDay != null) {
            drugListDay = new Gson().fromJson(drugDay, DrugList.class);
            if (drugListDay != null) {
                Kog.e(" drugListDay = " + drugListDay.toString());
            }
        }

        if (drugWeek != null) {
            drugListWeek = new Gson().fromJson(drugWeek, DrugList.class);
            if (drugListWeek != null) {
                Kog.e(" drugListWeek = " + drugListWeek.toString());
            }
        }

        Utils.replaceFragement(this, R.id.frame_layout_done, new FragmentScheduleDrugDone());

    }
}
