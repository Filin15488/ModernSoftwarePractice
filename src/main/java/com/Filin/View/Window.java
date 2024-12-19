package com.Filin.View;

import javax.swing.*;
import java.awt.*;

public class Window {
    public JFrame frame = getFrame();

    public Boolean getVisible() {
        return frame.isVisible();
    }
    public void setVisible(boolean b) {
        frame.setVisible(b);
    }

    static GridBagConstraints getConstraints(int gridx, int gridy, int gridwidth, int gridheight) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.1;
        constraints.weighty = 0.01;
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth =gridwidth;
        constraints.gridheight = gridheight;
        constraints.fill = GridBagConstraints.BOTH;
        return constraints;
    }

    public static JFrame getFrame() {
        int width = 800;
        int height = 600;
        JFrame frame = new JFrame();

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        frame.setBounds((screenSize.width - width) / 2, (screenSize.height - height) / 2, width, height);
//        frame.setVisible(true);
        return frame;

    }

}