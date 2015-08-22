package com.example.uros.dnd.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.uros.dnd.R;
import com.example.uros.dnd.db.LocationDataSource;
import com.example.uros.dnd.domen.Location;
import com.example.uros.dnd.services.NotificationService;

import java.util.ArrayList;
import java.util.List;


public class MyLocationsActivity extends Activity{

    private LocationDataSource datasource;
//    NotificationService mService;

    ArrayList<String> animalsNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_locations);
//        Intent intent = new Intent(this, NotificationService.class);
//        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);


        //ovo treba u metodi da bude, onoj koja ce se prva pozivati da ne bi bilo null pointer
        datasource = new LocationDataSource(this);

        // insertLocationExample();

        try {
            loadLocationsFromDb();
        } catch (Exception e) {
            System.out.println("Connection with database was unsuccessful");
        }



//
//
//        // Create The Adapter with passing ArrayList as 3rd parameter
//        ArrayAdapter<String> arrayAdapter =
//                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,animalsNameList);
//        // Set The Adapter
//        animalList.setAdapter(arrayAdapter);
//
//        // register onClickListener to handle click events on each item
//        animalList.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            // argument position gives the index of item which is clicked
//            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
//            {
//
//                String selectedAnimal=animalsNameList.get(position);
//                Toast.makeText(getApplicationContext(), "Animal Selected : " + selectedAnimal, Toast.LENGTH_LONG).show();
//
//            }
//        });
    }

    private void loadLocationsFromDb() throws Exception {

        ListView locationsList=(ListView)findViewById(R.id.listLocationView);

        //datasource = new LocationDataSource(this);
        datasource.openConnection();

        List<Location> locations = datasource.getAllLocations();

        ArrayAdapter<Location> adapter = new ArrayAdapter<Location>(this, android.R.layout.simple_list_item_1, locations);

        locationsList.setAdapter(adapter);

    }

    private void insertLocationExample()
    {

        datasource.openConnection();

        Location l1 = new Location();
        l1.setName("third");
        l1.setLatitude(123.232);
        l1.setLongitude(27.405);

        Location l2 = new Location();
        l2.setName("fourth");
        l2.setLatitude(223.232);
        l2.setLongitude(37.405);

        datasource.createLocation(l1);
        datasource.createLocation(l2);

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
       Intent intent = new Intent(MyLocationsActivity.this, TestActivity.class);
       startActivity(intent);
    }

    @Override
    protected void onResume() {
        datasource.openConnection();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.closeConnection();
        super.onPause();
    }
}
