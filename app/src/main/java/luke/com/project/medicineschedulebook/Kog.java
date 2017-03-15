package luke.com.project.medicineschedulebook;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;


public class Kog {

    private final static String PROJECT_NAME = "Brand";

    /**
     * Project version printing
     *
     * @param a_oContext
     */
    public static void printVersion(Context a_oContext) {

        PackageInfo packageInfo;
        try {
            packageInfo = a_oContext.getPackageManager().getPackageInfo(a_oContext.getPackageName(), 0);

            d(" ------------------------------------------------ ");
            d("                " + PROJECT_NAME);
            d("            version =" + packageInfo.versionName);
            d(" ------------------------------------------------ ");

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Log.e
     *
     * @param a_sMessage
     * @param a_oValue
     */
    public static void e(String a_sMessage, Object a_oValue) {

        if (BuildConfig.DEBUG) {
            Exception e = new Exception();
            StackTraceElement callerElement = e.getStackTrace()[1];
            Log.e(callerElement.getFileName(), getCallerInfo() + "[" + callerElement.getLineNumber() + "] " + "[ " + a_sMessage + " ] = " + a_oValue);
        }
    }

    /**
     * Log.e
     *
     * @param a_sMessage
     */
    public static void e(String a_sMessage) {

        if (BuildConfig.DEBUG) {
            Exception e = new Exception();
            StackTraceElement callerElement = e.getStackTrace()[1];
            Log.e(callerElement.getFileName(), getCallerInfo() + "[" + callerElement.getLineNumber() + "] " + "[ " + a_sMessage + " ]");
        }
    }

    public static void e(Exception ex) {

        if (BuildConfig.DEBUG) {
            Exception e = new Exception();
            StackTraceElement callerElement = e.getStackTrace()[1];
            Log.e(callerElement.getFileName(), getCallerInfo() + "[" + callerElement.getLineNumber() + "] ", ex);
        }
    }

    /**
     * Log.w
     *
     * @param a_sMessage
     * @param a_oValue
     */
    public static void w(String a_sMessage, Object a_oValue) {

        if (BuildConfig.DEBUG) {
            Exception e = new Exception();
            StackTraceElement callerElement = e.getStackTrace()[1];
            Log.w(callerElement.getFileName(), getCallerInfo() + "[" + callerElement.getLineNumber() + "] " + "[ " + a_sMessage + " ] = " + a_oValue);
        }
    }

    /**
     * Log.w
     *
     * @param a_sMessage
     */
    public static void w(String a_sMessage) {

        if (BuildConfig.DEBUG) {
            Exception e = new Exception();
            StackTraceElement callerElement = e.getStackTrace()[1];
            Log.w(callerElement.getFileName(), getCallerInfo() + "[" + callerElement.getLineNumber() + "] " + "[ " + a_sMessage + " ]");
        }
    }

    /**
     * Log.i
     *
     * @param a_sMessage
     * @param a_oValue
     */
    public static void i(String a_sMessage, Object a_oValue) {

        if (BuildConfig.DEBUG) {
            Exception e = new Exception();
            StackTraceElement callerElement = e.getStackTrace()[1];
            Log.i(callerElement.getFileName(), getCallerInfo() + "[" + callerElement.getLineNumber() + "] " + "[ " + a_sMessage + " ] = " + a_oValue);
        }
    }

    /**
     * Log.i
     *
     * @param a_sMessage
     */
    public static void i(String a_sMessage) {

        if (BuildConfig.DEBUG) {
            Exception e = new Exception();
            StackTraceElement callerElement = e.getStackTrace()[1];
            Log.i(callerElement.getFileName(), getCallerInfo() + "[ " + callerElement.getLineNumber() + "] " + "[ " + a_sMessage + " ]");
        }
    }

    /**
     * Log.d
     *
     * @param a_sMessage
     * @param a_oValue
     */
    public static void d(String a_sMessage, Object a_oValue) {

        if (BuildConfig.DEBUG) {
            Exception e = new Exception();
            StackTraceElement callerElement = e.getStackTrace()[1];
            Log.d(callerElement.getFileName(), getCallerInfo() + "[" + callerElement.getLineNumber() + "] " + "[ " + a_sMessage + " ] = " + a_oValue);
        }
    }

    /**
     * Log.d
     *
     * @param a_sMessage
     */
    public static void d(String a_sMessage) {

        if (BuildConfig.DEBUG) {
            Exception e = new Exception();
            StackTraceElement callerElement = e.getStackTrace()[1];
            Log.d(callerElement.getFileName(), getCallerInfo() + "[" + callerElement.getLineNumber() + "] " + "[ " + a_sMessage + " ]");
        }
    }

    /**
     * Log.v
     *
     * @param a_sMessage
     * @param a_oValue
     */
    public static void v(String a_sMessage, Object a_oValue) {

        if (BuildConfig.DEBUG) {
            Exception e = new Exception();
            StackTraceElement callerElement = e.getStackTrace()[1];
            Log.v(callerElement.getFileName(), getCallerInfo() + "[" + callerElement.getLineNumber() + "] " + "[ " + a_sMessage + " ] = " + a_oValue);
        }
    }

    /**
     * Log.v
     *
     * @param a_sMessage
     */
    public static void v(String a_sMessage) {

        if (BuildConfig.DEBUG) {
            Exception e = new Exception();
            StackTraceElement callerElement = e.getStackTrace()[1];
            Log.v(callerElement.getFileName(), getCallerInfo() + "[" + callerElement.getLineNumber() + "] " + "[ " + a_sMessage + " ]");
        }
    }

    private static String getCallerInfo() {
        StackTraceElement el = new Throwable().fillInStackTrace().getStackTrace()[2];
        String className = el.getClassName();
        className = className.substring(className.lastIndexOf('.') + 1);
        String caller = className + "." + el.getMethodName() + "() Line " + el.getLineNumber();

        return caller;
    }

}