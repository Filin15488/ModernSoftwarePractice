package com.Filin.View;

import com.Filin.Model.Person;
import com.Filin.Model.Role;
import com.Filin.ViewModel.PersonViewModel;
import com.Filin.ViewModel.RoleViewModel;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class WindowEditPerson extends Window{
    private JPanel contentPane = new JPanel();
    private JTextArea firstNameField = new JTextArea(1,13);
    private JTextArea lastNameField = new JTextArea(1,13);
    private JComboBox<String> rolesJComboBox;
    private RoleViewModel roleViewModel;
    private JDateChooser dateChooser = new JDateChooser();

    private Person personToEdit;


    private JPanel buttonPanel = new JPanel();
    private JButton okButton = new JButton("Сохранить");
    private JButton cancelButton = new JButton("Отменить");

    private WindowEmployee parentWindow; // ссылка на родительское окно

    public WindowEditPerson(WindowEmployee parentWindow) {
        this.parentWindow = parentWindow;
        roleViewModel = parentWindow.getPersonViewModel().getRoleViewModel();
        frame.setTitle("Edit Person");
        contentPane.setLayout(new GridBagLayout());

        int width = 300;
        int height = 200;
        frame.setBounds((getScreenSize().width - width) / 2, (getScreenSize().height - height) / 2, width, height);

//         System.out.println(roleViewModel);

        ArrayList<String> listRoles = new ArrayList<>();

        for (Role role : roleViewModel.getRoles()) {
            listRoles.add(role.getNameRole());
        }

        rolesJComboBox = new JComboBox<>(listRoles.toArray(new String[listRoles.size()]));
        rolesJComboBox.setPreferredSize(new Dimension(width/2, 25)); // 200 - ширина, 25 - высота

        dateChooser.setPreferredSize(new Dimension(width/2, 25));

//         обработчики кнопок
        cancelButton.addActionListener(e -> {
            frame.dispose();
        });

        okButton.addActionListener(e -> {
            // Получение ViewModel для обновления списка пользователей
            PersonViewModel personViewModel = parentWindow.getPersonViewModel();

            // Обновление данных в объекте Person
            Date selectedDate = dateChooser.getDate();
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(selectedDate);

            personToEdit.setFirstName(firstNameField.getText());
            personToEdit.setLastName(lastNameField.getText());
            personToEdit.setBirthday(gregorianCalendar);

            String selectedRoleName = (String) rolesJComboBox.getSelectedItem();
            if (!selectedRoleName.equals(roleViewModel.getNameRoleById(personToEdit.getRoleId()))) {
                // Если роль изменилась, обновляем roleId
                int selectedRoleId = roleViewModel.getIdByNameRole(selectedRoleName);
                personToEdit.setRoleId(selectedRoleId);
            }

            // Обновление таблицы в родительском окне
            parentWindow.refreshTable();

            // Закрытие окна
            frame.dispose();
        });


    }

    public void draw(){
        // Расположение компонентов на contentPane
        contentPane.add(new JLabel("Должность:"), getConstraints(0, 0, 1, 1));
        contentPane.add(rolesJComboBox, getConstraintsWhitoutFill(1, 0, 1, 1));

        contentPane.add(new JLabel("Фамилия:"), getConstraints(0, 1, 1, 1));
        contentPane.add(lastNameField, getConstraintsWhitoutFill(1, 1, 1, 1));

        contentPane.add(new JLabel("Имя:"), getConstraints(0, 2, 1, 1));
        contentPane.add(firstNameField, getConstraintsWhitoutFill(1, 2, 1, 1));

        contentPane.add(new JLabel("Дата рождения:"), getConstraints(0, 3, 1, 1));
//        dateChooser.setDate(new Date());
        contentPane.add(dateChooser, getConstraintsWhitoutFill(1, 3, 1, 1));

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        frame.add(contentPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setPersonToEdit(Person personToEdit){
        this.personToEdit = personToEdit;
        firstNameField.setText(personToEdit.getFirstName());
        lastNameField.setText(personToEdit.getLastName());
        rolesJComboBox.setSelectedItem(
                roleViewModel.getNameRoleById(personToEdit.getRoleId())
        );
        Date date = personToEdit.getBirthday().getTime();
        dateChooser.setDate(date);
    }
    public void updateData(){
        roleViewModel = parentWindow.getPersonViewModel().getRoleViewModel();
        rolesJComboBox.removeAllItems();
        for (Role role : roleViewModel.getRoles()) {

            rolesJComboBox.addItem(role.getNameRole());
//            System.out.println(role.getNameRole());
        }
    }
}
