package com.cs360.campsitelocator;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
// this class reads from the internal database and outputs campsites specific to state
public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static  DatabaseAccess instance;
    Cursor c = null;

    // opens database
    private DatabaseAccess (Context context){
        this.openHelper = new DatabaseOpenHelper(context);

    }
    //gets instance of database
    public static DatabaseAccess getInstance(Context context){
        if(instance == null) {
            instance = new DatabaseAccess(context);

        }
        return instance;
    }

    //Cursor points to new England state and pulls data from there
    Cursor readAllDataMA() {
        String query = "SELECT * FROM set_library WHERE site_state = 'MA'";
        this.db=openHelper.getReadableDatabase();

        c = null;
        if(db != null){
            c = db.rawQuery(query, null);
        }
        return c;
    }

    Cursor readAllDataCT() {
        String query = "SELECT * FROM set_library WHERE site_state = 'CT'";
        this.db=openHelper.getReadableDatabase();

        c = null;
        if(db != null){
            c = db.rawQuery(query, null);
        }
        return c;
    }

    Cursor readAllDataRI() {
        String query = "SELECT * FROM set_library WHERE site_state = 'RI'";
        this.db=openHelper.getReadableDatabase();

        c = null;
        if(db != null){
            c = db.rawQuery(query, null);
        }
        return c;
    }

    Cursor readAllDataNH() {
        String query = "SELECT * FROM set_library WHERE site_state = 'NH'";
        this.db=openHelper.getReadableDatabase();

        c = null;
        if(db != null){
            c = db.rawQuery(query, null);
        }
        return c;
    }

    Cursor readAllDataVT() {
        String query = "SELECT * FROM set_library WHERE site_state = 'VT'";
        this.db=openHelper.getReadableDatabase();

        c = null;
        if(db != null){
            c = db.rawQuery(query, null);
        }
        return c;
    }

    Cursor readAllDataME() {
        String query = "SELECT * FROM set_library WHERE site_state = 'ME'";
        this.db=openHelper.getReadableDatabase();

        c = null;
        if(db != null){
            c = db.rawQuery(query, null);
        }
        return c;
    }

}
