package com.example.uros.dnd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Uros on 0025 25 May.
 */
public class Trigger extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triger);



    }

    public void onClick_Finish(View v){

        EditText editText = (EditText) findViewById(R.id.editNameAct);
        editText.setVisibility(View.VISIBLE);
        String gatherText = editText.getText().toString();

        Intent intent = new Intent(Trigger.this, ListActivity.class);
        startActivity(intent);

    }

}
