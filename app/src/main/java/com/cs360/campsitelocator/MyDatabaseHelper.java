package com.cs360.campsitelocator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.annotation.Nullable;

//opens favorites database
class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "CampLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "site_name";
    private static final String COLUMN_LOCATION = "site_location";
    private static final String COLUMN_RATING = "star_rating";
    private static final String COLUMN_INFO = "site_info";
    private static final String COLUMN_URL = "site_url";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    //string to create favorites database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_LOCATION + " TEXT, " +
                        COLUMN_RATING + " INTEGER, " +
                        COLUMN_INFO + " TEXT, " +
                        COLUMN_URL + " TEXT);";
        db.execSQL(query);

    }

    //prevents 2nd table from being made
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //adds to database
    void addBook(String site, String location, int rating, String info, String url){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, site);
        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_RATING, rating);
        cv.put(COLUMN_INFO, info);
        cv.put(COLUMN_URL, url);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    //reads all data from database
    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //updates data into database and sets pop up to validate entry
    void updateData(String row_id, String site, String location, String rating, String info, String url){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, site);
        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_RATING, rating);
        cv.put(COLUMN_INFO, info);
        cv.put(COLUMN_URL, url);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed To Update.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
        }
    }

    //deletes one row
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    //delete all from database
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }


}
