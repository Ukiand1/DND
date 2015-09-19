package com.example.uros.dnd.util;


import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Mihailo on 9/19/2015.
 */
public class GPSUtil {

    private static Integer circleId = null;

    public static boolean isInCircle(double lat,double lon, double circleLat, double circleLon, int radius) {
        float[] results = new float[1];
        Location.distanceBetween(lat,lon,circleLat,circleLon,results);
        int currentDistance = (int) results[0];
        if (currentDistance < radius)
            return true;
        return false;
    }

    public static LatLng getCurrentLocation(Context context){
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location myLocation = locationManager.getLastKnownLocation(provider);
        LatLng currentLocation  = new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
        return currentLocation;
    }

    public static boolean isInCircle(int currentCircleId) {
        if (circleId != null)
            return circleId.intValue() == currentCircleId;
        return false;
    }

    public static void setCircleId(Integer currentCircleId) {
        circleId = currentCircleId;
    }

}
