package com.Filin.View;

import com.Filin.Model.Role;
import com.Filin.ViewModel.RoleViewModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WindowNewRole extends Window {

    private JPanel contentPane = new JPanel();
    private JTextArea jobTitle = new JTextArea(1, 15);
    private JPanel buttonPanel = new JPanel();
    private JButton okButton = new JButton("Сохранить");
    private JButton cancelButton = new JButton("Отменить");

    private WindowRole parentWindow; // Ссылка на родительское окно

    // Конструктор принимает ссылку на родительское окно
    public WindowNewRole(WindowRole parentWindow) {
        this.parentWindow = parentWindow;

        int width = 300;
        int height = 100;
        frame.setBounds((getScreenSize().width - width) / 2, (getScreenSize().height - height) / 2, width, height);
        frame.setTitle("New Role");

        okButton.addActionListener(e -> {
            RoleViewModel roleViewModel = parentWindow.getRoleViewModel();
            ArrayList<Role> roles = roleViewModel.getRoles();

            // Добавляем новую роль с текстом из поля jobTitle
            roles.add(Role.builder()
                    .id(roles.size() + 1)
                    .NameRole(jobTitle.getText()) // Берем текст из поля
                    .build());
            roleViewModel.setCOUNT_ROLE(roles.size());

//            System.out.println(roleViewModel);


            parentWindow.refreshTable(); // Обновляем главное окно
            frame.dispose();             // Закрываем текущее окно
        });
        cancelButton.addActionListener(e -> {
            frame.dispose();
        });
    }

    public void draw() {
        contentPane.add(new JLabel("Должность"), getConstraints(0, 1, 1, 1));
        contentPane.add(jobTitle, getConstraints(1, 1, 1, 1));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        frame.add(contentPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

    }

    public void clear() {
        contentPane.removeAll(); // Очищаем панель
        contentPane.revalidate(); // Обновляем интерфейс
        contentPane.repaint();
        buttonPanel.removeAll(); // Очищаем панель
        buttonPanel.revalidate(); // Обновляем интерфейс
        buttonPanel.repaint();
    }
}
