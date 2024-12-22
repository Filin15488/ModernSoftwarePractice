package com.Filin.View;

import com.Filin.Model.Role;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class WindowEditRole extends Window {
    private JPanel contentPane = new JPanel();
    private JTextArea jobTitle = new JTextArea(1, 15);
    private JPanel buttonPanel = new JPanel();
    private JButton saveButton = new JButton("Сохранить");
    private JButton cancelButton = new JButton("Отменить");

    private Role roleToEdit; // Роль, которую редактируем
    private WindowRole parentWindow; // Ссылка на родительское окно

    public WindowEditRole(WindowRole parentWindow) {
        this.parentWindow = parentWindow;

        int width = 300;
        int height = 100;
        frame.setBounds((getScreenSize().width - width) / 2, (getScreenSize().height - height) / 2, width, height);
        frame.setTitle("Редактирование роли");

        saveButton.addActionListener(e -> {
            roleToEdit.setNameRole(jobTitle.getText());

            //            записываем добавленную роль в json файл

            try {
                parentWindow.getRoleViewModel().saveRolesToFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            parentWindow.refreshTable(); // Обновление таблицы в главном окне
            frame.dispose();             // Закрытие окна
        });
        cancelButton.addActionListener(e -> {
            frame.dispose();
        });
    }

    public void setRoleToEdit(Role role) {
        this.roleToEdit = role;
        jobTitle.setText(role.getNameRole());
    }

    public void draw() {
        contentPane.removeAll();
        buttonPanel.removeAll();

        contentPane.add(new JLabel("Название роли"), getConstraints(0, 1, 1, 1));
        contentPane.add(jobTitle, getConstraints(1, 1, 1, 1));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        frame.add(contentPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }
}
