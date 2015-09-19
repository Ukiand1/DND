package com.example.uros.dnd.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.uros.dnd.db.LocationDataSource;
import com.example.uros.dnd.domen.Action;
import com.example.uros.dnd.domen.Location;
import com.example.uros.dnd.util.GPSUtil;
import com.example.uros.dnd.util.SoundProfileUtil;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Uros on 0005 05 Sep.
 */
public class GPSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        LatLng curLatLng= GPSUtil.getCurrentLocation(context);
        double lat = curLatLng.latitude;
        double lon = curLatLng.longitude;
        LocationDataSource locationDataSource = new LocationDataSource(context);
        List<Location> locations = locationDataSource.getAllLocations();

        for (Location location:locations ) { //prolaz kroz sve lokacije
            if (location.isEnabled()) { //ako je lokacija enables
                double circleLat = location.getLatitude();
                double circleLon = location.getLongitude();
                int radius = location.getRadius();
                if (GPSUtil.isInCircle(lat,lon,circleLat,circleLon,radius)) {//ako je u circle potrebno je staviti telefon u pravi mod
                    int circleId = (int) location.getLocation_id(); //vraca location_id sto je ustvari circleId

                    if (!GPSUtil.isInCircle(circleId)) { //ako je true nece ovde uci, jer se u prethofnoj iteraciji to vec desilo
                                                         //samo ako je false ce uci, to znaci da je sad prvi put usao u taj circle
                                                         //potrebno je setovati sve moguce stvari na osnovu Action
                        if (!SoundProfileUtil.isPreviousStateSetted()) { //ako nije setovano prethodno stanje potrebno ga je setovati
                                                                         //da kad izadje iz kruga vrati se na to stanje
                                                                         //moguca situacija: izasao iz jednog kruga i odm usao u drugi pa
                                                                         //setovanp stanje ne treba dirate (nece se ovde nista desiti
                                                                         //osim setovanja telefona na profil iz Action
                            SoundProfileUtil.setPreviousState(context);
                        }
                        Action action = location.getAction();
                        int soundLevel = action.getSound();
                        boolean vibration = action.isVibrate();
                        SoundProfileUtil.setMode(context, soundLevel, vibration);
                        GPSUtil.setCircleId((int) location.getLocation_id()); //location_id je ustvari circleID
                    }
                return;
                }
            }
        }
        SoundProfileUtil.resetMode(context);
    }
}
