package com.example.uros.dnd.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import com.example.uros.dnd.R;
import com.example.uros.dnd.db.LocationDataSource;
import com.example.uros.dnd.db.ServiceDataSource;
import com.example.uros.dnd.domen.Action;
import com.example.uros.dnd.domen.Location;
import com.example.uros.dnd.domen.LocationAdapter;
import com.example.uros.dnd.services.GPSService;
import com.example.uros.dnd.services.NotificationService;
import com.example.uros.dnd.util.SoundProfileUtil;

import java.util.ArrayList;
import java.util.List;


public class MyLocationsActivity extends Activity{

    private LocationDataSource locationDatasource;
    private ServiceDataSource serviceDataSource;
    private ListView locationsList;
    private Switch switchService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_locations);
        locationDatasource = new LocationDataSource(this); //mora da se pozove na pocetku, zbog null pointera



        try {
            loadLocationsFromDb();
        } catch (Exception e) {
            System.out.println("Connection with database was unsuccessful");
        }

        switchService = (Switch) findViewById(R.id.switchService);

        serviceDataSource = new ServiceDataSource(this);
        int serviceStatus = serviceDataSource.getServiceStatus();

        if(serviceStatus ==0) {
            switchService.setChecked(false);
        }else {
            switchService.setChecked(true);
        }


        switchService = (Switch)findViewById(R.id.switchService);
        switchService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    SoundProfileUtil.setPreviousState(getApplicationContext());
                    GPSService.turnOnService(getApplicationContext());
                    serviceDataSource.updateServiceStatus(1);
                }
                else{
                    GPSService.turnOffService(getApplicationContext());
                    serviceDataSource.updateServiceStatus(0);
                }


            }
        });

    }

    private void loadLocationsFromDb() throws Exception {

//        locationsList=(ListView)findViewById(R.id.listLocationView);
//        List<Location> locations = locationDatasource.getAllLocations();
//        ArrayAdapter<Location> adapter = new ArrayAdapter<Location>(this, android.R.layout.simple_list_item_1, locations);
//        locationsList.setAdapter(adapter);

        locationsList=(ListView)findViewById(R.id.listLocationView);
        List<Location> locations = locationDatasource.getAllLocations();
        LocationAdapter customAdapter = new LocationAdapter(this, R.layout.location_adapter, locations);
        locationsList.setAdapter(customAdapter);

//        locationsList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.putExtra("Location", )
//            }
//        });

        locationsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Location location = (Location) locationsList.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(),EditActionActivity.class);
                intent.putExtra("lName", location.getName());
                intent.putExtra("lId",location.getLocation_id());
                Action a = location.getAction();
                intent.putExtra("actionId",a.getActionId());
                intent.putExtra("actionName",a.getName());
                intent.putExtra("actionCall",a.getCall());
                intent.putExtra("actionVibrate",a.isVibrate());
                intent.putExtra("actionSound",a.getSound());
                startActivity(intent);

            }
        });

    }

    private void insertLocationExample()
    {

        Location l1 = new Location();
        l1.setName("Location 1");
        l1.setLatitude(123.232);
        l1.setLongitude(27.405);
        l1.setRadius(50);
        l1.setEnabled(true);

        Action a1 = new Action();
        a1.setName("Vibrate");
        a1.setSound(0);
        a1.setVibrate(true);
        a1.setCall("Call you back.");

        l1.setAction(a1);

        Location l2 = new Location();
        l2.setName("Location 2");
        l2.setLatitude(223.232);
        l2.setLongitude(37.405);
        l2.setRadius(120);
        l2.setEnabled(false);
        l2.setAction(a1);

        locationDatasource.insertLocation(l1);
        locationDatasource.insertLocation(l2);

    }


   public void onClick_AddNew (View v){
       Intent intent = new Intent(MyLocationsActivity.this, MapActivity.class);
       startActivity(intent);
    }

    @Override
    protected void onResume() {
        locationDatasource.openConnection();
        super.onResume();
    }

    @Override
    protected void onPause() {
        locationDatasource.closeConnection();
        super.onPause();
    }
}
