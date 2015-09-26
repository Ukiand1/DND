package com.example.uros.dnd.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Nikola on 9/26/2015.
 */
public class ServiceDataSource {

    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public ServiceDataSource (Context context) {dbHelper = new SQLiteHelper(context);}

    public void openConnection() {
        database = dbHelper.getWritableDatabase();
    }

    public void closeConnection() {
        dbHelper.close();
    }

    private String[] serviceColumns = {SQLiteHelper.SERVICE_SERVICE_ID,SQLiteHelper.SERVCIE_STATUS};

    public ServiceDataSource(SQLiteDatabase database){
        this.database = database;
    }

    public int getServiceStatus(){

        openConnection();

        Cursor cursor = database.query(SQLiteHelper.TABLE_SERVICE, serviceColumns,null,null,null,null,null);
        cursor.moveToFirst();

        int serviceStatus = cursor.getInt(1);
        cursor.close();

        closeConnection();

        return serviceStatus;
    }

    public void updateServiceStatus(int status){

        openConnection();
        String updateQuery = "Update service set status = "+status+" where service_id = 1";
        database.execSQL(updateQuery);
        closeConnection();
    }
}
