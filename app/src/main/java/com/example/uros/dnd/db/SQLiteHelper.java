package com.example.uros.dnd.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by Nikola on 7/26/2015.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "locations.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_LOCATIONS = "locations";
    public static final String COLUMN_ID = "location_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LAT = "latitude";
    public static final String COLUMN_LONG = "longitude";


    //string for database creation
    private static final String DATABASE_CREATE = "create table "
            + TABLE_LOCATIONS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_NAME
            + " text not null, "+ COLUMN_LAT +" text not null,"+COLUMN_LONG +" text not null);";

   // public SQLiteHelper(Context context) {
     //   super(context, DATABASE_NAME, null, DATABASE_VERSION);
    //}

    public SQLiteHelper(Context context) {
        super(context, Environment.getExternalStorageDirectory()
                        + File.separator + "DNDdb" + File.separator + DATABASE_NAME,
                null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
        onCreate(db);

    }
}
