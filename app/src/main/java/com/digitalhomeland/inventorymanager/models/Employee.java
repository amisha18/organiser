package com.digitalhomeland.inventorymanager.models;

/**
 * Created by Asus on 2/3/2018.
 */

public class Employee {
    String _id;
    String firstName;
    String middleName;
    String lastName;
    String phone;
    String email;
    String aadharId;
    String employeeId;
    String isManager;
    String teamName;
    String designation;
    String managerId;


    public Employee(String _id, String firstName, String middleName, String lastName, String phone, String email,String aadharId, String employeeId, String isManager)
    {
        this._id = _id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.aadharId = aadharId;
        this.employeeId = employeeId;
        this.isManager = isManager;
        this.teamName = "";
        this.designation = "";
        this.managerId = "";
    }

    public Employee( String firstName, String middleName, String lastName, String phone, String email,String aadharId, String employeeId, String isManager)
    {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.aadharId = aadharId;
        this.employeeId = employeeId;
        this.isManager = isManager;
        this.teamName = "";
        this.designation = "";
        this.managerId = "";
    }

    public Employee(String _id, String firstName, String middleName, String lastName, String phone, String email,String aadharId, String employeeId, String isManger, String teamName, String designation, String managerId)
    {
        this._id = _id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.aadharId = aadharId;
        this.employeeId = employeeId;
        this.isManager = isManger;
        this.teamName = teamName;
        this.designation = designation;
        this.managerId = managerId;
    }

    public String get_id(){return _id;}
    public String getFirstName(){return  firstName;}
    public String getMiddleName() {return middleName;}
    public String getLastName() {return lastName;}
    public String getPhone() {return phone;}
    public String getEmail() {return email;}
    public String getAadharId() {return aadharId;}
    public String getEmployeeId() { return employeeId;}

    public String getIsManager() {
        return isManager;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getDesignation() {
        return designation;
    }

    public String getManagerId() {
        return managerId;
    }
}
