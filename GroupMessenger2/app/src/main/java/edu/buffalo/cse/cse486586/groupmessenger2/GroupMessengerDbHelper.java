package edu.buffalo.cse.cse486586.groupmessenger2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GroupMessengerDbHelper extends SQLiteOpenHelper {

    /*Below modified code is based on understanding
    https://developer.android.com/training/data-storage/sqlite*/

    //Database name
    private static final String DATABASE_NAME = "groupMessengerDb.db";
    //To track the database schema changes
    private static final int VERSION = 1;
    //Table Name
    public static final String TABLE_NAME = "groupmessenger";
    private static final String TAG = GroupMessengerDbHelper.class.getName();

    public static final String KEY_FIELD = "key";
    public static final String VALUE_FIELD = "value";

    GroupMessengerDbHelper(Context context){super(context, DATABASE_NAME, null, VERSION);
        Log.d(TAG, "Table "+GroupMessengerDbHelper.TABLE_NAME+ "path "+
                context.getDatabasePath(DATABASE_NAME).getAbsolutePath());
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_TABLE =
                "CREATE TABLE " + GroupMessengerDbHelper.TABLE_NAME + " (" +
                KEY_FIELD + " TEXT NOT NULL PRIMARY KEY, " +
                VALUE_FIELD + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(CREATE_TABLE);
        Log.i(TAG, "Table "+GroupMessengerDbHelper.TABLE_NAME+ " created!");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ GroupMessengerDbHelper.TABLE_NAME);
        onCreate(sqLiteDatabase);

        Log.i(TAG, "Table "+GroupMessengerDbHelper.TABLE_NAME+ " deleted!");
    }
}
