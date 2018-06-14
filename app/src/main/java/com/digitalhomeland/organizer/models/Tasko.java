package com.digitalhomeland.organizer.models;

/**
 * Created by Asus on 2/6/2018.
 */

public class Tasko {
    String id;
    String title;
    String subject;
    String empId;
    String hours;
    String minutes;
    String dateToSet;
    String acceptedAt;
    String seqId;

    public Tasko(String id,String title, String subject, String empId, String hours, String minutes, String dateToSet, String acceptedAt, String seqId){
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.empId = empId;
        this.hours = hours;
        this.minutes = minutes;
        this.dateToSet = dateToSet;
        this.acceptedAt = acceptedAt;
        this.seqId = seqId;
    }

    public String getTitle() {
        return title;
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
    public String getDateToSet() {
        return dateToSet;
    }

    public String getId() {
        return id;
    }

    public String getSeqId() {
        return seqId;
    }

    public String getAcceptedAt() {
        return acceptedAt;
    }
}
