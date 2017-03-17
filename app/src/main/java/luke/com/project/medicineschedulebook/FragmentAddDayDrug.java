package luke.com.project.medicineschedulebook;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;

public class FragmentAddDayDrug extends Fragment implements View.OnClickListener {

    public CheckedTextView[] drugCheckedTextView = new CheckedTextView[4];

    public FragmentAddDayDrug() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add_day_drug, container, false);

        view.findViewById(R.id.add_day_button).setOnClickListener(this);


        drugCheckedTextView[0] = (CheckedTextView) view.findViewById(R.id.drug1);
        drugCheckedTextView[1] = (CheckedTextView) view.findViewById(R.id.drug2);
        drugCheckedTextView[2] = (CheckedTextView) view.findViewById(R.id.drug3);
        drugCheckedTextView[3] = (CheckedTextView) view.findViewById(R.id.drug4);

        if (((ScheduleDrugActivity) getActivity()).drugMainModel != null) {

            ArrayList<DrugModel> arrayList = ((ScheduleDrugActivity) getActivity()).drugMainModel.dayDrugList;

            if (arrayList != null) {
                DrugModel drugModel = arrayList.get(0);
                ArrayList<DrugModel2> aArrayList = drugModel.aDrugList;
                ArrayList<DrugModel2> bArrayList = drugModel.bDrugList;

                if (aArrayList != null) {

                    for (DrugModel2 drugModel2 : aArrayList) {
                        if (Data.DRUG_TYPE.A.equals(drugModel2.type)) {
                            drugCheckedTextView[0].setChecked(true);
                        } else if (Data.DRUG_TYPE.B.equals(drugModel2.type)) {
                            drugCheckedTextView[1].setChecked(true);
                        }
                    }


                }

                if (bArrayList != null) {
                    for (DrugModel2 drugModel2 : bArrayList) {
                        if (Data.DRUG_TYPE.A.equals(drugModel2.type)) {
                            drugCheckedTextView[2].setChecked(true);
                        } else if (Data.DRUG_TYPE.B.equals(drugModel2.type)) {
                            drugCheckedTextView[3].setChecked(true);
                        }
                    }
                }

            }
        }

        drugCheckedTextView[0].setOnClickListener(this);
        drugCheckedTextView[1].setOnClickListener(this);
        drugCheckedTextView[2].setOnClickListener(this);
        drugCheckedTextView[3].setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {

        if (view instanceof CheckedTextView) {

            CheckedTextView checkedTextView = (CheckedTextView) view;
            checkedTextView.setChecked(!checkedTextView.isChecked());

        } else {
            switch (view.getId()) {
                case R.id.add_day_button:
                    updateDB();
                    break;
            }
        }

    }

    public void updateDB() {
        DiaryDbAdapter mDbHelper = new DiaryDbAdapter(getActivity());
        mDbHelper.open();

        DrugModel drugModel = null;

        if (drugCheckedTextView[0].isChecked() || drugCheckedTextView[1].isChecked()) {
            if (drugModel == null) {
                drugModel = new DrugModel();
            }

            drugModel.aDrugList = new ArrayList<DrugModel2>();
            if (drugCheckedTextView[0].isChecked()) {
                DrugModel2 drugModel2 = new DrugModel2(Data.DRUG_TYPE.A, false);
                drugModel.aDrugList.add(drugModel2);
            }
            if (drugCheckedTextView[1].isChecked()) {
                DrugModel2 drugModel2 = new DrugModel2(Data.DRUG_TYPE.B, false);
                drugModel.aDrugList.add(drugModel2);
            }
        }

        if (drugCheckedTextView[2].isChecked() || drugCheckedTextView[3].isChecked()) {
            if (drugModel == null) {
                drugModel = new DrugModel();
            }

            drugModel.bDrugList = new ArrayList<DrugModel2>();
            if (drugCheckedTextView[2].isChecked()) {
                DrugModel2 drugModel2 = new DrugModel2(Data.DRUG_TYPE.A, false);
                drugModel.bDrugList.add(drugModel2);
            }
            if (drugCheckedTextView[3].isChecked()) {
                DrugModel2 drugModel2 = new DrugModel2(Data.DRUG_TYPE.B, false);
                drugModel.bDrugList.add(drugModel2);
            }
        }

        ArrayList<DrugModel> arrayList = null;
        if (drugModel != null) {
            arrayList = new ArrayList<DrugModel>();
            arrayList.add(drugModel);
        }

        if (((ScheduleDrugActivity) getActivity()).drugMainModel == null) {
            ((ScheduleDrugActivity) getActivity()).drugMainModel = new DrugMainModel();
        }

        ((ScheduleDrugActivity) getActivity()).drugMainModel.dayDrugList = arrayList;

        // 오늘꺼 설정
        Cursor cursor = mDbHelper.getDayDiary(((ScheduleDrugActivity) getActivity()).date);
        if (cursor != null && cursor.getCount() > 0) {
            String sBody = new Gson().toJson(((ScheduleDrugActivity) getActivity()).drugMainModel, ((ScheduleDrugActivity) getActivity()).drugMainModel.getClass());
            boolean isResult = mDbHelper.updateDiary(((ScheduleDrugActivity) getActivity()).date, sBody);
            Kog.e("DEBUG", "isResult = " + isResult);
        } else {
            String sBody1 = new Gson().toJson(((ScheduleDrugActivity) getActivity()).drugMainModel, ((ScheduleDrugActivity) getActivity()).drugMainModel.getClass());
            long addResult = mDbHelper.createDiary(((ScheduleDrugActivity) getActivity()).date, sBody1);
            Kog.e("DEBUG", "addResult = " + addResult);
        }
        cursor.close();
        cursor = null;

        Cursor cursor2 = null;
        // 매일 설정..
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, ((ScheduleDrugActivity) getActivity()).year);
        calendar.set(Calendar.MONTH, ((ScheduleDrugActivity) getActivity()).month);
        calendar.set(Calendar.DATE, ((ScheduleDrugActivity) getActivity()).day);
        for (int i = 1; i <= 27; i++) {

            calendar.add(Calendar.DATE, +1);

//            CustomCalendar customCalendar = new CustomCalendar();
//            customCalendar.calendar = calendar;
//            customCalendar.isToday = false;

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int date = calendar.get(Calendar.DATE);

            final String selectDay = year + ""
                    + String.format("%02d", (month + 1))
                    + "" + String.format("%02d", date);

            Kog.e("year = " + year + " month = " + month + " date = " + date);

            cursor2 = mDbHelper.getDayDiary(selectDay);
            if (cursor2 != null && cursor2.getCount() > 0) {

                while (cursor2.moveToNext()) {

                    String cDate = cursor2.getString(0);
                    String data = cursor2.getString(1);
                    String created = cursor2.getString(2);

                    Kog.d("DEBUG", "target sAllDate = " + cDate + " " + cDate.substring(6, 8) + " data = " + data + " created = " + created);

                    DrugMainModel drugMainModel = new Gson().fromJson(data, DrugMainModel.class);

                    drugMainModel.dayDrugList = arrayList;
                    String sBody2 = new Gson().toJson(drugMainModel, ((ScheduleDrugActivity) getActivity()).drugMainModel.getClass());
                    boolean isResult = mDbHelper.updateDiary(selectDay, sBody2);
                    Kog.e("DEBUG", "isResult = " + isResult);

                }

            } else {
                String sBody2 = new Gson().toJson(((ScheduleDrugActivity) getActivity()).drugMainModel, ((ScheduleDrugActivity) getActivity()).drugMainModel.getClass());
                long addResult = mDbHelper.createDiary(selectDay, sBody2);
                Kog.e("DEBUG", "addResult = " + addResult);
            }

            cursor2.close();
            cursor2 = null;

        }

        mDbHelper.close();
        mDbHelper = null;

        Kog.e(((ScheduleDrugActivity) getActivity()).drugMainModel.toString());
        Kog.e(((ScheduleDrugActivity) getActivity()).date);


        Intent intent = new Intent();
        intent.putExtra(Data.INTENT_SELECT_POS, ((ScheduleDrugActivity) getActivity()).pos);
        intent.putExtra(Data.INTENT_DATE_MSG, new Gson().toJson(((ScheduleDrugActivity) getActivity()).drugMainModel));
        getActivity().setResult(777, intent);
        getActivity().finish();


    }
}
