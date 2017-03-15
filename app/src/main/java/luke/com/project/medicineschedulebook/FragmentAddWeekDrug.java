package luke.com.project.medicineschedulebook;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class FragmentAddWeekDrug extends Fragment implements View.OnClickListener {

    public CheckedTextView[] drugCheckedTextView = new CheckedTextView[8];

    public FragmentAddWeekDrug() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_add_week_drug, container, false);

        view.findViewById(R.id.add_week_button).setOnClickListener(this);

        drugCheckedTextView[0] = (CheckedTextView) view.findViewById(R.id.drug1);
        drugCheckedTextView[1] = (CheckedTextView) view.findViewById(R.id.drug2);
        drugCheckedTextView[2] = (CheckedTextView) view.findViewById(R.id.drug3);
        drugCheckedTextView[3] = (CheckedTextView) view.findViewById(R.id.drug4);
        drugCheckedTextView[4] = (CheckedTextView) view.findViewById(R.id.drug5);
        drugCheckedTextView[5] = (CheckedTextView) view.findViewById(R.id.drug6);
        drugCheckedTextView[6] = (CheckedTextView) view.findViewById(R.id.drug7);
        drugCheckedTextView[7] = (CheckedTextView) view.findViewById(R.id.drug8);

        if (((ScheduleDrugActivity) getActivity()).drugMainModel != null) {

            ArrayList<DrugModel> arrayList = ((ScheduleDrugActivity) getActivity()).drugMainModel.weekDrugList;

            if (arrayList != null) {

                DrugModel drugModel = arrayList.get(0);
                ArrayList<DrugModel2> aArrayList = drugModel.aDrugList;
                ArrayList<DrugModel2> bArrayList = drugModel.bDrugList;

                if (aArrayList != null) {

                    for (DrugModel2 drugModel2 : aArrayList) {
                        if (DrugMainModel.DRUG_TYPE.C.equals(drugModel2.type)) {
                            drugCheckedTextView[0].setChecked(true);
                        } else if (DrugMainModel.DRUG_TYPE.D.equals(drugModel2.type)) {
                            drugCheckedTextView[1].setChecked(true);
                        } else if (DrugMainModel.DRUG_TYPE.E.equals(drugModel2.type)) {
                            drugCheckedTextView[2].setChecked(true);
                        } else if (DrugMainModel.DRUG_TYPE.F.equals(drugModel2.type)) {
                            drugCheckedTextView[3].setChecked(true);
                        } else if (DrugMainModel.DRUG_TYPE.G.equals(drugModel2.type)) {
                            drugCheckedTextView[4].setChecked(true);
                        } else if (DrugMainModel.DRUG_TYPE.B.equals(drugModel2.type)) {
                            drugCheckedTextView[5].setChecked(true);
                        }
                    }


                }

                if (bArrayList != null) {
                    for (DrugModel2 drugModel2 : bArrayList) {
                        if (DrugMainModel.DRUG_TYPE.C.equals(drugModel2.type)) {
                            drugCheckedTextView[6].setChecked(true);
                        } else if (DrugMainModel.DRUG_TYPE.B.equals(drugModel2.type)) {
                            drugCheckedTextView[7].setChecked(true);
                        }
                    }
                }

            }
        }

        drugCheckedTextView[0].setOnClickListener(this);
        drugCheckedTextView[1].setOnClickListener(this);
        drugCheckedTextView[2].setOnClickListener(this);
        drugCheckedTextView[3].setOnClickListener(this);
        drugCheckedTextView[4].setOnClickListener(this);
        drugCheckedTextView[5].setOnClickListener(this);
        drugCheckedTextView[6].setOnClickListener(this);
        drugCheckedTextView[7].setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
                case R.id.add_week_button:
                    updateDB();
                    break;
            }
        }

    }

    public void updateDB() {
        DiaryDbAdapter mDbHelper = new DiaryDbAdapter(getActivity());
        mDbHelper.open();

        DrugModel drugModel = null;

        if (drugCheckedTextView[0].isChecked()
                || drugCheckedTextView[1].isChecked()
                || drugCheckedTextView[2].isChecked()
                || drugCheckedTextView[3].isChecked()
                || drugCheckedTextView[4].isChecked()
                || drugCheckedTextView[5].isChecked()) {
            if (drugModel == null) {
                drugModel = new DrugModel();
            }

            drugModel.aDrugList = new ArrayList<DrugModel2>();
            if (drugCheckedTextView[0].isChecked()) {
                DrugModel2 drugModel2 = new DrugModel2(DrugMainModel.DRUG_TYPE.C, false);
                drugModel.aDrugList.add(drugModel2);
            }
            if (drugCheckedTextView[1].isChecked()) {
                DrugModel2 drugModel2 = new DrugModel2(DrugMainModel.DRUG_TYPE.D, false);
                drugModel.aDrugList.add(drugModel2);
            }

            if (drugCheckedTextView[2].isChecked()) {
                DrugModel2 drugModel2 = new DrugModel2(DrugMainModel.DRUG_TYPE.E, false);
                drugModel.aDrugList.add(drugModel2);
            }

            if (drugCheckedTextView[3].isChecked()) {
                DrugModel2 drugModel2 = new DrugModel2(DrugMainModel.DRUG_TYPE.F, false);
                drugModel.aDrugList.add(drugModel2);
            }

            if (drugCheckedTextView[4].isChecked()) {
                DrugModel2 drugModel2 = new DrugModel2(DrugMainModel.DRUG_TYPE.G, false);
                drugModel.aDrugList.add(drugModel2);
            }

            if (drugCheckedTextView[5].isChecked()) {
                DrugModel2 drugModel2 = new DrugModel2(DrugMainModel.DRUG_TYPE.B, false);
                drugModel.aDrugList.add(drugModel2);
            }

        }

        if (drugCheckedTextView[6].isChecked() || drugCheckedTextView[7].isChecked()) {
            if (drugModel == null) {
                drugModel = new DrugModel();
            }

            drugModel.bDrugList = new ArrayList<DrugModel2>();
            if (drugCheckedTextView[6].isChecked()) {
                DrugModel2 drugModel2 = new DrugModel2(DrugMainModel.DRUG_TYPE.C, false);
                drugModel.bDrugList.add(drugModel2);
            }
            if (drugCheckedTextView[7].isChecked()) {
                DrugModel2 drugModel2 = new DrugModel2(DrugMainModel.DRUG_TYPE.B, false);
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

        ((ScheduleDrugActivity) getActivity()).drugMainModel.weekDrugList = arrayList;

        Cursor cursor = mDbHelper.getDayDiary(((ScheduleDrugActivity) getActivity()).date);

        if (cursor != null && cursor.getCount() > 0) {
            String sBody = new Gson().toJson(((ScheduleDrugActivity) getActivity()).drugMainModel, ((ScheduleDrugActivity) getActivity()).drugMainModel.getClass());
            boolean isResult = mDbHelper.updateDiary(((ScheduleDrugActivity) getActivity()).date, sBody);
            Kog.e("DEBUG", "isResult = " + isResult);
        } else {
            String sBody = new Gson().toJson(((ScheduleDrugActivity) getActivity()).drugMainModel, ((ScheduleDrugActivity) getActivity()).drugMainModel.getClass());
            long addResult = mDbHelper.createDiary(((ScheduleDrugActivity) getActivity()).date, sBody);
            Kog.e("DEBUG", "addResult = " + addResult);
        }

        mDbHelper.close();
        mDbHelper = null;

        Kog.e(((ScheduleDrugActivity) getActivity()).drugMainModel.toString());

        Intent intent = new Intent();
        intent.putExtra(Data.INTENT_SELECT_POS, ((ScheduleDrugActivity) getActivity()).pos);
        intent.putExtra(Data.INTENT_DATE_MSG, new Gson().toJson(((ScheduleDrugActivity) getActivity()).drugMainModel));
        getActivity().setResult(777, intent);
        getActivity().finish();
    }
}
