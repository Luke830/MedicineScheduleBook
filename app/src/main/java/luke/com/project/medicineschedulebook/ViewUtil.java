package luke.com.project.medicineschedulebook;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * @author motec-ksb android View Util
 */
public class ViewUtil {
    private static Toast toast;


    public static void shortToast(Context c, String text) {
        if (null != toast) {
            toast.cancel();
        }
        toast = Toast.makeText(c, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Toast 팝업(Long)
     *
     * @param c    사용중인 Context
     * @param text 팝업 text
     */
    public static void longToast(Context c, String text) {
        if (null != toast) {
            toast.cancel();
        }
        toast = Toast.makeText(c, text, Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * Toast 팝업(Long)
     *
     * @param c       사용중인 Context
     * @param text    팝업 text
     * @param gravity 정렬
     */
    public static void LongToast(Context c, String text, int gravity) {
        if (null != toast) {
            toast.cancel();
        }
        toast = Toast.makeText(c, text, Toast.LENGTH_LONG);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

    public static void showToast(Context context, String text, int duration) {
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

//    public static AddDiaryDialog addDiaryDialog(Context context, String contentText) {
//        return addDiaryDialog(context, contentText, context.getResources().getString(R.string.ok), null);
//    }
//
//    public static AddDiaryDialog addDiaryDialog(Context context, String contentText, View.OnClickListener okOnClickListener) {
//        return addDiaryDialog(context, contentText, context.getResources().getString(R.string.ok), okOnClickListener);
//    }

    public static AddDiaryDialog addDiaryDialog(Context context, String input, String contentText, String okText, View.OnClickListener okOnClickListener) {
        AddDiaryDialog dialog = new AddDiaryDialog(context, input, contentText, okText, okOnClickListener);
        try {
            dialog.setCancelable(true);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dialog;
    }

    public static CheckDiaryDialog checkDiaryDialog(Context context, String contentText, String topText, String bottomText, View.OnClickListener leftOnClickListener, View.OnClickListener rightOnClickListener) {
        CheckDiaryDialog dialog = new CheckDiaryDialog(context, contentText, topText, bottomText, leftOnClickListener, rightOnClickListener);
        try {
            dialog.setCancelable(true);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dialog;
    }

//    public static AddDiaryDialog selectDialog(Context context, String contentText, View.OnClickListener leftOnClickListener, View.OnClickListener rightOnClickListener) {
//        return selectDialog(context, contentText, context.getResources().getString(R.string.cancel2), context.getResources().getString(R.string.ok), leftOnClickListener, rightOnClickListener);
//    }
//


}
