package com.cs360.campsitelocator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

import androidx.annotation.Nullable;

//contains the information of the set library
class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "SetLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "set_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "site_name";
    private static final String COLUMN_LOCATION = "site_location";
    private static final String COLUMN_STATE = "site_state";
    private static final String COLUMN_INFO = "site_info";
    private static final String COLUMN_URL = "site_url";
    private static final String COLUMN_RATING = "star_rating";

    //sets database helper method to assist in database creation
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    //sets string to create sqlite database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_LOCATION + " TEXT, " +
                        COLUMN_STATE + " TEXT, " +
                        COLUMN_INFO + " TEXT, " +
                        COLUMN_URL + " TEXT, " +
                        COLUMN_RATING + " INTEGER);";
        db.execSQL(query);
    }

    //sets upgrade method to update database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //sets database
    void setLibrary(String site, String location, String state, String info, String webPage, int rating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, site);
        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_STATE, state);
        cv.put(COLUMN_INFO, info);
        cv.put(COLUMN_URL, webPage);
        cv.put(COLUMN_RATING, rating);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

}
