package com.digitalhomeland.organizer.models;

/**
 * Created by Asus on 2/27/2018.
 */

public class StRoster {
    String id;
    String dayOfw;
    String date;
    String storeStatus;
    String events;
    String eventTitle;
    String eventSub;
    String pushed;

    // For generate
    public StRoster(String dayOfw, String date){
        this.id = "";
        this.dayOfw = dayOfw;
        this.date = date;
        this.storeStatus = "Closed";
        this.events = "None";
        this.eventTitle = "";
        this.eventSub = "";
        this.pushed = "";
    }

    public StRoster(String id,String dayOfw, String date, String storeStatus, String events, String eventTitle, String eventSub){
        this.id = id;
        this.dayOfw = dayOfw;
        this.date = date;
        this.storeStatus = storeStatus;
        this.events = events;
        this.eventTitle = eventTitle;
        this.eventSub = eventSub;
        this.pushed = "";
    }

    public void setStoreStatusToggle(String status){
        this.storeStatus = status;
        this.events = "None";
        this.eventTitle = "";
        this.eventSub = "";
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDayOfw() {
        return dayOfw;
    }

    public String getEvents() {
        return events;
    }

    public String getEventSub() {
        return eventSub;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getStoreStatus() {
        return storeStatus;
    }

    public String getPushed() {
        return pushed;
    }

    public void setStoreStatus(String storeStatus) {
        this.storeStatus = storeStatus;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setEventSub(String eventSub) {
        this.eventSub = eventSub;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInString(){
        return (this.id + " : " + this.dayOfw + ": " + this.date  + " : " + this.storeStatus + " : " + this.events + " : " + this.eventTitle + " : " + this.eventSub);
    }

    public void setPushed(String pushed) {
        this.pushed = pushed;
    }
}
