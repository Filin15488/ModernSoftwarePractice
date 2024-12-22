package com.Filin.ViewModel;

import com.Filin.Model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.IntStream;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class PersonViewModel {
    @Getter
    @Setter
    private RoleViewModel roleViewModel = RoleViewModel.getInstance();
    @Getter
    private static int COUNT_LIST;
    private static ArrayList<Person> persons = new ArrayList<>();
    private static final String[] columnNames = {
            "Код",
            "Фамилия",
            "Имя",
            "Должность",
            "Дата рождения"
    };

    private final String filePath = "src/main/java/com/Filin/json/PersonViewModel.json";
//    dependency injection by noob

    private static PersonViewModel instance;

    // Приватный конструктор, чтобы не было возможности создать новый объект
    private PersonViewModel() {
//        persons.add(Person.builder()
//                .id(++COUNT_LIST)
//                .RoleId(1)
//                .FirstName("Иванов")
//                .LastName("Иванов")
//                .birthday(new GregorianCalendar(1980, 2, 28))
//                .build());
//
//        persons.add(new Person(
//                ++COUNT_LIST,
//                2,
//                "Петр",
//                "Петров",
//                new GregorianCalendar(1981,03,20)
//        ));
//        persons.add(new Person(
//                ++COUNT_LIST,
//                3,
//                "Виктор",
//                "Викторович",
//                new GregorianCalendar(1982,04,15)
//        ));
//        persons.add(new Person(
//                ++COUNT_LIST,
//                2,
//                "Сидор",
//                "Сидоров",
//                new GregorianCalendar(1983,05,10)
//        ));

        try {
            persons = loadPersonFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

//    работа с json
    private ArrayList<Person> loadPersonFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), new TypeReference<ArrayList<Person>>() {});
    }
    public void savePersonsToFole () throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), persons);
    }

    // Метод для получения единственного экземпляра
    public static PersonViewModel getInstance() {
        if (instance == null) {
            instance = new PersonViewModel();
        }
        return instance;
    }


    public JTable getTable(){
        String[][] data = new String[persons.size()][columnNames.length];
        for (int i = 0; i < persons.size(); i++) {
            data[i][0] = String.valueOf(persons.get(i).getId());
            data[i][1] = String.valueOf(persons.get(i).getLastName());
            data[i][2] = String.valueOf(persons.get(i).getFirstName());
//            data[i][3] = String.valueOf(roleViewModel.getNameRoleById(persons.get(i).getRoleId()));
            if (roleViewModel.getNameRoleById(persons.get(i).getRoleId()) != null){
                data[i][3] = String.valueOf(roleViewModel.getNameRoleById(persons.get(i).getRoleId()));
            }
            else {
                data[i][3] = "None";
            }
            data[i][4] = String.valueOf(persons.get(i).getBirthday().get(Calendar.DAY_OF_MONTH)) +
                    "." +
                    String.valueOf(persons.get(i).getBirthday().get(Calendar.MONTH)) +
                    "." +
                    String.valueOf(persons.get(i).getBirthday().get(Calendar.YEAR));
            ;

        }

// Создаем таблицу с полученными данными
        JTable table = new JTable(data, columnNames);
//        System.out.println(roleViewModel.getRoles());
//        JTable table = new JTable(data, columnNames);
        return table;
    }



//    public PersonViewModel()
//    {
////        System.out.println(persons);
//
//
//        persons.add(Person.builder()
//                        .id(++COUNT_LIST)
//                        .RoleId(1)
//                        .FirstName("Иванов")
//                        .LastName("Иванов")
//                        .birthday(new GregorianCalendar(1980, 2, 28))
//                .build());
//
//        persons.add(new Person(
//                ++COUNT_LIST,
//                2,
//                "Петр",
//                "Петров",
//                new GregorianCalendar(1981,03,20)
//        ));
//        persons.add(new Person(
//                ++COUNT_LIST,
//                3,
//                "Виктор",
//                "Викторович",
//                new GregorianCalendar(1982,04,15)
//        ));
//        persons.add(new Person(
//                ++COUNT_LIST,
//                2,
//                "Сидор",
//                "Сидоров",
//                new GregorianCalendar(1983,05,10)
//        ));
//
//
//
//    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

}
