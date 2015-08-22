package com.example.uros.dnd.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.uros.dnd.domen.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikola on 7/26/2015.
 */
public class LocationDataSource {

    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] columns = {SQLiteHelper.COLUMN_ID, SQLiteHelper.COLUMN_NAME, SQLiteHelper.COLUMN_LAT, SQLiteHelper.COLUMN_LONG};


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

        List<Location> locations = new ArrayList<Location>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_LOCATIONS, columns,null,null,null,null,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){

            Location location = cursorToLocation(cursor);
            locations.add(location);
            cursor.moveToNext();
        }

        cursor.close();

        return locations;
    }

    private Location cursorToLocation(Cursor cursor) {
        Location location = new Location();
        location.setLocation_id(cursor.getLong(0));
        location.setName(cursor.getString(1));
        location.setLatitude(cursor.getDouble(2));
        location.setLongitude(cursor.getDouble(3));
        return location;

    }

    public Location createLocation(Location location){

        ContentValues values = new ContentValues();

        values.put(SQLiteHelper.COLUMN_NAME, location.getName());
        values.put(SQLiteHelper.COLUMN_LAT, location.getLatitude());
        values.put(SQLiteHelper.COLUMN_LONG, location.getLongitude());

        long insertId = database.insert(SQLiteHelper.TABLE_LOCATIONS, null, values);

        Cursor cursor = database.query(SQLiteHelper.TABLE_LOCATIONS, columns, SQLiteHelper.COLUMN_ID + " = " + insertId,
                null,null,null,null);
        cursor.moveToFirst();
        Location newLocation = cursorToLocation(cursor);
        cursor.close();
        return newLocation;

    }



    public void deleteLocation(Location location){

        long id = location.getAction_id();
        System.out.println("Deleting location with id "+id);
        database.delete(SQLiteHelper.TABLE_LOCATIONS,SQLiteHelper.COLUMN_ID + " = " +id,null);
    }
}
