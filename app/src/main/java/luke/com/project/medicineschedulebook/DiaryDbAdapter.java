package luke.com.project.medicineschedulebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;

/**
 * Created by itsm02 on 2017. 2. 20..
 */

public class DiaryDbAdapter {

    public static final String KEY_DATE = "date";

    public static final String KEY_DAY = "day";
    public static final String KEY_WEEK = "week";
    public static final String KEY_CREATED = "created";

    private static final String TAG = "DiaryDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

//    private static final String DATABASE_CREATE = "create table diary (_id integer primary key autoincrement, "
//            + "title text not null, body text not null, created text not null);";

//    private static final String DATABASE_CREATE = "create table diary (date text primary key,"
//            + "title text not null, body text not null, created text not null);";

    private static final String DATABASE_CREATE =
            "create table diary (date text primary key," +
                    " day text, week text, created text not null);";

    private static final String DATABASE_NAME = "database";
    private static final String DATABASE_TABLE = "diary";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            db.execSQL("DROP TABLE IF EXISTS diary");
//            onCreate(db);
        }
    }

    public DiaryDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public DiaryDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public long createDayDiary(String rowYearMonthDay, String day) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_DATE, rowYearMonthDay);
        initialValues.put(KEY_DAY, day);
        Calendar calendar = Calendar.getInstance();
        String created = calendar.get(Calendar.YEAR) + ""
                + (String.format("%02d", (calendar.get(Calendar.MONTH) + 1))) + ""
                + calendar.get(Calendar.DAY_OF_MONTH) + ""
                + calendar.get(Calendar.HOUR_OF_DAY) + ""
                + calendar.get(Calendar.MINUTE) + "";
        initialValues.put(KEY_CREATED, created);
        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    public long createWeekDiary(String rowYearMonthDay, String week) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_DATE, rowYearMonthDay);
        initialValues.put(KEY_WEEK, week);
        Calendar calendar = Calendar.getInstance();
        String created = calendar.get(Calendar.YEAR) + ""
                + (String.format("%02d", (calendar.get(Calendar.MONTH) + 1))) + ""
                + calendar.get(Calendar.DAY_OF_MONTH) + ""
                + calendar.get(Calendar.HOUR_OF_DAY) + ""
                + calendar.get(Calendar.MINUTE) + "";
        initialValues.put(KEY_CREATED, created);
        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }


    public boolean deleteDiary(String rowYearMonthDay) {
        return mDb.delete(DATABASE_TABLE, KEY_DATE + "=" + rowYearMonthDay, null) > 0;
    }

    public Cursor getAllNotes() {
        return mDb.query(DATABASE_TABLE, new String[]{KEY_DATE,
                KEY_DAY, KEY_WEEK, KEY_CREATED}, null, null, null, null, null);
    }

    public Cursor getMonthNotes(String rowYearMonth) {

//        Log.d("DEBUG", "rowYearMonth = " + rowYearMonth);

        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[]{KEY_DATE,
                                KEY_DAY, KEY_WEEK, KEY_CREATED}, KEY_DATE + " LIKE '%" + rowYearMonth + "%'", null, null,
                        null, null, null);
//        if (mCursor != null) {
//            mCursor.moveToFirst();
//        }
        return mCursor;
    }

    public Cursor getDayDiary(String rowYearMonthDay) throws SQLException {

        // rowYearMonthDay -> 20170316

        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[]{KEY_DATE,
                                KEY_DAY, KEY_WEEK, KEY_CREATED}, KEY_DATE + "=" + rowYearMonthDay, null, null,
                        null, null, null);
//        if (mCursor != null) {
//            mCursor.moveToFirst();
//        }
        return mCursor;

    }

    public Cursor getMonthNotesBetween(String rowYearMonth1, String rowYearMonth2) {

//        Log.d("DEBUG", "rowYearMonth = " + rowYearMonth);

        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[]{KEY_DATE,
                                KEY_DAY, KEY_WEEK, KEY_CREATED}, KEY_DATE + " BETWEEN " + rowYearMonth1 + " AND " + rowYearMonth2, null, null,
                        null, null, null);
//        if (mCursor != null) {
//            mCursor.moveToFirst();
//        }
        return mCursor;
    }

    public boolean updateDayDiary(String rowYearMonthDay, String day) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_DAY, day);
        Calendar calendar = Calendar.getInstance();
        String created = calendar.get(Calendar.YEAR) + ""
                + calendar.get(Calendar.MONTH) + ""
                + calendar.get(Calendar.DAY_OF_MONTH) + ""
                + calendar.get(Calendar.HOUR_OF_DAY) + ""
                + calendar.get(Calendar.MINUTE) + "";
        initialValues.put(KEY_CREATED, created);

        return mDb.update(DATABASE_TABLE, initialValues, KEY_DATE + "=" + rowYearMonthDay, null) > 0;
    }

    public boolean updateWeekDiary(String rowYearMonthDay, String week) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_WEEK, week);
        Calendar calendar = Calendar.getInstance();
        String created = calendar.get(Calendar.YEAR) + ""
                + calendar.get(Calendar.MONTH) + ""
                + calendar.get(Calendar.DAY_OF_MONTH) + ""
                + calendar.get(Calendar.HOUR_OF_DAY) + ""
                + calendar.get(Calendar.MINUTE) + "";
        initialValues.put(KEY_CREATED, created);

        return mDb.update(DATABASE_TABLE, initialValues, KEY_DATE + "=" + rowYearMonthDay, null) > 0;
    }

}
