package com.Filin.View;

import com.Filin.Model.Role;
import com.Filin.ViewModel.RoleViewModel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class WindowRole extends Window {

    private JPanel panel = new JPanel(); // Убрали static
    private ControlPanel controlPanel = new ControlPanel();
    @Getter
    @Setter
    private RoleViewModel roleViewModel = new RoleViewModel(); // Убрали static
    private WindowNewRole addRoleWindow = new WindowNewRole(this);
    private JTable roleTable = roleViewModel.getTable();

    {
        frame.setTitle("Должности");
        panel.setLayout(new GridBagLayout());

        controlPanel.getAdd().addActionListener(e -> {
            addRoleWindow.setVisible(true);
            addRoleWindow.clear();
            addRoleWindow.draw();
        });

        controlPanel.getRemove().addActionListener(e -> {
            int selectedRow = roleTable.getSelectedRow();
            if (selectedRow != -1) {
                int roleId = Integer.parseInt((String) roleTable.getValueAt(selectedRow, 0));
                roleViewModel.getRoles().removeIf(role -> role.getId() == roleId);

                // Перерисовка таблицы
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(frame, "Выберите строку для удаления!", "Ошибка", JOptionPane.WARNING_MESSAGE);
            }
        });

        controlPanel.getUpdate().addActionListener(e -> {
            int selectedRow = roleTable.getSelectedRow();

            if (selectedRow != -1) {
                int roleId = Integer.parseInt((String) roleTable.getValueAt(selectedRow, 0));
                Role roleToEdit = roleViewModel.getRoles().stream()
                        .filter(role -> role.getId() == roleId)
                        .findFirst()
                        .orElse(null);

                if (roleToEdit != null) {
                    WindowEditRole editWindow = new WindowEditRole(this);
                    editWindow.setRoleToEdit(roleToEdit);
                    editWindow.draw();
                    editWindow.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Выберите строку для редактирования!", "Ошибка", JOptionPane.WARNING_MESSAGE);
            }
        });


    }

    public void draw() {
        GridBagConstraints labelConstraints = getConstraints(0, 1, 1, 1);
        labelConstraints.anchor = GridBagConstraints.NORTH;
        labelConstraints.weightx = 0; // Без растяжения
        labelConstraints.weighty = 0;
        labelConstraints.fill = GridBagConstraints.NONE;

        panel.add(controlPanel);
        panel.add(new JLabel("Список должностей сотрудников"), labelConstraints);
        panel.add(new JScrollPane(roleTable), getConstraints(0, 2, 1, 1));
        frame.add(panel);
    }

    // Метод для обновления таблицы ролей
    public void refreshTable() {
        panel.removeAll(); // Очищаем панель
        roleTable = roleViewModel.getTable();
        draw();            // Перерисовываем содержимое
        panel.revalidate(); // Обновляем интерфейс
        panel.repaint();
    }

//    public RoleViewModel getRoleViewModel() {
//        return roleViewModel; // Геттер для модели
//    }
}
