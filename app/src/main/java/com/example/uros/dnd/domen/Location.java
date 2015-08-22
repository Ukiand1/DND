package com.example.uros.dnd.domen;

/**
 * Created by Nikola on 7/26/2015.
 */
public class Location {

    private long location_id;
    private String name;
    private double latitude;
    private double longitude;
    private long action_id;


    public Location(long action_id, String name, double latitude, double longitude) {
        this.action_id = action_id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location() {

    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(long location_id) {
        this.location_id = location_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getAction_id() {
        return action_id;
    }

    public void setAction_id(long action_id) {
        this.action_id = action_id;
    }





}
