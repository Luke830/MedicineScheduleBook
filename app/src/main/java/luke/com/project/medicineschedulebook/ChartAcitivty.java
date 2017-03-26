package luke.com.project.medicineschedulebook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by itsm02 on 2017. 3. 24..
 */
public class ChartAcitivty extends FragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char);

        Utils.replaceFragement(this, R.id.frame_layout_char, new FragmentChar());
    }

}
