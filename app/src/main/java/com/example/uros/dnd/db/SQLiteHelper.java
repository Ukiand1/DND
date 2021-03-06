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
//
    public static final String TABLE_LOCATION = "location";
    public static final String LOCATION_LOCATION_ID = "location_id";
    public static final String LOCATION_NAME = "name";
    public static final String LOCATION_LATITUDE = "latitude";
    public static final String LOCATION_LONGITUDE = "longitude";
    public static final String LOCATION_RADIUS = "radius";
    public static final String LOCATION_ENABLED = "enabled";
    public static final String LOCATION_ACTION_ID = "action_id";

    public static final String TABLE_ACTION = "action";
    public static final String ACTION_ACTION_ID = "action_id";
    public static final String ACTION_NAME = "name";
    public static final String ACTION_CALL = "call";
    public static final String ACTION_SOUND = "sound";
    public static final String ACTION_VIBRATION = "vibration";

    public static final String TABLE_SERVICE = "service";
    public static final String SERVICE_SERVICE_ID = "service_id";
    public static final String SERVCIE_STATUS = "status";



    //string for database creation
//    private static final String DATABASE_CREATE = "create table "
//            + TABLE_LOCATIONS + "(" + COLUMN_ID
//            + " integer primary key autoincrement, " + COLUMN_NAME
//            + " text not null, "+ COLUMN_LAT +" text not null,"+COLUMN_LONG +" text not null);";

    private static final String CREATE_ACTION =

            "CREATE TABLE action (\n" +
            "\taction_id\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\tname\tTEXT,\n" +
            "\tcall\tTEXT,\n" +
            "\tsound\tINTEGER,\n" +
            "\tvibration\tINTEGER\n" +
            ");";

    private static final String CREATE_LOCATION =

            "CREATE TABLE location (\n" +
            "\tlocation_id\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\tlatitude\tREAL,\n" +
            "\tlongitude\tREAL,\n" +
            "\tname\tTEXT,\n" +
            "\tradius\tINTEGER,\n" +
            "\tenabled\tINTEGER,\n" +
            "\taction_id\tINTEGER\n" +
            ");";

    private static final String CREATE_SERVICE =

            "CREATE TABLE service (\n" +
                    "\tservice_id\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "\tstatus\tINTEGER\n" +
                    ");";

    private static final String insertServiceVaules = "insert into service (status) values (0)";


    private static final String insertDefault = "insert into action  (name, sound , vibration) values \n" +
            "('vibration', 0, 1),\n" +
            "('silent', 0, 0),\n" +
            "('normal', 100, 1)";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//    public SQLiteHelper(Context context) {
//        super(context, Environment.getExternalStorageDirectory()
//                        + File.separator + "DNDdb" + File.separator + DATABASE_NAME,
//                null, DATABASE_VERSION);
//    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_ACTION);
        database.execSQL(CREATE_LOCATION);
        database.execSQL(insertDefault);
        database.execSQL(CREATE_SERVICE);
        database.execSQL(insertServiceVaules);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void insertDefaultActions(){

    }

}
