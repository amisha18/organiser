package com.digitalhomeland.inventorymanager.models;

/**
 * Created by prthma on 13-05-2017.
 */

public class Notif {
    String id;
    String type;
    String title;
    String subject;
    String date;
    String employeeId;
    Integer seqId;

    public Notif(){

    }

    public Notif(String id,String type, String title, String subject, String date, String employeeId, Integer seqId){
        this.id = id;
        this.type = type;
        this.title = title;
        this.subject = subject;
        this.date = date;
        this.employeeId = employeeId;
        this.seqId = seqId;
    }

    public String getId(){ return this.id;}

    public String getType(){
        return this.type;
    }

    public String getTitle(){
        return  this .title;
    }

    public String getSubject(){
        return this.subject;
    }

    public String getDate(){
        return this.date;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public Integer getSeqId(){return seqId;}

    public void setTitle(String title){
        this.title = title;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setSeqId(Integer seqId1){ this.seqId = seqId1;}
}
