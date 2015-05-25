package com.example.uros.dnd;

/**
 * Created by Uros on 0024 24 May.
 */
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MapPane extends Activity {

    private final LatLng LOCATION_BURNABY = new LatLng(44.789850, 20.482035);

    // Google Map
    private GoogleMap googleMap;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                R.id.map)).getMap();

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_BURNABY, 13);
        googleMap.animateCamera(update);
        //googleMap.addMarker(new MarkerOptions().position(LOCATION_BURNABY).title("Hello I'm here!"));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMyLocationEnabled(true);


        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title("Kliknite ovde za potvrdu");
                //latLng.latitude + " : " + latLng.longitude

                // Clears the previously touched position
                googleMap.clear();

                // Animating to the touched position
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                googleMap.addMarker(markerOptions);
            }
        });

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                MarkerOptions markerOptions = new MarkerOptions();


                Intent intent = new Intent(MapPane.this, Trigger.class);
                startActivity(intent);


              //  EditText editText = (EditText) findViewById(R.id.editName);
               // editText.setVisibility(View.VISIBLE);
               // String gatherText = editText.getText().toString();

            }
        });

       // try {
            // Loading map
       //     initilizeMap();

      //  } catch (Exception e) {
      //      e.printStackTrace();
      //  }

    }

    public void onClick_City(View v) {
        //CameraUpdate update = CameraUpdateFactory.newLatLng(LOCATION_BURNABY);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_BURNABY, 14);
        googleMap.animateCamera(update);

    }



    /**
     * function to load map. If map is not created it will create it for you

    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
     }
     * */

/*
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }
    * */



}