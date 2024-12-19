package com.Filin.ViewModel;

import com.Filin.Model.Person;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PersonViewModel {
    private RoleViewModel roleViewModel = new RoleViewModel();
    private static int COUNT_LIST;
    private static ArrayList<Person> persons;
    private static String[] columnNames = {
            "Код",
            "Фамилия",
            "Имя",
            "Должность",
            "Дата рождения"
    };


    public JTable getTable(){
        String[][] data = new String[persons.size()][columnNames.length];
        for (int i = 0; i < persons.size(); i++) {
            data[i][0] = String.valueOf(persons.get(i).getId());
            data[i][1] = String.valueOf(persons.get(i).getLastName());
            data[i][2] = String.valueOf(persons.get(i).getFirstName());
//            data[i][3] = String.valueOf(roleViewModel.getNameRoleById(persons.get(i).getRoleId()));
            data[i][3] = String.valueOf(roleViewModel.getNameRoleById(persons.get(i).getRoleId()));
            data[i][4] = String.valueOf(persons.get(i).getBirthday().get(Calendar.DAY_OF_MONTH)) +
                    "." +
                    String.valueOf(persons.get(i).getBirthday().get(Calendar.MONTH)) +
                    "." +
                    String.valueOf(persons.get(i).getBirthday().get(Calendar.YEAR));
            ;

        }
        System.out.println(roleViewModel.getRoles());
        JTable table = new JTable(data, columnNames);
        return table;
    }


    public PersonViewModel()
    {
        persons = new ArrayList<>();

        persons.add(new Person(
                ++COUNT_LIST,
                1,
                "Иван",
                "Иванов",
                new GregorianCalendar(1980,02,28)
        ));
        persons.add(new Person(
                ++COUNT_LIST,
                2,
                "Петр",
                "Петров",
                new GregorianCalendar(1981,03,20)
        ));
        persons.add(new Person(
                ++COUNT_LIST,
                3,
                "Виктор",
                "Викторович",
                new GregorianCalendar(1982,04,15)
        ));
        persons.add(new Person(
                ++COUNT_LIST,
                2,
                "Сидор",
                "Сидоров",
                new GregorianCalendar(1983,05,10)
        ));



    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

}
