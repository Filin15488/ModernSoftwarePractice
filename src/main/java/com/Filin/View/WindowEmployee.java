package com.Filin.View;


import com.Filin.Model.Person;
import com.Filin.ViewModel.PersonViewModel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import javax.swing.JScrollPane;


public class WindowEmployee extends Window {
    static JPanel panel = new JPanel();
    private ControlPanel controlPanel = new ControlPanel();
    @Getter
    @Setter
    private PersonViewModel personViewModel = PersonViewModel.getInstance();
//    private PersonViewModel personViewModel = new PersonViewModel();
    private JTable personTable = personViewModel.getTable();

    private WindowEditPerson editWindow = new WindowEditPerson(this);

    private WindowNewPerson addPersonWindow = new WindowNewPerson(this);

    {
        frame.setTitle("Сотрудники");
        panel.setLayout(new GridBagLayout());
//        System.out.println(personViewModel);
    }

//    Рабочий конвертатор в json:
//    ObjectMapper objectMapper = new ObjectMapper();
//    String json = null;
//            for (Person person : personViewModel.getPersons()){
//        try {
//            json = objectMapper.writeValueAsString(person);
//            System.out.println(json);
//        } catch (JsonProcessingException ex) {
//            throw new RuntimeException(ex);
//        }
//    }

    {
        controlPanel.getAdd().addActionListener(e -> {
            addPersonWindow.updateData();
            addPersonWindow.setVisible(true);
            addPersonWindow.draw();

        });
        controlPanel.getRemove().addActionListener(e -> {
            int selectedRow = personTable.getSelectedRow();

            if (selectedRow != -1) {
                int roleId = Integer.parseInt((String) personTable.getValueAt(selectedRow, 0));
//                System.out.println(personTable);
                personViewModel.getPersons().removeIf(role -> role.getId() == roleId);

                // Перерисовка таблицы
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(frame, "Выберите строку для удаления!", "Ошибка", JOptionPane.WARNING_MESSAGE);
            }

        });

        controlPanel.getUpdate().addActionListener(e -> {
           int selectedRow = personTable.getSelectedRow();

            if (selectedRow != -1) {
                int personId = Integer.parseInt((String) personTable.getValueAt(selectedRow, 0));
//               System.out.println(personId);
                Person personToEdit = personViewModel.getPersons().stream()
                        .filter(person -> person.getId() == personId).
                        findFirst()
                        .orElse(null);
//               System.out.println(personToEdit);

                if (personToEdit != null) {
//                    System.out.println(personToEdit);
                    editWindow.updateData();
                    editWindow.setPersonToEdit(personToEdit);
                    editWindow.draw();
                    editWindow.setVisible(true);

                }
            }
            else {
                JOptionPane.showMessageDialog(frame, "Выберите строку для редактирования!", "Ошибка", JOptionPane.WARNING_MESSAGE);
            }


        });
    }


    public void draw() {
//        labelConstraints
        GridBagConstraints labelConstraints = getConstraints(0,1,1,1);
        labelConstraints.anchor = GridBagConstraints.NORTH;
        labelConstraints.weightx = 0; // Без растяжения
        labelConstraints.weighty = 0;
        labelConstraints.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Сотрудники"), labelConstraints);

        panel.add(controlPanel);

        panel.add(new JScrollPane(personTable), getConstraints(0,2,1,1));
        frame.add(panel);
    }

    // Метод для обновления таблицы ролей
    public void refreshTable() {
        panel.removeAll(); // Очищаем панель
        personTable = personViewModel.getTable();
        draw();            // Перерисовываем содержимое
        panel.revalidate(); // Обновляем интерфейс
        panel.repaint();
    }

}
