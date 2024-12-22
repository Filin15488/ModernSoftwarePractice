package com.Filin.ViewModel;

import com.Filin.Model.Person;
import com.Filin.Model.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

@Data
//@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleViewModel {
    @Getter
    private ArrayList<Role> roles;
    @Getter
    private int COUNT_ROLE;
    private static String[] columnNames = {
            "Код",
            "Название роли"
    };

    private final String filePath = "src/main/java/com/Filin/json/RoleViewModel.json";

    //    dependency injection by noob

    private static RoleViewModel instance;

    // Метод для получения единственного экземпляра
    public static RoleViewModel getInstance() {
        if (instance == null) {
            instance = new RoleViewModel();
        }
        return instance;
    }

    // Приватный конструктор, чтобы не было возможности создать новый объект
    private RoleViewModel() {
        try {
//            roles = new ArrayList<>();
            roles = loadRolesFromFile();
//            System.out.println(roles);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

//    работа с json

    public ArrayList<Role> loadRolesFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), new TypeReference<ArrayList<Role>>() {});
    }

    // Сохранение в файл
    public void saveRolesToFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), roles);
    }


    public JTable getTable(){
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

    public int getIdByNameRole(String nameRole){
        for (Role role : roles) {
            if (role.getNameRole().equals(nameRole)) {
                return role.getId();
            }
        }
        return -1;
    }

}
