package luke.com.project.medicineschedulebook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by firefox on 2017. 2. 20..
 */

public class Utils {

    public static String getCurYearMonthDay() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        String date = new StringBuffer().append(year).append(String.format("%02d", month)).append(String.format("%02d", day)).toString();
        Log.d("DEBUG", "3 date = " + date);
        return date;
    }

    public static String getCurYearMonthDay(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        String date = new StringBuffer().append(year).append(String.format("%02d", month)).append(String.format("%02d", day)).toString();
//        Log.d("DEBUG", "2 date = " + date);
        return date;
    }

    public static String getCurYearMonth(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        String date = new StringBuffer().append(year).append(String.format("%02d", month)).toString();
//        Log.d("DEBUG", "1 date = " + date);
        return date;
    }

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment) {
        replaceFragement(activity, layoutId, fragment, null, fragment.getClass().getName(), null);
    }

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, Bundle bundle) {
        replaceFragement(activity, layoutId, fragment, bundle, fragment.getClass().getName(), null);
    }

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, Object object) {
        replaceFragement(activity, layoutId, fragment, null, fragment.getClass().getName(), (object != null ? object.getClass().getName() : null));
    }

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, String object) {
        replaceFragement(activity, layoutId, fragment, null, fragment.getClass().getName(), (object != null ? object : null));
    }

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, Bundle bundle, Object object) {
        replaceFragement(activity, layoutId, fragment, bundle, fragment.getClass().getName(), (object != null ? object.getClass().getName() : null));
    }

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, Bundle bundle, String object) {
        replaceFragement(activity, layoutId, fragment, bundle, fragment.getClass().getName(), (object != null ? object : null));
    }

    private static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, Bundle bundle, String tag, String addToBackStack) {
//        Kog.e("activity = " + activity + " layoutId = " + layoutId + " bundle = " + bundle + " tag = " + tag + " addToBackStack = " + addToBackStack);

//        Kog.e(" layoutId = " + layoutId + " bundle = " + bundle + " tag = " + tag + " addToBackStack = " + addToBackStack);

        try {
            if (activity != null) {
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                if (transaction != null) {
                    if (bundle != null) {
                        fragment.setArguments(bundle);
                    }
                    transaction.replace(layoutId, fragment, tag);
                    if (addToBackStack != null) {
                        transaction.addToBackStack(addToBackStack);
                    }
                    transaction.commit();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideAddFragment(FragmentActivity activity, Fragment hideFragment, int layoutId, Fragment addFragment, Bundle bundle, Object object) {
        hideAddFragment(activity, hideFragment, layoutId, addFragment, bundle, addFragment.getClass().getName(), (object != null ? object.getClass().getName() : null));
    }

    public static void hideAddFragment(FragmentActivity activity, Fragment hideFragment, int layoutId, Fragment addFragment, Bundle bundle, String tag, String addToBackStack) {
        if (activity != null) {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            if (transaction != null) {
                if (bundle != null) {
                    addFragment.setArguments(bundle);
                }
                transaction.hide(hideFragment);
                transaction.add(layoutId, addFragment, tag);
                if (addToBackStack != null) {
                    transaction.addToBackStack(addToBackStack);
                }
                transaction.commit();
            }
        }
    }
}
