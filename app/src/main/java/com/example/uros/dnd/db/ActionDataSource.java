package com.example.uros.dnd.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.uros.dnd.domen.Action;
import com.example.uros.dnd.domen.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Uros on 0023 23 Aug.
 */
public class ActionDataSource {

    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public ActionDataSource(Context context){
        dbHelper = new SQLiteHelper(context);
    }

    public void openConnection() {
        database = dbHelper.getWritableDatabase();
    }

    public void closeConnection() {
        dbHelper.close();
    }



    private String[] actionColumns = {SQLiteHelper.ACTION_ACTION_ID,
            SQLiteHelper.ACTION_NAME,
            SQLiteHelper.ACTION_CALL,
            SQLiteHelper.ACTION_SOUND,
            SQLiteHelper.ACTION_VIBRATION};


    public ActionDataSource(SQLiteDatabase database){
        this.database = database;
    }


    public Action getActionByName(String actionName){

        String whereClause = SQLiteHelper.ACTION_NAME+"=?";
        String[] whereParam = {actionName};
        Action action = null;
        Cursor cursor = database.query(SQLiteHelper.TABLE_ACTION, actionColumns,whereClause,whereParam,null,null,null);
        if (cursor.getCount() == 0)
            return null;
        cursor.moveToFirst();
        action = cursorToAction(cursor);
        cursor.close();
        return action;
    }

    public List<Action> getAllActions(){
        openConnection();
        List<Action> actions = new ArrayList<Action>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_ACTION, actionColumns,null,null,null,null,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){

            Action action = cursorToAction(cursor);
            actions.add(action);
            cursor.moveToNext();
        }

        cursor.close();

        closeConnection();
        return actions;
    }



    public Action getActionByID(long actionId){

        String whereClause = SQLiteHelper.ACTION_ACTION_ID+"=?";
        String[] whereParam = {actionId+""};

        Action action = null;


        Cursor cursor = database.query(SQLiteHelper.TABLE_ACTION, actionColumns,whereClause,whereParam,null,null,null);
        if (cursor.getCount() == 0) {
            return null;
        }

        cursor.moveToFirst();

        action = cursorToAction(cursor);

        cursor.close();

        return action;
    }


    public void updateAction(Action a){
        int vibrate = 0;
        if(a.isVibrate())
            vibrate = 1;

        String query = "Update action set name ="+a.getName()+",call="+a.getCall()+",sound="+a.getSound()+",vibration="+vibrate+" where action_id="+a.getActionId();

        database.execSQL(query);

    }


    private Action cursorToAction(Cursor cursor) {
        Action action = new Action();
        action.setActionId(cursor.getLong(0));
        action.setName(cursor.getString(1));
        action.setCall(cursor.getString(2));
        action.setSound(cursor.getInt(3));
        int vibration = cursor.getInt(4);
        if (vibration == 1)
            action.setVibrate(true);
        else
            action.setVibrate(false);

        return action;

    }

    public long insertAction(Action action){

        ContentValues values = new ContentValues();

        values.put(SQLiteHelper.ACTION_NAME, action.getName());
        values.put(SQLiteHelper.ACTION_CALL, action.getCall());
        values.put(SQLiteHelper.ACTION_SOUND, action.getSound());
        if (action.isVibrate())
            values.put(SQLiteHelper.ACTION_VIBRATION, 1);
        else
            values.put(SQLiteHelper.ACTION_VIBRATION, 0);

        long id = database.insert(SQLiteHelper.TABLE_ACTION, null, values);
        return id;
    }



//    public void deleteLocation(Location location){
//
//        long id = location.getAction_id();
//        System.out.println("Deleting location with id "+id);
//        database.delete(SQLiteHelper.TABLE_LOCATIONS,SQLiteHelper.COLUMN_ID + " = " +id,null);
//    }




}
