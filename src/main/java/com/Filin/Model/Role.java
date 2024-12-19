package com.Filin.Model;

public class Role {
    private int id;
    private String NameRole;

    public void setId(int id) {
        this.id = id;
    }

    public void setNameRole(String nameRole) {
        NameRole = nameRole;
    }

    public int getId() {
        return id;
    }

    public String getNameRole() {
        return NameRole;
    }

    public Role() {}

    public Role(int id, String nameRole) {
        this.id = id;
        NameRole = nameRole;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", NameRole='" + NameRole + '\'' +
                '}';
    }
}
