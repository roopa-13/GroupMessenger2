package edu.buffalo.cse.cse486586.groupmessenger2;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import static edu.buffalo.cse.cse486586.groupmessenger2.GroupMessengerDbHelper.TABLE_NAME;

/**
 * GroupMessengerProvider is a key-value table. Once again, please note that we do not implement
 * full support for SQL as a usual ContentProvider does. We re-purpose ContentProvider's interface
 * to use it as a key-value table.
 * 
 * Please read:
 * 
 * http://developer.android.com/guide/topics/providers/content-providers.html
 * http://developer.android.com/reference/android/content/ContentProvider.html
 * 
 * before you start to get yourself familiarized with ContentProvider.
 * 
 * There are two methods you need to implement---insert() and query(). Others are optional and
 * will not be tested.
 * 
 * @author stevko
 *
 */
public class GroupMessengerProvider extends ContentProvider {

    private GroupMessengerDbHelper groupMessengerDbHelper;
    private static final String TAG = GroupMessengerProvider.class.getName();

    public static final Uri BASE_URI = buildUri("content", "edu.buffalo.cse.cse486586.groupmessenger2.provider");

    private static Uri buildUri(String scheme, String authority) {
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.authority(authority);
        uriBuilder.scheme(scheme);
        return uriBuilder.build();
    }

    /*Below modified insert() and query() code is based on understanding
    https://developer.android.com/guide/topics/providers/content-provider-creating*/

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // You do not need to implement this.
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        // You do not need to implement this.
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        /*
         * TODO: You need to implement this method. Note that values will have two columns (a key
         * column and a value column) and one row that contains the actual (key, value) pair to be
         * inserted.
         *
         * For actual storage, you can use any option. If you know how to use SQL, then you can use
         * SQLite. But this is not a requirement. You can use other storage options, such as the
         * internal storage option that we used in PA1. If you want to use that option, please
         * take a look at the code for PA1.
         */

        //Access to Db to write data
        final SQLiteDatabase db = groupMessengerDbHelper.getWritableDatabase();

        Uri returnUri;

        long id = db.replace(TABLE_NAME, null, values);
        if (id > 0) {
            returnUri = ContentUris.withAppendedId(BASE_URI, id);
        } else {
            throw new android.database.SQLException("Failed to insert row into " + uri);
        }

        //Notify the resolver if the uri has been changed, and return the newly inserted URI
        getContext().getContentResolver().notifyChange(uri, null);

        Log.v("insert", values.toString());
        return returnUri;
    }

    @Override
    public boolean onCreate() {
        // If you need to perform any one-time initialization task, please do it here.
        Context context = getContext();
        groupMessengerDbHelper = new GroupMessengerDbHelper(context);

        Log.d(TAG, "Table instance created successfully");
        return true;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // You do not need to implement this.
        return 0;
    }

    //Read the data from the Content Provider
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        /*
         * TODO: You need to implement this method. Note that you need to return a Cursor object
         * with the right format. If the formatting is not correct, then it is not going to work.
         *
         * If you use SQLite, whatever is returned from SQLite is a Cursor object. However, you
         * still need to be careful because the formatting might still be incorrect.
         *
         * If you use a file storage option, then it is your job to build a Cursor * object. I
         * recommend building a MatrixCursor described at:
         * http://developer.android.com/reference/android/database/MatrixCursor.html
         */

        //Access to Db to read Data
        final SQLiteDatabase db = groupMessengerDbHelper.getReadableDatabase();

        Cursor cursor;

        cursor = db.query(TABLE_NAME,
                projection,
                "key='" + selection + "'",
                selectionArgs,
                null,
                null,
                null);

        Log.v("query", selection);

        return cursor;
    }
}