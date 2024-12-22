package com.Filin;

import com.Filin.View.Window;
import com.Filin.View.WindowEmployee;
import com.Filin.View.WindowRole;
import com.Filin.ViewModel.PersonViewModel;
import com.Filin.ViewModel.RoleViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends Window {
//    private PersonViewModel personViewModel = new PersonViewModel();
    private PersonViewModel personViewModel = PersonViewModel.getInstance();
    private RoleViewModel roleViewModel = new RoleViewModel();
    private static WindowEmployee windowEmployee = new WindowEmployee();
    private static WindowRole windowRole = new WindowRole();
    public static void main(String[] args) {
        new Main();
    }
    public Main() {
//        System.out.println(personViewModel);
        frame.setVisible(true);
        frame.setTitle("Main Window");
//        frame.setLayout();
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Установка JMenuBar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        JMenuItem employeesItem = new JMenuItem("Сотрудники");
        JMenuItem rolesItem = new JMenuItem("Должности");
        JMenuItem closeItem = new JMenuItem("Выход");

        // Добавление обработчиков
        employeesItem.addActionListener(e -> {
            personViewModel.setRoleViewModel(roleViewModel);
            windowEmployee.setPersonViewModel(personViewModel);
            windowRole.setRoleViewModel(roleViewModel);

            windowEmployee.refreshTable();
            windowEmployee.setVisible(true);
        });

        rolesItem.addActionListener(e -> {
            personViewModel.setRoleViewModel(roleViewModel);
            windowEmployee.setPersonViewModel(personViewModel);
            windowRole.setRoleViewModel(roleViewModel);

//            System.out.println(roleViewModel);

            windowRole.refreshTable();
            windowRole.setVisible(true);
        });

        closeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuBar.add(employeesItem);
        menuBar.add(rolesItem);
        menuBar.add(closeItem);

        frame.setJMenuBar(menuBar);
    }
}
