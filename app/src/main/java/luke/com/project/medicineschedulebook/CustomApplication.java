package luke.com.project.medicineschedulebook;

import android.app.Application;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by itsm02 on 2017. 3. 22..
 */

public class CustomApplication extends Application {

    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

    public CustomApplication() {
        super();
    }

    @Override
    public void onCreate() {

        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandlerApplication());

        super.onCreate();
    }

    /**
     * 메시지로 변환
     *
     * @param th
     * @return
     */
    private String getStackTrace(Throwable th) {

        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);

        Throwable cause = th;
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        final String stacktraceAsString = result.toString();
        printWriter.close();

        return stacktraceAsString;
    }

    class UncaughtExceptionHandlerApplication implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {

            //예외상황이 발행 되는 경우 작업
            Kog.e("Error", getStackTrace(ex));

            //예외처리를 하지 않고 DefaultUncaughtException으로 넘긴다.
            mUncaughtExceptionHandler.uncaughtException(thread, ex);
        }

    }


}
