package com.cs360.campsitelocator;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

//opens internal database for search
public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "SetLibrary.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
