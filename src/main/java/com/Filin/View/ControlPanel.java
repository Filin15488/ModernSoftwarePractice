package com.Filin.View;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

import static com.Filin.View.Window.getConstraints;

@Data
public class ControlPanel extends JPanel {
    private JButton add = new JButton("Добавить");
    private JButton remove = new JButton("Удалить");
    private JButton update = new JButton("Редактировать");
    GridBagConstraints controlConstraints = getConstraints(0,0,1,1);
    {
        controlConstraints.anchor = GridBagConstraints.NORTHWEST;
        controlConstraints.weightx = 0; // Без растяжения
        controlConstraints.weighty = 0;
        controlConstraints.fill = GridBagConstraints.NONE;



        this.add(add);
        this.add(update);
        this.add(remove);

    }
}
