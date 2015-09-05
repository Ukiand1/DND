package com.example.uros.dnd.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;

import com.example.uros.dnd.domen.Action;
import com.example.uros.dnd.domen.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikola on 7/26/2015.
 */
public class LocationDataSource {

    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] locationColumns = {SQLiteHelper.LOCATION_LOCATION_ID,
                                        SQLiteHelper.LOCATION_LATITUDE,
                                        SQLiteHelper.LOCATION_LONGITUDE,
                                        SQLiteHelper.LOCATION_NAME,
                                        SQLiteHelper.LOCATION_RADIUS,
                                        SQLiteHelper.LOCATION_ENABLED,
                                        SQLiteHelper.LOCATION_ACTION_ID};



    public LocationDataSource(Context context){
        dbHelper = new SQLiteHelper(context);
    }

    public void openConnection() {
        database = dbHelper.getWritableDatabase();
    }

    public void closeConnection() {
        dbHelper.close();
    }

    public List<Location> getAllLocations(){
        openConnection();
        List<Location> locations = new ArrayList<Location>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_LOCATION, locationColumns,null,null,null,null,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){

            Location location = cursorToLocation(cursor);
            locations.add(location);
            cursor.moveToNext();
        }

        cursor.close();

        closeConnection();
        return locations;
    }

    private Location cursorToLocation(Cursor cursor) {
        Location location = new Location();
        location.setLocation_id(cursor.getLong(0));
        location.setLatitude(cursor.getDouble(1));
        location.setLongitude(cursor.getDouble(2));
        location.setName(cursor.getString(3));
        location.setRadius(cursor.getInt(4));

        int enabled = cursor.getInt(5);
        if (enabled == 1)
            location.setEnabled(true);
        else
            location.setEnabled(false);

        ActionDataSource actionDataSource = new ActionDataSource(database);
        location.setAction(actionDataSource.getActionByID(cursor.getLong(6)));
        return location;

    }

    public void insertLocation(Location location){

        openConnection();

        ActionDataSource actionDataSource = new ActionDataSource(database);
        Action action = actionDataSource.getActionByName(location.getAction().getName());
        long actionId = 0;
        if (action == null) {
            actionId = actionDataSource.insertAction(location.getAction());
        }else {
            actionId = action.getActionId();
        }

        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.LOCATION_NAME, location.getName());
        values.put(SQLiteHelper.LOCATION_LATITUDE, location.getLatitude());
        values.put(SQLiteHelper.LOCATION_LONGITUDE, location.getLongitude());
        values.put(SQLiteHelper.LOCATION_RADIUS, location.getRadius());
        int enabled;
        if (location.isEnabled())
            enabled = 1;
        else
            enabled = 0;
        values.put(SQLiteHelper.LOCATION_ENABLED, enabled);
        values.put(SQLiteHelper.LOCATION_ACTION_ID, actionId);




        database.insert(SQLiteHelper.TABLE_LOCATION, null, values);

        closeConnection();
    }



//    public void deleteLocation(Location location){
//
//        long id = location.getAction_id();
//        System.out.println("Deleting location with id "+id);
//        database.delete(SQLiteHelper.TABLE_LOCATIONS,SQLiteHelper.COLUMN_ID + " = " +id,null);
//    }
}
