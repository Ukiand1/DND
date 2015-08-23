package com.example.uros.dnd.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.uros.dnd.R;
import com.example.uros.dnd.db.LocationDataSource;
import com.example.uros.dnd.domen.Action;
import com.example.uros.dnd.domen.Location;
import com.example.uros.dnd.services.NotificationService;

import java.util.ArrayList;
import java.util.List;


public class MyLocationsActivity extends Activity{

    private LocationDataSource locationDatasource;
    ListView locationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_locations);
        locationDatasource = new LocationDataSource(this); //mora da se pozove na pocetku, zbog null pointera
        //zakomentarisati za drugi poziv
       // insertLocationExample();

        try {
            loadLocationsFromDb();
        } catch (Exception e) {
            System.out.println("Connection with database was unsuccessful");
        }

    }

    private void loadLocationsFromDb() throws Exception {

        locationsList=(ListView)findViewById(R.id.listLocationView);
        List<Location> locations = locationDatasource.getAllLocations();
        ArrayAdapter<Location> adapter = new ArrayAdapter<Location>(this, android.R.layout.simple_list_item_1, locations);
        locationsList.setAdapter(adapter);

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_list, menu);
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
