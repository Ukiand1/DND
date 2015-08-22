package com.example.uros.dnd.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.uros.dnd.R;

/**
 * Created by Uros on 0025 25 May.
 */
public class ActionsActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);



    }

    public void onClick_Finish(View v){

        EditText editText = (EditText) findViewById(R.id.editNameAct);
        editText.setVisibility(View.VISIBLE);
        String gatherText = editText.getText().toString();

        Intent intent = new Intent(ActionsActivity.this, MyLocationsActivity.class);
        startActivity(intent);



    }

}
