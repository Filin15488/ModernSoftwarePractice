package com.Filin.View;


import com.Filin.ViewModel.PersonViewModel;

import javax.swing.*;
import java.awt.*;
import javax.swing.JScrollPane;


public class WindowEmployee extends Window {
    static JPanel panel = new JPanel();
    private ControlPanel controlPanel = new ControlPanel();

    {
        frame.setTitle("Сотрудники");
        panel.setLayout(new GridBagLayout());
    }

    {
        controlPanel.getAdd().addActionListener(e -> {
            System.out.println("EmployeeWindowControl");
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

        PersonViewModel personViewModel = new PersonViewModel();

        panel.add(controlPanel);

        panel.add(new JScrollPane(personViewModel.getTable()), getConstraints(0,2,1,1));
        frame.add(panel);
    }

}
