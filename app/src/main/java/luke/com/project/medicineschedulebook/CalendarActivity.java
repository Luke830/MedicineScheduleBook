package luke.com.project.medicineschedulebook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by itsm02 on 2017. 2. 22..
 */


public class CalendarActivity extends AppCompatActivity {

    ViewPager pager;
    PagerAdapter pagerAdapter;
    //    private ArrayList<MonthGridView> arrayList;
    private ArrayList<CustomCalendar> arrayList;

    private int MAX_INTERVER = 12 * 10;
//    private int MAX_INTERVER = 2;

//    public Calendar frontCal, lastCal;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        Log.e("DEBUG", "onCreate onCreate onCreate");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar);


        arrayList = new ArrayList<CustomCalendar>();
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

//                if (2 > position) {


//                    frontCal.add(Calendar.MONTH, -1);
//
//                    Calendar calendar = (Calendar) frontCal.clone();
//                    MonthGridView monthGridView = new MonthGridView();
//                    monthGridView.setmCal(calendar);
//                    monthGridView.setTodayMonth(false);
//                    arrayList.add(0, monthGridView);


//                    pagerAdapter.notifyDataSetChanged();
//                pager.setCurrentItem(pager.getCurrentItem() + 1, true);

//                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (arrayList != null) {
            arrayList.clear();
            arrayList = null;
        }
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            Log.e("DEBUG", "getItem = " + position);
            MonthGridView monthGridView = new MonthGridView();
            monthGridView.setmCal(arrayList.get(position).calendar);
            monthGridView.setTodayMonth(arrayList.get(position).isToday);
            return monthGridView;
//            return arrayList.get(position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            Log.e("DEBUG", "instantiateItem = " + position);
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.e("DEBUG", "destroyItem = " + position);
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

