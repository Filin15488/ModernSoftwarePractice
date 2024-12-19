package com.Filin.ViewModel;

import com.Filin.Model.Role;

import javax.swing.*;
import java.util.ArrayList;

public class RoleViewModel {
    private static ArrayList<Role> roles;
    private int COUNT_ROLE;
    private static String[] columnNames = {
            "Код",
            "Название роли"
    };

    {
        roles = new ArrayList<>();
        roles.add(new Role(++COUNT_ROLE, "Директор"));
        roles.add(new Role(++COUNT_ROLE, "Бухгалтер"));
        roles.add(new Role(++COUNT_ROLE, "Менеджер"));

    }

    public static JTable getTable(){
        String[][]data = new String[roles.size()][columnNames.length];
        for (int i = 0; i < roles.size(); i++) {
            data[i][0] = String.valueOf(roles.get(i).getId());
            data[i][1] = String.valueOf(roles.get(i).getNameRole());
        }


        JTable table = new JTable(data, columnNames);
        return table;
    }

    public String getNameRoleById(int id){
        for (Role role : roles) {
            if (role.getId() == id) {
                return role.getNameRole();
            }
        }
        return null;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }
}
