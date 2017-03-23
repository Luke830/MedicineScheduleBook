package luke.com.project.medicineschedulebook;

import android.content.Intent;
import android.os.Bundle;
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


public class CalendarActivity extends FragmentActivity implements View.OnClickListener {

    ViewPager pager;
    PagerAdapter pagerAdapter;
    //    private ArrayList<MonthGridView> arrayList;
    private ArrayList<CustomCalendar> arrayList;

    private int MAX_INTERVER = 12 * 10;
//    private int MAX_INTERVER = 2;

//    public Calendar frontCal, lastCal;

    public HashMap<Integer, MonthGridView> hashMap;

    public DrawerLayout drawerLayout;
    private TextView tvDate;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        Log.e("DEBUG", "onCreate onCreate onCreate");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        findViewById(R.id.image_button_menu).setOnClickListener(this);

        tvDate = (TextView) findViewById(R.id.tv_date);

        //현재 날짜 텍스트뷰에 뿌려줌
        Calendar cal = Calendar.getInstance();
        tvDate.setText(cal.get(Calendar.YEAR) + " / " + String.format("%02d", (cal.get(Calendar.MONTH) + 1)));

//        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
//        drawerLayout.openDrawer(GravityCompat.START);

        Utils.replaceFragement(this, R.id.right_drawer_fragment_container, new FragmentLeftMenu());

        hashMap = new HashMap<Integer, MonthGridView>();
        arrayList = new ArrayList<CustomCalendar>();
        load();

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(MAX_INTERVER);

        pager.setOffscreenPageLimit(1);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                CustomCalendar customCalendar = arrayList.get(position);
                Calendar cal = customCalendar.calendar;
                tvDate.setText(cal.get(Calendar.YEAR) + " / " + String.format("%02d", (cal.get(Calendar.MONTH) + 1)));


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    public void load() {
        for (int i = MAX_INTERVER; i > 0; i--) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -i);
            calendar.add(Calendar.DATE, 1);

            CustomCalendar customCalendar = new CustomCalendar();
            customCalendar.calendar = calendar;
            customCalendar.isToday = false;

            arrayList.add(customCalendar);

        }

        {
            Calendar cal = Calendar.getInstance();
            CustomCalendar customCalendar = new CustomCalendar();
            customCalendar.calendar = cal;
            customCalendar.isToday = true;
            arrayList.add(customCalendar);
        }

        for (int i = 1; i <= MAX_INTERVER; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, +i);
            calendar.add(Calendar.DATE, 1);

            CustomCalendar customCalendar = new CustomCalendar();
            customCalendar.calendar = calendar;
            customCalendar.isToday = false;

            arrayList.add(customCalendar);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (arrayList != null) {
            arrayList.clear();
            arrayList = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Kog.e("");

        MonthGridView monthGridView = hashMap.get(pager.getCurrentItem() + 1);
        if (monthGridView != null) {
            monthGridView.updateUI();
        }

        monthGridView = hashMap.get(pager.getCurrentItem() - 1);
        if (monthGridView != null) {
            monthGridView.updateUI();
        }

    }

    @Override
    public void onClick(View view) {

        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
//            Log.e("DEBUG", "getItem = " + position);
            MonthGridView monthGridView = new MonthGridView();
            monthGridView.setmCal(arrayList.get(position).calendar);
            monthGridView.setTodayMonth(arrayList.get(position).isToday);
            return monthGridView;
//            return arrayList.get(position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

//            Log.e("DEBUG", "instantiateItem = " + position);
            Object object = super.instantiateItem(container, position);
//            Log.e("DEBUG", "object = " + object);
            hashMap.put(position, (MonthGridView) object);
            return object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            Log.e("DEBUG", "destroyItem = " + position);
            super.destroyItem(container, position, object);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
//            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }
    }


}

