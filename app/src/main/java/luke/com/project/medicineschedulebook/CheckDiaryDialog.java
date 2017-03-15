package luke.com.project.medicineschedulebook;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class CheckDiaryDialog extends Dialog {

    public static enum DialogType {
        BUTTON1, BUTTON2
    }

    public DialogType dialogType;
    public String contentText;
    public String okText, topText, bottomText;
    public TextView titleTextView;
    public LinearLayout button1LinearLayout, button2LinearLayout;
    public View.OnClickListener okOnClickListener, topOnClickListener, bottomOnClickListener;
    public Button okButton, topButton, bottomButton;

//    public checkDiaryDialog(Context context, String contentText, String okText, View.OnClickListener okOnClickListener) {
//        super(context, android.R.style.Theme_Translucent_NoTitleBar);
//        this.contentText = contentText;
//        this.okText = okText;
//        this.okOnClickListener = okOnClickListener;
//        dialogType = DialogType.BUTTON1;
//    }

    public CheckDiaryDialog(Context context, String contentText, String topText, String bottomText, View.OnClickListener topOnClickListener, View.OnClickListener bottomOnClickListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.contentText = contentText;
        this.topText = topText;
        this.bottomText = bottomText;
        this.topOnClickListener = topOnClickListener;
        this.bottomOnClickListener = bottomOnClickListener;
        dialogType = DialogType.BUTTON2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAllTranslucent(false);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.check_diary_dialog_layout);
        setView();
    }

    public void setView() {
        titleTextView = (TextView) findViewById(R.id.dialog_date_textView);
        titleTextView.setText(contentText);

        switch (dialogType) {
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
//                            okOnClickListener.onClick(v);
//                        }
//                    }
//                };
//
//                okButton.setOnClickListener((View.OnClickListener) rootOkOnClickListener);
//                break;

            case BUTTON2:
//                button2LinearLayout = (LinearLayout) findViewById(R.id.button2_linear_layout);
//                button2LinearLayout.setVisibility(View.VISIBLE);

                topButton = (Button) findViewById(R.id.top_button);
                bottomButton = (Button) findViewById(R.id.bottom_button);
                topButton.setText(topText);
                bottomButton.setText(bottomText);

                View.OnClickListener rootLeftOnClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (topOnClickListener != null) {
                            topOnClickListener.onClick(v);
                        }
                    }
                };

                View.OnClickListener rootRightOnClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (bottomOnClickListener != null) {
                            bottomOnClickListener.onClick(v);
                        }
                    }
                };

                topButton.setOnClickListener((View.OnClickListener) rootLeftOnClickListener);
                bottomButton.setOnClickListener((View.OnClickListener) rootRightOnClickListener);
                break;
        }
    }

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

}
