package luke.com.project.medicineschedulebook;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;


public class AddDiaryDialog extends Dialog implements View.OnClickListener {


    public static enum DialogType {
        BUTTON1, BUTTON2
    }

    public DialogType dialogType;
    public String contentText;
    public String okText, leftText, rightText;
    public TextView titleTextView;
    public LinearLayout button1LinearLayout, button2LinearLayout;
    public View.OnClickListener okOnClickListener, leftOnClickListener, rightOnClickListener;
    public Button okButton, leftButton, rightButton;

    public CheckedTextView[] drugCheckedTextView = new CheckedTextView[4];

    public DrugMainModel drugMainModel;

    public AddDiaryDialog(Context context, String input, String contentText, String okText, View.OnClickListener okOnClickListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.contentText = contentText;
        this.okText = okText;
        this.okOnClickListener = okOnClickListener;
        dialogType = DialogType.BUTTON1;


        drugMainModel = new Gson().fromJson(input, DrugMainModel.class);
    }

//    public AddDiaryDialog(Context context, String contentText, String topText, String bottomText, View.OnClickListener leftOnClickListener, View.OnClickListener rightOnClickListener) {
//        super(context, android.R.style.Theme_Translucent_NoTitleBar);
//        this.contentText = contentText;
//        this.topText = topText;
//        this.bottomText = bottomText;
//        this.leftOnClickListener = leftOnClickListener;
//        this.rightOnClickListener = rightOnClickListener;
//        dialogType = DialogType.BUTTON2;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAllTranslucent(false);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.add_diary_dialog_layout);
//        setView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dismiss();
    }

    @Override
    public void cancel() {
        super.cancel();
        dismiss();
    }

//    public void setView() {
//        titleTextView = (TextView) findViewById(R.id.dialog_date_textView);
//        titleTextView.setText(contentText);
//
//        drugCheckedTextView[0] = (CheckedTextView) findViewById(R.id.drug1);
//        drugCheckedTextView[0].setOnClickListener(this);
//
//        drugCheckedTextView[1] = (CheckedTextView) findViewById(R.id.drug2);
//        drugCheckedTextView[1].setOnClickListener(this);
//
//        drugCheckedTextView[2] = (CheckedTextView) findViewById(R.id.drug3);
//        drugCheckedTextView[2].setOnClickListener(this);
//
//        drugCheckedTextView[3] = (CheckedTextView) findViewById(R.id.drug4);
//        drugCheckedTextView[3].setOnClickListener(this);
//
////        drugMainModel.drugDetailList;
//
//        if (drugMainModel != null) {
//            for (int i = 0; i < drugMainModel.drugDetailList.size(); i++) {
//                DrugDetailModel drugDetailModel = drugMainModel.drugDetailList.get(i);
//                if (drugDetailModel != null) {
//
//                    if (drugDetailModel.durgType == DrugDetailModel.DRUG_TYPE.INJECTIONS) {
//
//                        for (int j = 0; j < drugDetailModel.drugList.size(); j++) {
//
//                            DrugDetailModel.DRUG drug = drugDetailModel.drugList.get(j);
//                            if (drug != null) {
//
//                                if (drug == DrugDetailModel.DRUG.A) {
//                                    drugCheckedTextView[0].setChecked(true);
//                                } else if (drug == DrugDetailModel.DRUG.B) {
//                                    drugCheckedTextView[1].setChecked(true);
//                                }
//                            }
//                        }
//
//
//                    } else if (drugDetailModel.durgType == DrugDetailModel.DRUG_TYPE.ORAL_ADMINISTRATION) {
//
//                        for (int j = 0; j < drugDetailModel.drugList.size(); j++) {
//
//                            DrugDetailModel.DRUG drug = drugDetailModel.drugList.get(j);
//                            if (drug != null) {
//
//                                if (drug == DrugDetailModel.DRUG.A) {
//                                    drugCheckedTextView[2].setChecked(true);
//                                } else if (drug == DrugDetailModel.DRUG.B) {
//                                    drugCheckedTextView[3].setChecked(true);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        switch (dialogType)
//
//        {
//            case BUTTON1:
////                button1LinearLayout = (LinearLayout) findViewById(R.id.button1_linear_layout);
////                button1LinearLayout.setVisibility(View.VISIBLE);
//
//                okButton = (Button) findViewById(R.id.button1);
//                okButton.setText(okText);
//
//                View.OnClickListener rootOkOnClickListener = new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        try {
//                            dismiss();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                        if (okOnClickListener != null) {
//
//                            boolean durg1 = drugCheckedTextView[0].isChecked();
//                            boolean durg2 = drugCheckedTextView[1].isChecked();
//
//                            boolean durg3 = drugCheckedTextView[2].isChecked();
//                            boolean durg4 = drugCheckedTextView[3].isChecked();
//
//                            if (durg1 || durg2 || durg3 || durg4) {
//                                DrugMainModel drugMainModel = new DrugMainModel();
//                                drugMainModel.dayType = DrugMainModel.DAY_TYPY.DAY;
//                                drugMainModel.drugDetailList = new ArrayList<DrugDetailModel>();
//
//                                if (durg1) {
//                                    DrugDetailModel drugDetailModel = new DrugDetailModel();
//                                    drugDetailModel.durgType = DrugDetailModel.DRUG_TYPE.INJECTIONS;
//                                    drugDetailModel.drugList = new ArrayList<DrugDetailModel.DRUG>();
//                                    drugDetailModel.drugList.add(DrugDetailModel.DRUG.A);
//
//                                    drugMainModel.drugDetailList.add(drugDetailModel);
//                                }
//
//                                if (durg2) {
//                                    DrugDetailModel drugDetailModel = new DrugDetailModel();
//                                    drugDetailModel.durgType = DrugDetailModel.DRUG_TYPE.INJECTIONS;
//                                    drugDetailModel.drugList = new ArrayList<DrugDetailModel.DRUG>();
//                                    drugDetailModel.drugList.add(DrugDetailModel.DRUG.B);
//
//                                    drugMainModel.drugDetailList.add(drugDetailModel);
//                                }
//
//                                if (durg3) {
//                                    DrugDetailModel drugDetailModel = new DrugDetailModel();
//                                    drugDetailModel.durgType = DrugDetailModel.DRUG_TYPE.ORAL_ADMINISTRATION;
//                                    drugDetailModel.drugList = new ArrayList<DrugDetailModel.DRUG>();
//                                    drugDetailModel.drugList.add(DrugDetailModel.DRUG.A);
//
//                                    drugMainModel.drugDetailList.add(drugDetailModel);
//                                }
//
//                                if (durg4) {
//                                    DrugDetailModel drugDetailModel = new DrugDetailModel();
//                                    drugDetailModel.durgType = DrugDetailModel.DRUG_TYPE.ORAL_ADMINISTRATION;
//                                    drugDetailModel.drugList = new ArrayList<DrugDetailModel.DRUG>();
//                                    drugDetailModel.drugList.add(DrugDetailModel.DRUG.B);
//
//                                    drugMainModel.drugDetailList.add(drugDetailModel);
//                                }
////                                Log.e("DEBUG", drugMainModel.toString());
//                                v.setTag(R.id.add_id, drugMainModel);
//                            }
//                            okOnClickListener.onClick(v);
//                        }
//                    }
//                };
//
//                okButton.setOnClickListener((View.OnClickListener) rootOkOnClickListener);
//                break;
//
//            case BUTTON2:
////                button2LinearLayout = (LinearLayout) findViewById(R.id.button2_linear_layout);
////                button2LinearLayout.setVisibility(View.VISIBLE);
////
////                leftButton = (Button) findViewById(R.id.left_button);
////                rightButton = (Button) findViewById(R.id.right_button);
////                leftButton.setText(topText);
////                rightButton.setText(bottomText);
////
////                View.OnClickListener rootLeftOnClickListener = new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        try {
////                            dismiss();
////                        } catch (Exception e) {
////                            e.printStackTrace();
////                            DataUtil.sendMail(getContext(), e);
////                        }
////
////                        if (leftOnClickListener != null) {
////                            leftOnClickListener.onClick(v);
////                        }
////                    }
////                };
////
////                View.OnClickListener rootRightOnClickListener = new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        try {
////                            dismiss();
////                        } catch (Exception e) {
////                            e.printStackTrace();
////                            DataUtil.sendMail(getContext(), e);
////                        }
////
////                        if (rightOnClickListener != null) {
////                            rightOnClickListener.onClick(v);
////                        }
////                    }
////                };
////
////                leftButton.setOnClickListener((View.OnClickListener) rootLeftOnClickListener);
////                rightButton.setOnClickListener((View.OnClickListener) rootRightOnClickListener);
//                break;
//        }
//
//    }

    public void setAllTranslucent(boolean setAllTranslucent) {
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        if (setAllTranslucent) {
            lpWindow.dimAmount = 0.0f;
        } else {
            lpWindow.dimAmount = 0.8f;
        }
        getWindow().setAttributes(lpWindow);
    }

    @Override
    public void onClick(View v) {

        boolean isChecked = false;

        switch (v.getId()) {
            case R.id.drug1:
                isChecked = drugCheckedTextView[0].isChecked();
                Log.e("DEBUG", "isChecked = " + isChecked);
                drugCheckedTextView[0].setChecked(!isChecked);
                break;

            case R.id.drug2:
                isChecked = drugCheckedTextView[1].isChecked();
                Log.e("DEBUG", "isChecked = " + isChecked);
                drugCheckedTextView[1].setChecked(!isChecked);
                break;

            case R.id.drug3:
                isChecked = drugCheckedTextView[2].isChecked();
                Log.e("DEBUG", "isChecked = " + isChecked);
                drugCheckedTextView[2].setChecked(!isChecked);
                break;

            case R.id.drug4:
                isChecked = drugCheckedTextView[3].isChecked();
                Log.e("DEBUG", "isChecked = " + isChecked);
                drugCheckedTextView[3].setChecked(!isChecked);
                break;
        }

    }

}
