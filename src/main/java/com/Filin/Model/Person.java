package com.Filin.Model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Person {
    private int id;
    private int RoleId;
    private String FirstName;
    private String LastName;
    private GregorianCalendar birthday;

    public Person() {}

    public Person(int id, int roleId, String firstName, String lastName, GregorianCalendar birthday) {
        this.id = id;
        RoleId = roleId;
        FirstName = firstName;
        LastName = lastName;
        this.birthday = birthday;
    }

    public void setBirthday(GregorianCalendar birthday) {
        this.birthday = birthday;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public String getFirstName() {
        return FirstName;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return LastName;
    }

    public int getRoleId() {
        return RoleId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", RoleId=" + RoleId +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
