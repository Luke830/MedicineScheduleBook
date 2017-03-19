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

public class FragmentAddDayDrugDone extends Fragment implements View.OnClickListener {

    public CheckedTextView[] drugCheckedTextView = new CheckedTextView[4];

    public FragmentAddDayDrugDone() {
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

        View view = inflater.inflate(R.layout.fragment_add_day_drug_done, container, false);
        view.findViewById(R.id.done_add_day_button).setOnClickListener(this);

        drugCheckedTextView[0] = (CheckedTextView) view.findViewById(R.id.done_drug1);
        drugCheckedTextView[1] = (CheckedTextView) view.findViewById(R.id.done_drug2);
        drugCheckedTextView[2] = (CheckedTextView) view.findViewById(R.id.done_drug3);
        drugCheckedTextView[3] = (CheckedTextView) view.findViewById(R.id.done_drug4);

        for (int i = 0; i < drugCheckedTextView.length; i++) {
            drugCheckedTextView[i].setVisibility(View.GONE);
            drugCheckedTextView[i].setOnClickListener(this);
        }

        if (((ScheduleDrugDoneActivity) getActivity()).drugListDay != null) {

            ArrayList<DrugModel> arrayList = ((ScheduleDrugDoneActivity) getActivity()).drugListDay.list;

            if (arrayList != null) {
                DrugModel drugModel = arrayList.get(0);
                ArrayList<DrugModel2> aArrayList = drugModel.aDrugList;
                ArrayList<DrugModel2> bArrayList = drugModel.bDrugList;

                if (aArrayList != null) {

                    for (DrugModel2 drugModel2 : aArrayList) {

                        if (Data.DRUG_TYPE.A.equals(drugModel2.type)) {

                            drugCheckedTextView[0].setVisibility(View.VISIBLE);
                            if (drugModel2.isDone) {
                                drugCheckedTextView[0].setChecked(true);
                            }
                        } else if (Data.DRUG_TYPE.B.equals(drugModel2.type)) {

                            drugCheckedTextView[1].setVisibility(View.VISIBLE);

                            if (drugModel2.isDone) {
                                drugCheckedTextView[1].setChecked(true);
                            }
                        }

                    }


                }

                if (bArrayList != null) {
                    for (DrugModel2 drugModel2 : bArrayList) {
                        if (Data.DRUG_TYPE.A.equals(drugModel2.type)) {

                            drugCheckedTextView[2].setVisibility(View.VISIBLE);
                            if (drugModel2.isDone) {
                                drugCheckedTextView[2].setChecked(true);
                            }


                        } else if (Data.DRUG_TYPE.B.equals(drugModel2.type)) {

                            drugCheckedTextView[3].setVisibility(View.VISIBLE);
                            if (drugModel2.isDone) {
                                drugCheckedTextView[3].setChecked(true);
                            }

                        }
                    }
                }

            }
        }


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
                case R.id.done_add_day_button:
                    updateDB();
                    break;
            }
        }

    }

    public void updateDB() {
        DiaryDbAdapter mDbHelper = new DiaryDbAdapter(getActivity());
        mDbHelper.open();


        if (((ScheduleDrugDoneActivity) getActivity()).drugListDay != null) {

            ArrayList<DrugModel> arrayList = ((ScheduleDrugDoneActivity) getActivity()).drugListDay.list;

            if (arrayList != null) {
                DrugModel drugModel = arrayList.get(0);
                ArrayList<DrugModel2> aArrayList = drugModel.aDrugList;
                ArrayList<DrugModel2> bArrayList = drugModel.bDrugList;

                if (aArrayList != null) {

                    for (DrugModel2 drugModel2 : aArrayList) {

                        if (Data.DRUG_TYPE.A.equals(drugModel2.type)) {

                            drugModel2.isDone = drugCheckedTextView[0].isChecked();

//                            drugCheckedTextView[0].setVisibility(View.VISIBLE);
//                            if (drugModel2.isDone) {
//                                drugCheckedTextView[0].setChecked(true);
//                            }
                        } else if (Data.DRUG_TYPE.B.equals(drugModel2.type)) {

                            drugModel2.isDone = drugCheckedTextView[1].isChecked();

//                            drugCheckedTextView[1].setVisibility(View.VISIBLE);
//
//                            if (drugModel2.isDone) {
//                                drugCheckedTextView[1].setChecked(true);
//                            }
                        }

                    }


                }

                if (bArrayList != null) {
                    for (DrugModel2 drugModel2 : bArrayList) {
                        if (Data.DRUG_TYPE.A.equals(drugModel2.type)) {

                            drugModel2.isDone = drugCheckedTextView[2].isChecked();

//                            drugCheckedTextView[2].setVisibility(View.VISIBLE);
//                            if (drugModel2.isDone) {
//                                drugCheckedTextView[2].setChecked(true);
//                            }


                        } else if (Data.DRUG_TYPE.B.equals(drugModel2.type)) {

                            drugModel2.isDone = drugCheckedTextView[3].isChecked();

//                            drugCheckedTextView[3].setVisibility(View.VISIBLE);
//                            if (drugModel2.isDone) {
//                                drugCheckedTextView[3].setChecked(true);
//                            }

                        }
                    }
                }

            }
        }


        // 오늘꺼 설정
        Cursor cursor = mDbHelper.getDayDiary(((ScheduleDrugDoneActivity) getActivity()).date);
        if (cursor != null && cursor.getCount() > 0) {
            String sBody = new Gson().toJson(((ScheduleDrugDoneActivity) getActivity()).drugListDay, DrugList.class);
            boolean isResult = mDbHelper.updateDayDiary(((ScheduleDrugDoneActivity) getActivity()).date, sBody);
            Kog.e("DEBUG", "isResult = " + isResult);
        } else {

            String sBody1 = new Gson().toJson(((ScheduleDrugDoneActivity) getActivity()).drugListDay, DrugList.class);
            long addResult = mDbHelper.createDayDiary(((ScheduleDrugDoneActivity) getActivity()).date, sBody1);
            Kog.e("DEBUG", "addResult = " + addResult);
        }

        cursor.close();
        cursor = null;

        mDbHelper.close();
        mDbHelper = null;

        Intent intent = new Intent();
//        intent.putExtra(Data.INTENT_SELECT_POS, ((ScheduleDrugActivity) getActivity()).pos);
//        intent.putExtra(Data.INTENT_DATE_MSG, new Gson().toJson(((ScheduleDrugActivity) getActivity()).drugMainModel));
        getActivity().setResult(777, intent);
        getActivity().finish();
    }
}
