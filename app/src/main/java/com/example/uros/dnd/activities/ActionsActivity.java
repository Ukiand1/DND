package com.example.uros.dnd.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.uros.dnd.R;
import com.example.uros.dnd.db.ActionDataSource;
import com.example.uros.dnd.db.LocationDataSource;
import com.example.uros.dnd.domen.Action;
import com.example.uros.dnd.domen.Location;

import java.util.List;

/**
 * Created by Uros on 0025 25 May.
 */
public class ActionsActivity extends Activity{


    private String locationName;
    private double latitude;
    private double longitude;
    private int radius;

    private String actionName;
    private boolean isVibrate;
    private int soundLevel;
    private String messageCall;


    private Spinner comboBox;
    private EditText editName;
    private RadioButton rbtnVibrationYes;
    private RadioButton rbtnVibrationNo;
    private SeekBar progressSound;
    private EditText editMessage;
    private Button btnFinish;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);

        comboBox = (Spinner) findViewById(R.id.comboActions);
        editName = (EditText) findViewById(R.id.editName);
        rbtnVibrationYes = (RadioButton) findViewById(R.id.rbtnVibrationYes);
        rbtnVibrationNo = (RadioButton) findViewById(R.id.rbtnVibrationNo);
        rbtnVibrationNo.setChecked(true);
        progressSound = (SeekBar) findViewById(R.id.progressSound);
        editMessage = (EditText) findViewById(R.id.editMessage);
        btnFinish = (Button)findViewById(R.id.btnFinish);


        Intent intent = getIntent();

        latitude = intent.getDoubleExtra("latitude",0.0);
        longitude = intent.getDoubleExtra("longitude", 0.0);
        locationName = intent.getStringExtra("locationName");
        radius = intent.getIntExtra("radius", 0);

        addListenerToFinishButton();

        fillComboBox();


    }

    private void fillComboBox() {

        ActionDataSource actionDataSource = new ActionDataSource(getApplicationContext());
        List<Action> actions = actionDataSource.getAllActions();


        ArrayAdapter<Action> adapter = new ArrayAdapter<Action>(this, android.R.layout.simple_spinner_item, actions);
        comboBox.setAdapter(adapter);

    }

    private void addListenerToFinishButton() {
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                actionName = editName.getText().toString();
                if (rbtnVibrationNo.isChecked())
                    isVibrate = false;
                else if (rbtnVibrationYes.isChecked()) {
                    isVibrate = true;
                }

                soundLevel = progressSound.getProgress();

                if (!editMessage.getText().toString().isEmpty())
                    messageCall= editMessage.getText().toString();
                else
                    messageCall = null;

                if (actionName == null || actionName.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "You need to enter action name.",Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                Action action = new Action();
                action.setName(actionName);
                action.setCall(messageCall);
                action.setSound(soundLevel);
                action.setVibrate(isVibrate);

                Location location = new Location();
                location.setLatitude(latitude);
                location.setLongitude(longitude);
                location.setName(locationName);
                location.setRadius(radius);
                location.setEnabled(true);
                location.setAction(action);

                LocationDataSource locationDataSource = new LocationDataSource(getApplicationContext());
                locationDataSource.insertLocation(location);

            }
        });



    }


}
