package com.digitalhomeland.organizer.models;

/**
 * Created by Asus on 1/25/2018.
 */
/**
 * Created by Asus on 12/22/2017.
 */

public class LocationObj {
    String lat;
    String longi;
    String address;

    public LocationObj(String lat, String longi, String address){
        this.lat = lat;
        this.longi = longi;
        this.address = address;
    }

    public String getLat(){return lat;}
    public String getLong() {return longi;}
    public String getAddress() {return address;}

}
