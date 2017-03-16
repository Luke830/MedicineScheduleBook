package luke.com.project.medicineschedulebook;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by itsm02 on 2017. 2. 22..
 */

public class MonthGridView extends Fragment {

    /**
     * 연/월 텍스트뷰
     */

    private TextView tvDate;

    /**
     * 그리드뷰 어댑터
     */

    private GridAdapter gridAdapter;


    /**
     * 일 저장 할 리스트
     */

    private ArrayList<DateModel> dayList;


    /**
     * 그리드뷰
     */

    private GridView gridView;

    private boolean isTodayMonth;

    public boolean isTodayMonth() {
        return isTodayMonth;
    }

    public void setTodayMonth(boolean todayMonth) {
        isTodayMonth = todayMonth;
    }

    /**
     * 캘린더 변수
     */

    private Calendar mCal;

    private DiaryDbAdapter mDbHelper;
    private Cursor mDiaryCursor;

    private HashMap<String, EventModel> hashMap;

    public Calendar getmCal() {
        return mCal;
    }

    public void setmCal(Calendar mCal) {
        this.mCal = mCal;
    }

    private String key;

    public MonthGridView() {
        super();
    }

    @Override
    public void onStart() {
        super.onStart();
        Kog.e("");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.grid_activity_main, container, false);

        tvDate = (TextView) view.findViewById(R.id.tv_date);
        gridView = (GridView) view.findViewById(R.id.gridview);

        // 오늘에 날짜를 세팅 해준다.
        long now = System.currentTimeMillis();
        final Date date = new Date(now);
        //연,월,일을 따로 저장
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        //현재 날짜 텍스트뷰에 뿌려줌
        tvDate.setText(mCal.get(Calendar.YEAR) + " / " + String.format("%02d", (mCal.get(Calendar.MONTH) + 1)));

        dayList = new ArrayList<DateModel>();
        loadDB();

        gridAdapter = new GridAdapter(getActivity().getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);

        ViewTreeObserver vto = gridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = gridView.getWidth();
                gridView.setColumnWidth(width / 7);
                gridView.invalidate();
            }
        });


        return view;

    }

    public void loadDB() {
        mDbHelper = new DiaryDbAdapter(getActivity());
        mDbHelper.open();

        hashMap = new HashMap<String, EventModel>();
        Cursor cursor = mDbHelper.getMonthNotes(Utils.getCurYearMonth(mCal));
        int cnt = cursor.getCount();
        Log.d("DEBUG", "cnt = " + cnt);

        while (cursor.moveToNext()) {
            String cDate = cursor.getString(0);
//            String title = cursor.getString(1);
            String body = cursor.getString(1);
            String created = cursor.getString(2);

            EventModel eventModel = new EventModel(cDate, body, created);
            hashMap.put(cDate.substring(6, 8), eventModel);
            Log.d("DEBUG", "target sAllDate = " + cDate + " " + cDate.substring(6, 8) + " body = " + body + " created = " + created);
        }
        cursor.close();
        mDbHelper.close();

        //gridview 요일 표시

        dayList.add(new DateModel("일", Color.rgb(255, 0, 0), true, false, null));
        dayList.add(new DateModel("월", Color.rgb(0, 0, 0), true, false, null));
        dayList.add(new DateModel("화", Color.rgb(0, 0, 0), true, false, null));
        dayList.add(new DateModel("수", Color.rgb(0, 0, 0), true, false, null));
        dayList.add(new DateModel("목", Color.rgb(0, 0, 0), true, false, null));
        dayList.add(new DateModel("금", Color.rgb(0, 0, 0), true, false, null));
        dayList.add(new DateModel("토", Color.rgb(0, 0, 255), true, false, null));

        String total3 = mCal.get(Calendar.YEAR) + " " + mCal.get(Calendar.MONTH) + " " + mCal.get(Calendar.DATE);
        mCal.set(Calendar.DATE, 1);
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        //1일 - 요일 매칭 시키기 위해 공백 add
        for (int i = 1; i < dayNum; i++) {
            dayList.add(new DateModel("", Color.rgb(0, 0, 0), false, false, null));
        }

        setCalendarDate(mCal.get(Calendar.MONTH));
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    /**
     * 해당 월에 표시할 일 수 구함
     *
     * @param month
     */
    private void setCalendarDate(int month) {
//        mCal.set(Calendar.MONTH, month);
        String total = mCal.get(Calendar.YEAR) + " " + mCal.get(Calendar.MONTH) + " " + mCal.get(Calendar.DATE);
//        Log.d("DEBUG", "2 total = " + total);
        int iDay = Calendar.getInstance().get(Calendar.DATE);

        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {

            int day = i + 1;
            mCal.set(Calendar.DATE, day);

            DateModel dateModel = new DateModel();
            dateModel.day = ("" + day);

            int weekDay = mCal.get(Calendar.DAY_OF_WEEK);

//            Log.d("DEBUG", "weekDay = " + weekDay);

            if (isTodayMonth && day == iDay) {
                dateModel.textColor = Color.rgb(0, 200, 0);
                dateModel.isToday = true;
            } else if (weekDay == Calendar.SATURDAY) {
                dateModel.textColor = Color.rgb(0, 0, 255);
            } else if (weekDay == Calendar.SUNDAY) {
                dateModel.textColor = Color.rgb(255, 0, 0);
            } else {
                dateModel.textColor = Color.rgb(0, 0, 0);
            }

            dateModel.isEnableClick = true;
            dateModel.isDayOfWeek = false;

            key = String.valueOf(String.format("%02d", day));
            if (hashMap.containsKey(key)) {
                dateModel.sBody = hashMap.get(key).sBody;
                dateModel.drugMainModel = new Gson().fromJson(dateModel.sBody, DrugMainModel.class);

                if (dateModel.drugMainModel.dayDrugList != null && dateModel.drugMainModel.dayDrugList.size() > 0
                        || dateModel.drugMainModel.weekDrugList != null && dateModel.drugMainModel.weekDrugList.size() > 0) {
                    dateModel.isEvent = true;
                } else {
                    dateModel.isEvent = false;
                }
            }
            dayList.add(dateModel);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Kog.e("requestCode = " + requestCode + " resultCode = " + resultCode);

        updateUI();

    }

    public void updateUI() {
        dayList.clear();
        loadDB();
        gridAdapter.notifyDataSetChanged();
    }

    /**
     * 그리드뷰 어댑터
     */

    private class GridAdapter extends BaseAdapter implements View.OnClickListener {


        private final List<DateModel> list;


        private final LayoutInflater inflater;


        /**
         * 생성자
         *
         * @param context
         * @param list
         */

        public GridAdapter(Context context, List<DateModel> list) {

            this.list = list;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }


        @Override

        public int getCount() {

            return list.size();

        }


        @Override

        public DateModel getItem(int position) {

            return list.get(position);

        }


        @Override

        public long getItemId(int position) {

            return position;

        }


        @Override

        public View getView(int position, View convertView, ViewGroup parent) {


            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
                holder = new ViewHolder();

                holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.layout_item);
                holder.tvDay = (TextView) convertView.findViewById(R.id.text_view_day);
                holder.tvWeek = (TextView) convertView.findViewById(R.id.text_view_week);
                holder.tvItemGridView = (CheckedTextView) convertView.findViewById(R.id.tv_item_gridview);


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvItemGridView.setText("" + getItem(position).day);
            holder.tvItemGridView.setTextColor(getItem(position).textColor);

            if (getItem(position).drugMainModel != null) {

                if (getItem(position).drugMainModel.dayDrugList != null && getItem(position).drugMainModel.dayDrugList.size() > 0) {
                    holder.tvDay.setVisibility(View.VISIBLE);
                } else {
                    holder.tvDay.setVisibility(View.GONE);
                }

                if (getItem(position).drugMainModel.weekDrugList != null && getItem(position).drugMainModel.weekDrugList.size() > 0) {
                    holder.tvWeek.setVisibility(View.VISIBLE);
                } else {
                    holder.tvWeek.setVisibility(View.GONE);
                }

            } else {
                holder.tvDay.setVisibility(View.GONE);
                holder.tvWeek.setVisibility(View.GONE);
            }


//            holder.tvItemGridView.setC
//            holder.linearLayout.setEnabled(getItem(position).isEnableClick);
            if (getItem(position).isEnableClick) {
//                holder.tvDay.setVisibility(View.VISIBLE);
                holder.linearLayout.setTag(R.id.click_id, position);
                holder.linearLayout.setOnClickListener(this);

                if (getItem(position).isToday) {
//                    holder.tvItemGridView.setBackgroundResource(R.drawable.list_selector4);
                } else {
//                    holder.tvItemGridView.setBackgroundResource(R.drawable.list_selector);
                }

                holder.linearLayout.setGravity(Gravity.NO_GRAVITY);
            } else {
                holder.linearLayout.setGravity(Gravity.CENTER);
                holder.tvDay.setVisibility(View.GONE);
                holder.tvWeek.setVisibility(View.GONE);
//                holder.tvItemGridView.setBackgroundResource(R.drawable.list_selector2);
            }

//            if (getItem(position).isToday) {
//                holder.tvItemGridView.setTextSize(dpToPx(20));
//            } else {
//                holder.tvItemGridView.setTextSize(dpToPx(20));
//            }

            if (getItem(position).isToday) {

                if (getItem(position).isEvent) {
//                holder.tvItemGridView.setChecked(true);

                    holder.linearLayout.setBackgroundResource(R.drawable.item_border_selected3);
                } else {
//                holder.tvItemGridView.setChecked(false);

                    holder.linearLayout.setBackgroundResource(R.drawable.item_border4);
                }

            } else {

                if (getItem(position).isEvent) {
//                holder.tvItemGridView.setChecked(true);

                    holder.linearLayout.setBackgroundResource(R.drawable.item_border_selected2);
                } else {
//                holder.tvItemGridView.setChecked(false);

                    holder.linearLayout.setBackgroundResource(R.drawable.item_border);
                }

            }

            int a = getCount() % 7;
            int b = getCount() / 7;

            holder.linearLayout.getLayoutParams().height = gridView.getHeight() / (a == 0 ? b : b + 1);

            return convertView;

        }

        @Override
        public void onClick(View v) {

            final int pos = (int) v.getTag(R.id.click_id);
            Log.e("DEBUG", "pos = " + pos);
            final DateModel dateModel = gridAdapter.getItem(pos);
            Log.e("DEBUG", "dateModel = " + dateModel);

            final String selectDay = mCal.get(Calendar.YEAR) + ""
                    + String.format("%02d", (mCal.get(Calendar.MONTH) + 1))
                    + "" + String.format("%02d", Integer.parseInt(dateModel.day));

            final String selectTitle = mCal.get(Calendar.YEAR) + "년 "
                    + String.format("%02d", (mCal.get(Calendar.MONTH) + 1))
                    + "월 " + String.format("%02d", Integer.parseInt(dateModel.day))
                    + "일 ";

//            if (dateModel.isEvent) {
//
////                dateModel.isEvent = false;
////                mDbHelper.open();
////                boolean rmResult = mDbHelper.deleteDiary(allDay);
////                mDbHelper.close();
////                Log.e("DEBUG", "rmResult = " + rmResult);
//
//                ViewUtil.addDiaryDialog(getActivity(), dateModel.sBody != null ? dateModel.sBody : null, selectTitle, "추가하기", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        DrugMainModel drugMainModel = (DrugMainModel) v.getTag(R.id.add_id);
//                        mDbHelper.open();
//                        if (drugMainModel != null) {
//                            Log.e("DEBUG", drugMainModel.toString());
//
//
//                            Log.e("DEBUG", " allDay = " + selectDay);
//                            String sBody = new Gson().toJson(drugMainModel, drugMainModel.getClass());
//                            boolean isResult = mDbHelper.updateDiary(selectDay, "aaa", sBody);
//
//                            dateModel.sBody = sBody;
//                            Log.e("DEBUG", "addResult = " + isResult);
////                            if (addResult > -1) {
////                                dateModel.isEvent = true;
////                            }
//
//                        } else {
//                            dateModel.isEvent = false;
//                            dateModel.sBody = null;
//                            boolean rmResult = mDbHelper.deleteDiary(selectDay);
//                            Log.e("DEBUG", "rmResult = " + rmResult);
//                        }
//                        mDbHelper.close();
//                        gridAdapter.notifyDataSetChanged();
//
//                    }
//                });
//
//                gridAdapter.notifyDataSetChanged();


//            } else {

            Intent intent = new Intent(getActivity(), ScheduleDrugActivity.class);
            intent.putExtra(Data.INTENT_SELECT_POS, pos);
            intent.putExtra(Data.INTENT_DATE_MSG, dateModel.sBody);
            intent.putExtra(Data.INTENT_SELECT_DATE, selectDay);
            intent.putExtra(Data.INTENT_SELECT_TITLE, selectTitle);

            intent.putExtra(Data.INTENT_SELECT_YEAR, mCal.get(Calendar.YEAR));
            intent.putExtra(Data.INTENT_SELECT_MONTH, mCal.get(Calendar.MONTH));
            intent.putExtra(Data.INTENT_SELECT_DAY, Integer.parseInt(dateModel.day));

            startActivityForResult(intent, 660);

//                ViewUtil.addDiaryDialog(getActivity(), dateModel.sBody != null ? dateModel.sBody : null, title, "추가하기", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        DrugMainModel drugMainModel = (DrugMainModel) v.getTag(R.id.add_id);
//                        if (drugMainModel != null) {
//                            Log.e("DEBUG", drugMainModel.toString());
//                            mDbHelper.open();
//                            Log.e("DEBUG", " allDay = " + allDay);
//                            String sBody = new Gson().toJson(drugMainModel, drugMainModel.getClass());
//                            long addResult = mDbHelper.createDiary(allDay, "aaa", sBody);
//                            mDbHelper.close();
//                            dateModel.sBody = sBody;
//                            Log.e("DEBUG", "addResult = " + addResult);
//                            if (addResult > -1) {
//                                dateModel.isEvent = true;
//                            }
//
//                            gridAdapter.notifyDataSetChanged();
//                        }
//
//                    }
//                });
//                ViewUtil.checkDiaryDialog(getActivity(), "bbb", "투약 하셨나요?", "투약 일정 추가/변경", null, null);
//            }
//        }
        }


        private class ViewHolder {

            LinearLayout linearLayout;
            TextView tvDay;
            TextView tvWeek;
            CheckedTextView tvItemGridView;

        }
    }

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()
        );
    }

}
