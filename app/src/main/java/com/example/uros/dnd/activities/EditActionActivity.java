package com.example.uros.dnd.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.uros.dnd.R;

public class EditActionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_action);

        Intent intent = getIntent();
        long actionId = intent.getLongExtra("actionId",0);
        String actionName = intent.getStringExtra("actionName");
        boolean actionVibrate =  intent.getBooleanExtra("actionVibrate",false);
        String actionCall = intent.getStringExtra("actionCall");
        int actionSound = intent.getIntExtra("actionSound",0);

        String locationName = intent.getStringExtra("lName");
        long locationId = intent.getLongExtra("lId",0);


    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_edit_action, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
