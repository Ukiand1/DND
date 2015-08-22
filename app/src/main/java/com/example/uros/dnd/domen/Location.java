package com.example.uros.dnd.domen;

/**
 * Created by Nikola on 7/26/2015.
 */
public class Location {

    private long id;
    private String name;
    private String latitude;
    private String longitude;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public Location(long id, String name, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location() {

    }

    @Override
    public String toString() {
        return name;
    }


}
