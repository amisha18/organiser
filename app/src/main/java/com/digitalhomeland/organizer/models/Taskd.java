package com.digitalhomeland.organizer.models;

/**
 * Created by Asus on 2/6/2018.
 */

public class Taskd {
    String _id;
    String date;
    String title;
    String subject;
    String empId;
    String hours;
    String minutes;
    String acceptedAt;
    String seqId;

    public Taskd(String _id,String date,String title, String subject, String empId, String hours, String minutes, String seqId){
        this._id = _id;
        this.date = date;
        this.title = title;
        this.subject = subject;
        this.empId = empId;
        this.hours = hours;
        this.minutes = minutes;
        this.acceptedAt = "";
        this.seqId = seqId;
    }

    public Taskd(String _id,String date,String title, String subject, String empId, String hours, String minutes, String seqId, String acceptedAt){
        this._id = _id;
        this.date = date;
        this.title = title;
        this.subject = subject;
        this.empId = empId;
        this.hours = hours;
        this.minutes = minutes;
        this.acceptedAt = acceptedAt;
        this.seqId = seqId;
    }

    public String get_id() {
        return _id;
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
    public String getAcceptedAt() {return acceptedAt;}

    public String getDate() {
        return date;
    }

    public String getSeqId() {
        return seqId;
    }
}
