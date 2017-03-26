package luke.com.project.medicineschedulebook;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

public class FragmentChar extends Fragment implements View.OnClickListener {

    public BarChart mChart;
    private DiaryDbAdapter mDbHelper;

    public Calendar currCal;
    public TextView chartTextView;

    public ArrayList<String> arrayList;
    private HashMap<String, EventModel> hashMap;

    public FragmentChar() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_char, container, false);

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_date);
        tvTitle.setText("투약 성취도 그래프");

        view.findViewById(R.id.image_button_menu).setOnClickListener(this);

        arrayList = new ArrayList<String>();
        hashMap = new HashMap<String, EventModel>();

        mChart = (BarChart) view.findViewById(R.id.chart1);
        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);
        mChart.getDescription().setEnabled(false);
        mChart.setPinchZoom(false);
//        mChart.setVisibleXRangeMinimum(100);
//        mChart.setVisibleYRange(0, 100);
//        mChart.setMaxVisibleValueCount(100);
        mChart.setDrawGridBackground(false);
        mChart.getAxisLeft().setAxisMinimum(0);
        mChart.getAxisLeft().setAxisMaximum(101);
        mChart.getAxisRight().setEnabled(false);

        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(mChart, arrayList);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity((int) 1); // only intervals of 1 day
        xAxis.setLabelCount(7);
//        xAxis.setAxisMinimum(0);
//        xAxis.setAxisMaximum(100);
        xAxis.setValueFormatter(xAxisFormatter);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);


//        XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
//        mv.setChartView(mChart); // For bounds control
//        mChart.setMarker(mv); // Set the marker to the chart

        view.findViewById(R.id.before_button).setOnClickListener(this);
        view.findViewById(R.id.next_button).setOnClickListener(this);

        chartTextView = (TextView) view.findViewById(R.id.text_view_char_week);

        currCal = Calendar.getInstance();
        String[] curArray = currentWeek(currCal);

//        setData(curArray);

        return view;
    }

    public String[] currentWeek(Calendar currCal) {

        arrayList.clear();
        String[] array = new String[7];

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Calendar cal = (Calendar) currCal.clone();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
//        System.out.println("dayOfWeek = " + dayOfWeek);

        cal.add(Calendar.DAY_OF_MONTH, (-(dayOfWeek - 1)));
        for (int i = 0; i < 7; i++) {
//            System.out.println(sdf.format(cal.getTime()));

            array[i] = sdf.format(cal.getTime());
            arrayList.add(array[i]);

            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        chartTextView.setText(getDateFormat(array[0]) + " ~ " + getDateFormat(array[array.length - 1]));
        loadDB(array[0], array[array.length - 1]);

        setData(array);
        return array;
    }

    public void beforeWeek() {

        currCal.add(Calendar.DATE, -7);
        currentWeek(currCal);
    }

    public void nextWeek() {

        currCal.add(Calendar.DATE, +7);
        currentWeek(currCal);

    }

    public String getDateFormat(String day) {
        return day.substring(0, 4) + "." + day.substring(4, 6) + "." + day.substring(6, 8);
    }

    public void loadDB(String start, String end) {
        mDbHelper = new DiaryDbAdapter(getActivity());
        mDbHelper.open();

        Kog.e("DEBUG", "start = " + start + " end = " + end);

        Cursor cursor = mDbHelper.getMonthNotesBetween(start, end);
        int cnt = cursor.getCount();
        Kog.e("DEBUG", "cnt = " + cnt);

        while (cursor.moveToNext()) {

            String cDate = cursor.getString(0);
            String day = cursor.getString(1);
            String week = cursor.getString(2);
            String created = cursor.getString(3);

            EventModel eventModel = new EventModel(cDate, day, week, created);
            hashMap.put(cDate, eventModel);

            Kog.e("DEBUG", cDate + " " + " day = " + day + " week = " + week + " created = " + created);
        }

        cursor.close();
        mDbHelper.close();

    }

    private void setData(String[] dayArray) {
        mChart.clear();
        mChart.setData(null);

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
//        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();

        for (int i = 0; i < 7; i++) {

            float totalCnt = 0, doneCnt = 0;

//            float gg = Float.valueOf(dayArray[i].substring(6, 8));
//            Kog.e(" gg = " + gg);

            if (hashMap.containsKey(dayArray[i])) {
                EventModel eventModel = hashMap.get(dayArray[i]);

                DrugList drugListDay = new Gson().fromJson(eventModel.sDay, DrugList.class);
                if (drugListDay != null) {

                    if (drugListDay.list != null && drugListDay.list.size() > 0) {

                        DrugModel drugModel = drugListDay.list.get(0);

                        if (drugModel != null) {

                            if (drugModel.aDrugList != null) {

                                totalCnt += drugModel.aDrugList.size();

                                for (DrugModel2 drugModel2 : drugModel.aDrugList) {

                                    doneCnt += drugModel2.isDone == true ? 1 : 0;

                                }

                            }

                            if (drugModel.bDrugList != null) {

                                totalCnt += drugModel.bDrugList.size();

                                for (DrugModel2 drugModel2 : drugModel.bDrugList) {

                                    doneCnt += drugModel2.isDone == true ? 1 : 0;

                                }

                            }

                        }

                    }
                }

                DrugList drugListWeek = new Gson().fromJson(eventModel.sWeek, DrugList.class);
                if (drugListWeek != null) {

                    if (drugListWeek.list != null && drugListWeek.list.size() > 0) {

                        DrugModel drugModel = drugListWeek.list.get(0);

                        if (drugModel != null) {

                            if (drugModel.aDrugList != null) {

                                totalCnt += drugModel.aDrugList.size();

                                for (DrugModel2 drugModel2 : drugModel.aDrugList) {

                                    doneCnt += drugModel2.isDone == true ? 1 : 0;

                                }

                            }

                            if (drugModel.bDrugList != null) {

                                totalCnt += drugModel.bDrugList.size();

                                for (DrugModel2 drugModel2 : drugModel.bDrugList) {

                                    doneCnt += drugModel2.isDone == true ? 1 : 0;

                                }

                            }

                        }

                    }
                }

//                Kog.e("@@ totalCnt = " + totalCnt);
//                Kog.e("@@ doneCnt = " + doneCnt);

//                DrugList drugListWeek = new Gson().fromJson(eventModel.sWeek, DrugList.class);

                yVals1.add(new BarEntry(i, (int) (doneCnt / totalCnt * 100)));
            } else {
                yVals1.add(new BarEntry(i, 0));
            }

//            yVals2.add(new BarEntry(0, 10));

        }

        BarDataSet set1;
//        BarDataSet set2;

//        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
//
//            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
//            set1.setValues(yVals1);
//            mChart.getData().notifyDataChanged();
//            mChart.notifyDataSetChanged();
//
//        } else {


        set1 = new BarDataSet(yVals1, "투약성취도(%)");
//        set2 = new BarDataSet(yVals2, "매주");

        set1.setDrawIcons(false);
//        set2.setDrawIcons(false);

        final int[] MATERIAL_COLORS1 = {

                rgb("#2ecc71")

        };
        final int[] MATERIAL_COLORS2 = {

                rgb("#ffb51c")

        };

        set1.setColors(MATERIAL_COLORS1);
//        set2.setColors(MATERIAL_COLORS2);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
//        dataSets.add(set2);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
//            data.setValueTypeface(mTfLight);
        data.setBarWidth(0.45f);


        mChart.setData(data);
        mChart.getData().notifyDataChanged();
        mChart.notifyDataSetChanged();

//        mChart.groupBars(-1f, 0f, 0.09f);

//        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.before_button:
                beforeWeek();
                break;

            case R.id.next_button:
                nextWeek();
                break;

            case R.id.image_button_menu:
                if (((CalendarActivity) getActivity()).drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    ((CalendarActivity) getActivity()).drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    ((CalendarActivity) getActivity()).drawerLayout.openDrawer(Gravity.LEFT);
                }
                break;
        }

    }
}
