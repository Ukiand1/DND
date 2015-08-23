package com.example.uros.dnd.activities;

/**
 * Created by Uros on 0024 24 May.
 */
import com.example.uros.dnd.R;
import com.example.uros.dnd.db.LocationDataSource;
import com.example.uros.dnd.domen.Action;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MapActivity extends Activity{


    private Marker marker;
    private Circle circle;

    // Google Map
    private GoogleMap googleMap;
    private SeekBar radiusBar;
    private Button btnConfirm;
    private EditText editLocationName;


    private double latitude;
    private double longitude;
    private int radius;
    //private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.uros.dnd.R.layout.activity_map);

        editLocationName = (EditText)findViewById(R.id.editLocationName);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        radiusBar = (SeekBar) findViewById(R.id.progresRadius);

        initializeMap();
        arrangeSeekBar();

        setConfirmOnClickListener();

        drawCircles();



    }

    private void drawCircles() {

        LocationDataSource locationDataSource = new LocationDataSource(getApplicationContext());
        List<com.example.uros.dnd.domen.Location> locations = locationDataSource.getAllLocations();
        for (com.example.uros.dnd.domen.Location location : locations) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            int radius = location.getRadius();

            LatLng latLng = new LatLng(latitude,longitude);
            drawCircle(radius,latLng);

        }


    }

    private void setConfirmOnClickListener() {

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationName = editLocationName.getText().toString();
                if (locationName == null || locationName.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Set name for your location.", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }


                if (latitude == 0 || longitude == 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Choose location first.", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                if (radius < 10) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Minimum radius is 10m.", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                Intent intent = new Intent(MapActivity.this, ActionsActivity.class);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                intent.putExtra("radius", radius);
                intent.putExtra("locationName", locationName);
                startActivity(intent);

            }
        });
    }

    private void arrangeSeekBar() {
        radiusBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (latitude == 0 || longitude == 0)
                    return;
                LatLng latLng = new LatLng(latitude, longitude);


                resizeCircle(progress, latLng);
                addMarker(latLng);

                radius = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    private void drawCircle(int radius, LatLng latLng) {
        CircleOptions co = new CircleOptions();
        co.radius(radius);
        co.center(latLng);
        googleMap.addCircle(co);


    }

    private void resizeCircle(int radius, LatLng latLng) {
        if (circle != null)
            circle.remove();
        CircleOptions co = new CircleOptions();
        co.radius(radius);
        co.center(latLng);
        circle = googleMap.addCircle(co);


    }


    private LatLng getCurrentLocation(){

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Create a criteria object to retrieve provider
        Criteria criteria = new Criteria();
        // Get the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Get Current Location
        Location myLocation = locationManager.getLastKnownLocation(provider);

        LatLng currentLocation  = new LatLng(myLocation.getLatitude(),myLocation.getLongitude());

        return currentLocation;
    }


    private void initializeMap(){
        googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                R.id.map)).getMap();
        LatLng currentLocation = getCurrentLocation();
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(currentLocation, 14);
        googleMap.animateCamera(update);
        //googleMap.addMarker(new MarkerOptions().position(LOCATION_BURNABY).title("Hello I'm here!"));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMyLocationEnabled(true);

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //MarkerOptions markerOptions = new MarkerOptions();
               // markerOptions.position(latLng);
                ;
                addMarker(latLng);
                refreshProgress();
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//                googleMap.addMarker(markerOptions);

                latitude = latLng.latitude;
                longitude = latLng.longitude;
            }
        });
    }


    private void addMarker(LatLng latLng) {
        if (marker != null)
            marker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        marker = googleMap.addMarker(markerOptions);

    }

    private void refreshProgress() {
        if (circle != null)
            circle.remove();
        radiusBar.setProgress(0);
    }

}





