package com.example.uros.dnd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Uros on 0025 25 May.
 */
public class Chooser extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);


    }

    public void onClick_Map(View v ){
        Intent intent = new Intent(Chooser.this, MapPane.class);
        startActivity(intent);
    }

}
