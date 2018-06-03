package com.digitalhomeland.storeadmin.models;

/**
 * Created by prthma on 15-05-2017.
 */

public class Attendance {
    String date;
    String value;
    String employeeId;

    public Attendance(){}
    public Attendance(String date, String value, String employeeId){
        this.date = date;
        this.value = value;
        this.employeeId = employeeId;
    }

    public String getDate(){
        return this.date;
    }

    public String getValue(){
        return this.value;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setValue(String value){
        this.value = value;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
