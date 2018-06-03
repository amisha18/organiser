package com.digitalhomeland.storeadmin.models;

/**
 * Created by prthma on 14-06-2017.
 */

public class Application {
    String id;
    String title;
    String subject;
    String date;
    String employeeId;
    String storeId;
    String acceptStatus;
    String seqId;

    public Application(){
    }

    public Application(String id, String title, String subject, String date, String employeeId, String seqId){
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.date = date;
        this.employeeId = employeeId;
        this.acceptStatus = "";
        this.seqId = seqId;
    }

    public Application(String id, String acceptStatus, String title, String subject, String date, String employeeId,String seqId){
        this.id = id;
        this.acceptStatus = acceptStatus;
        this.title = title;
        this.subject = subject;
        this.employeeId = employeeId;
        this.date = date;
        this.seqId = seqId;
    }

    public String getId(){ return this.id;}

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

    public String getAcceptStatus() {
        return acceptStatus;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getSeqId() {
        return seqId;
    }

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

    public void setAcceptStatus(String acceptStatus) {
        this.acceptStatus = acceptStatus;
    }
}
