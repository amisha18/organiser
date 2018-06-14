package com.digitalhomeland.organizer.models;

/**
 * Created by Asus on 4/29/2018.
 */

public class Team {

    String id;
    String name;
    String managerId;
    String managerName;

    public Team(String id,String name, String managerId, String managerName){
        this.id = id;
        this.name = name;
        this.managerId = managerId;
        this.managerName = managerName;
    }

    public String getName() {
        return name;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerId() {
        return managerId;
    }

    public String getId() {
        return id;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}


