package com.example.uros.dnd.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.uros.dnd.R;
import com.example.uros.dnd.broadcast.CallReceiver;

import junit.framework.Test;

public class TestActivity extends AppCompatActivity {

    private Button ukljuci;
    private Button iskljuci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ukljuci = (Button) findViewById(R.id.buttonUkljuci);
        iskljuci = (Button) findViewById(R.id.buttonIskljuci);

        ukljuci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm  = TestActivity.this.getPackageManager();
                ComponentName componentName = new ComponentName(TestActivity.this, CallReceiver.class);
                pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP);
                Toast.makeText(getApplicationContext(), "activated", Toast.LENGTH_LONG).show();


            }
        });

        iskljuci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm  = TestActivity.this.getPackageManager();
                ComponentName componentName = new ComponentName(TestActivity.this, CallReceiver.class);
                pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
                Toast.makeText(getApplicationContext(), "cancelled", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
