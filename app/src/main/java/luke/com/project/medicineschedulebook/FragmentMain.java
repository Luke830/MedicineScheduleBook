package luke.com.project.medicineschedulebook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class FragmentMain extends Fragment implements View.OnClickListener {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private ArrayList<CustomCalendar> arrayList;
    private int MAX_INTERVER = 12 * 10;
    public HashMap<Integer, MonthGridView> hashMap;

    private TextView tvDate;

    public FragmentMain() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Kog.e("");

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        view.findViewById(R.id.image_button_menu).setOnClickListener(this);

        tvDate = (TextView) view.findViewById(R.id.tv_date);

        //현재 날짜 텍스트뷰에 뿌려줌
        Calendar cal = Calendar.getInstance();
        tvDate.setText(cal.get(Calendar.YEAR) + " / " + String.format("%02d", (cal.get(Calendar.MONTH) + 1)));

        hashMap = new HashMap<Integer, MonthGridView>();
        arrayList = new ArrayList<CustomCalendar>();

        load();

        pager = (ViewPager) view.findViewById(R.id.pager);
        pagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager());
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
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
    public void onDestroy() {
        super.onDestroy();

        if (arrayList != null) {
            arrayList.clear();
            arrayList = null;
        }
    }

    @Override
    public void onClick(View view) {

        if (((CalendarActivity) getActivity()).drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            ((CalendarActivity) getActivity()).drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            ((CalendarActivity) getActivity()).drawerLayout.openDrawer(Gravity.LEFT);
        }
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
        public Parcelable saveState() {
            // return super.saveState();
            return null; // 예외처리 코드 꼭 필요한 코드 절대 빼지 말것...
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // super.restoreState(arg0, arg1); //예외처리 코드 꼭 필요한 코드 절대 빼지 말것...
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
