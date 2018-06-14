package com.digitalhomeland.organizer.models;

/**
 * Created by Asus on 2/6/2018.
 */

public class Taskw {
    String id;
    String date;
    String title;
    String subject;
    String empId;
    String hours;
    String minutes;
    String dayOfWeek;
    String acceptedAt;
    String seqId;

    public Taskw(String id,String date,String title, String subject, String empId, String hours, String minutes, String dayOfWeek, String seqId){
        this.id = id;
        this.date = date;
        this.title = title;
        this.subject = subject;
        this.empId = empId;
        this.hours = hours;
        this.minutes = minutes;
        this.dayOfWeek = dayOfWeek;
        this.seqId = seqId;
        this.acceptedAt = "";
    }

    public String getTitle() {
        return title;
    }

    public String getSeqId() {
        return seqId;
    }

    public String getId() {
        return id;
    }

    public String getAcceptedAt() {
        return acceptedAt;
    }

    public String getSubject() {
        return subject;
    }
    public String getEmpId(){
        return empId;
    }
    public String getHours(){
        return hours;
    }
    public String getMinutes(){
        return minutes;
    }

    public String getDate() {
        return date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }
}
