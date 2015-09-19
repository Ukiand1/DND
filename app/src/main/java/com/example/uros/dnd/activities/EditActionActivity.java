package com.example.uros.dnd.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.uros.dnd.R;
import com.example.uros.dnd.db.ActionDataSource;
import com.example.uros.dnd.domen.Action;

public class EditActionActivity extends Activity {


    private EditText editName;
    private RadioButton rbtnVibrationYes;
    private RadioButton rbtnVibrationNo;
    private SeekBar progressSound;
    private EditText editMessage;
    private Button btnUpdate;
    private Button btnDelete;
    private TextView locationNameText;

    private ActionDataSource dataSource;


    private long actionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_action);


        editName = (EditText) findViewById(R.id.editActionName);
        rbtnVibrationYes = (RadioButton) findViewById(R.id.rbtnVibrationYesEdit);
        rbtnVibrationNo = (RadioButton) findViewById(R.id.rbtnVibrationNoEdit);

        progressSound = (SeekBar) findViewById(R.id.progressSoundEdit);
        editMessage = (EditText) findViewById(R.id.editMessageText);
        btnUpdate = (Button)findViewById(R.id.btnUpdateAction);
        btnDelete = (Button) findViewById(R.id.btnDeleteAction);
        locationNameText = (TextView) findViewById(R.id.locatioNameEdit);

        final Intent intent = getIntent();
        arrangeActionDisplay(intent);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aName = editName.getText().toString();
                String aMessage = editMessage.getText().toString();
                boolean vibrate = false;
                if(rbtnVibrationYes.isChecked())
                    vibrate = true;
                int aSound = progressSound.getProgress();
                Action action =  new Action(actionId,aName,aMessage,vibrate,aSound);
                updateAction(action);

                Intent intent = new Intent(EditActionActivity.this, MyLocationsActivity.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    private void arrangeActionDisplay(Intent intent){

        actionId = intent.getLongExtra("actionId",0);
        String actionName = intent.getStringExtra("actionName");
        boolean actionVibrate =  intent.getBooleanExtra("actionVibrate", false);
        String actionCall = intent.getStringExtra("actionCall");
        int actionSound = intent.getIntExtra("actionSound",0);

        String locationName = intent.getStringExtra("lName");
        long locationId = intent.getLongExtra("lId",0);

        editName.setText(actionName);
        if (actionVibrate)
            rbtnVibrationYes.setChecked(true);
        rbtnVibrationNo.setChecked(true);
        progressSound.setProgress(actionSound);
        editMessage.setText(actionCall);
        locationNameText.setText(locationName);



    }

    public void updateAction(Action a){

        dataSource = new ActionDataSource(getApplicationContext());
        dataSource.openConnection();
        dataSource.updateAction(a);
        dataSource.closeConnection();


    }

    public void deleteAction(long actionId){

        dataSource = new ActionDataSource(getApplicationContext());
        dataSource.openConnection();
        dataSource.deleteAction(actionId);
        dataSource.closeConnection();
    }


}
