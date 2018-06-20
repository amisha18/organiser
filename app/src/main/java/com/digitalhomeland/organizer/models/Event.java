package com.digitalhomeland.organizer.models;

/**
 * Created by Asus on 1/11/2018.
 */

public class User {
    String Event_id;
    String Title;
    String Description;
    String Theme;
    String Days;
    String Startdate;
    String Enddate;
    String storeId;

    public User(String Evenr_id, String Title, String Description, String Theme, String Days, String Startdate,String Enddate, String storeId)
    {
        this.Event_id = Event_id;
        this.Title = Title;
        this.Description = Description;
        this.Theme = Theme;
        this.Days = Days;
        this.Startdate = Startdate;
        this.Enddate =Enddate;
        this.storeId = storeId;
    }


    public String getTitle(){return  Title;}
    public String getDescription() {return Description;}
    public String getTheme() {return Theme;}
    public String getDays() {return Days;}
    public String getStartdate() {return Startdate;}
    public String getEnddate() {return Enddate;}
    public String getStoreId() {return storeId;}

}
