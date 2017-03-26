package luke.com.project.medicineschedulebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by itsm02 on 2017. 2. 22..
 */


public class CalendarActivity extends FragmentActivity {

    public DrawerLayout drawerLayout;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Utils.replaceFragement(this, R.id.main_fragment_container, new FragmentMain());
        Utils.replaceFragement(this, R.id.right_drawer_fragment_container, new FragmentLeftMenu());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Kog.i("onActivityResult requestCode = " + requestCode + " resultCode = " + resultCode);

        Fragment fragment = null;
        fragment = getSupportFragmentManager().findFragmentByTag(FragmentMain.class.getName());
        if (fragment != null && fragment instanceof FragmentMain) {
            ((FragmentMain) fragment).onActivityResult(requestCode, resultCode, data);
        }

    }
}

