package com.Filin.View;

import com.Filin.ViewModel.RoleViewModel;

import javax.swing.*;
import java.awt.*;

public class WindowRole extends Window{
    static JPanel panel = new JPanel();
    {
//        temp
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Должности");
        panel.setLayout(new GridBagLayout());
    }


    public void draw() {
//        labelConstraints
        GridBagConstraints labelConstraints = getConstraints(0,0,1,1);
        labelConstraints.anchor = GridBagConstraints.NORTH;
        labelConstraints.weightx = 0; // Без растяжения
        labelConstraints.weighty = 0;
        labelConstraints.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Список должностей сотрудников"), labelConstraints);

        RoleViewModel roleViewModel = new RoleViewModel();

        panel.add(new JScrollPane(roleViewModel.getTable()), getConstraints(0,1,1,1));
        frame.add(panel);
    }
}
