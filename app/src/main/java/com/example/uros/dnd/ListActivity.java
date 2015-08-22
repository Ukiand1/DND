package com.example.uros.dnd;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uros.dnd.db.LocationDataSource;
import com.example.uros.dnd.domen.Location;

import java.util.ArrayList;
import java.util.List;


public class ListActivity extends Activity{

    private LocationDataSource datasource;

    ArrayList<String> animalsNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        //ovo treba u metodi da bude, onoj koja ce se prva pozivati da ne bi bilo null pointer
        datasource = new LocationDataSource(this);

         insertLocationExample();

        try {
            loadLocationsFromDb();
        } catch (Exception e) {
            System.out.println("Connection with database was unsuccessful");
        }

        // ListView animalList=(ListView)findViewById(R.id.listLocationView);



//        animalsNameList = new ArrayList<String>();
//
//        animalsNameList.add("DOG");
//        animalsNameList.add("CAT");
//        animalsNameList.add("HORSE");
//        animalsNameList.add("ELEPHANT");
//        animalsNameList.add("LION");
//        animalsNameList.add("COW");
//        animalsNameList.add("MONKEY");
//        animalsNameList.add("DEER");
//        animalsNameList.add("RABBIT");
//        animalsNameList.add("BEER");
//        animalsNameList.add("DONKEY");
//        animalsNameList.add("LAMB");
//        animalsNameList.add("GOAT");
//
//        //getAnimalNames();
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
        l1.setLatitude("123.232");
        l1.setLongitude("27.405");

        Location l2 = new Location();
        l2.setName("fourth");
        l2.setLatitude("223.232");
        l2.setLongitude("37.405");

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
        Intent intent = new Intent(ListActivity.this, Chooser.class);
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
