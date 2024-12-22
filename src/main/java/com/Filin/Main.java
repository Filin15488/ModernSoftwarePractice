package com.Filin;

import com.Filin.View.Window;
import com.Filin.View.WindowEmployee;
import com.Filin.View.WindowRole;

import javax.swing.*;
import java.awt.*;

public class Main extends Window {
    private static WindowEmployee windowEmployee = new WindowEmployee();
    private static WindowRole windowRole = new WindowRole();
    public static void main(String[] args) {
        new Main();
    }
    public Main() {
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

        // Добавление обработчиков
        employeesItem.addActionListener(e -> {
            windowEmployee.refreshTable();
            windowEmployee.setVisible(true);
        });

        rolesItem.addActionListener(e -> {
            windowRole.refreshTable();
            windowRole.setVisible(true);
        });

        menuBar.add(employeesItem);
        menuBar.add(rolesItem);

        frame.setJMenuBar(menuBar);
    }
}
