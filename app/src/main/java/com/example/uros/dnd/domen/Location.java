package com.example.uros.dnd.domen;

/**
 * Created by Nikola on 7/26/2015.
 */
public class Location {

    private long location_id;
    private String name;
    private double latitude;
    private double longitude;
    private int radius;
    private boolean enabled;
    private Action action;



    public Location() {

    }

    public Location(long location_id, String name, double latitude, double longitude, int radius, boolean enabled, Action action) {
        this.location_id = location_id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.enabled = enabled;
        this.action = action;
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

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return name;
    }
}
