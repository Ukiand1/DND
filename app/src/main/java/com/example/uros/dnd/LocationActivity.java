package com.example.uros.dnd;

import android.app.Activity;
import android.content.Intent;
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
public class LocationActivity extends Activity{

    ArrayList<String> animalsNameList;

    String outNo1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ListView animalList=(ListView)findViewById(R.id.listViewAnimals);


        animalsNameList = new ArrayList<String>();

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

        //getAnimalNames();


        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,animalsNameList);
        // Set The Adapter
        animalList.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        animalList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {

                String selectedAnimal=animalsNameList.get(position);
                Toast.makeText(getApplicationContext(), "Animal Selected : " + selectedAnimal, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LocationActivity.this, MapPane.class);
                intent.putExtra(selectedAnimal.toString(), outNo1);
                startActivity(intent);

            }
        });
    }


}

