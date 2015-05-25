package com.example.uros.dnd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Uros on 0025 25 May.
 */
public class Location extends Activity{

    ArrayList<String> types;
    ArrayList<String> animalsNameList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // Get the reference of ListViewAnimals
        ListView animalList=(ListView)findViewById(R.id.listaLocationView);


        animalsNameList = new ArrayList<String>();
        getAnimalNames();
        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, animalsNameList);
        // Set The Adapter
        animalList.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        animalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedAnimal = animalsNameList.get(position);
                Toast.makeText(getApplicationContext(), "Animal Selected : " + selectedAnimal, Toast.LENGTH_LONG).show();
            }

            // argument position gives the index of item which is clicked

        });




    }
    void getAnimalNames() {
        animalsNameList.add("DOG");
        animalsNameList.add("CAT");
        animalsNameList.add("HORSE");
        animalsNameList.add("ELEPHANT");
        animalsNameList.add("LION");
        animalsNameList.add("COW");
        animalsNameList.add("MONKEY");
        animalsNameList.add("DEER");
        animalsNameList.add("RABBIT");
        animalsNameList.add("BEER");
        animalsNameList.add("DONKEY");
        animalsNameList.add("LAMB");
        animalsNameList.add("GOAT");


    }
}
