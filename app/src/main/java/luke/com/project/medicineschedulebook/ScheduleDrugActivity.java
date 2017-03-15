package luke.com.project.medicineschedulebook;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.gson.Gson;

public class ScheduleDrugActivity extends FragmentActivity {

    public int pos;
    public String msg;
    public String title;
    public String date;
    public DrugMainModel drugMainModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_drug);

        pos = getIntent().getIntExtra(Data.INTENT_SELECT_POS, -1);
        msg = getIntent().getStringExtra(Data.INTENT_DATE_MSG);
        title = getIntent().getStringExtra(Data.INTENT_SELECT_TITLE);
        date = getIntent().getStringExtra(Data.INTENT_SELECT_DATE);

        drugMainModel = new Gson().fromJson(msg, DrugMainModel.class);

        Utils.replaceFragement(this, R.id.frame_layout, new FragmentScheduleDrug());

    }
}
