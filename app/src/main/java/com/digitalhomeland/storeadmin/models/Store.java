package com.digitalhomeland.storeadmin.models;

/**
 * Created by Asus on 1/31/2018.
 */

public class Store {

    String id;
    String storeId;
    String name;
    String city;
    String state;
    String lat;
    String longi;
    String address;
    String empCount;
    String keyState;
    String sellerId;
    String adminId;
    String closingDay;
    String rosterGenDay;

    public Store(String id, String lat, String longi, String address){
        this.id = id;
        this.lat = lat;
        this.longi = longi;
        this.address = address;
        this.keyState = "INACTIVE";
    }

    public Store(String id,  String name, String storeId, String city, String state, String lat, String longi, String address,String empCount, String keyState, String sellerId, String closingDay, String rosterGenDay, String adminId){
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
        this.lat = lat;
        this.longi = longi;
        this.address = address;
        this.empCount = empCount;
        this.keyState = keyState;
        this.sellerId = sellerId;
        this.adminId = adminId;
        this.closingDay = closingDay;
        this.rosterGenDay = rosterGenDay;
        this.storeId = storeId;
    }

    public String getClosingDay() {
        return closingDay;
    }

    //to be set seperately
    public void setClosingDay(String closingDay) {
        this.closingDay = closingDay;
    }

    public String getCity() {
        return city;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getLat() {
        return lat;
    }

    public String getLongi() {
        return longi;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getAddress() {
        return address;
    }

    public String getEmpCount() {
        return empCount;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getKeyState() {return keyState;}

    public String getRosterGenDay() {
        return rosterGenDay;
    }

    public String getSellerId() {
        return sellerId;
    }
}
