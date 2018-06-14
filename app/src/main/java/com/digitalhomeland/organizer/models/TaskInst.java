package com.digitalhomeland.organizer.models;

/**
 * Created by Asus on 3/12/2018.
 */

public class TaskInst {
    String id;
    String date;
    String title;
    String subject;
    String hours;
    String minutes;
    String acceptedAt;
    String type;

    public TaskInst(Taskd tsk){
        this.id = tsk.get_id();
        this.date = tsk.getDate();
        this.title = tsk.getTitle();
        this.subject = tsk.getSubject();
        this.hours = tsk.getHours();
        this.minutes = tsk.getMinutes();
        this.acceptedAt = tsk.getAcceptedAt();
        this.type = "taskd";
    }

    public TaskInst(Taskw tsk){
        this.id = tsk.getId();
        this.date = tsk.getDate();
        this.title = tsk.getTitle();
        this.subject = tsk.getSubject();
        this.hours = tsk.getHours();
        this.minutes = tsk.getMinutes();
        this.acceptedAt = tsk.getAcceptedAt();
        this.type = "taskw";
    }

    public TaskInst(Tasko tsk){
        this.id = tsk.getId();
        this.date = tsk.getDateToSet();
        this.title = tsk.getTitle();
        this.subject = tsk.getSubject();
        this.hours = tsk.getHours();
        this.minutes = tsk.getMinutes();
        this.acceptedAt = tsk.getAcceptedAt();
        this.type = "tasko";
    }

    public String getId() {
        return id;
    }

    public String getHours() {
        return hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getAcceptedAt() {
        return acceptedAt;
    }
}
