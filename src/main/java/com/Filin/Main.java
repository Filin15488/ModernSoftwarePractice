package com.Filin;

import com.Filin.View.WindowEmployee;
import com.Filin.View.WindowRole;

public class Main {
    private static WindowEmployee windowEmployee = new WindowEmployee();
    private static WindowRole windowRole = new WindowRole();
    public static void main(String[] args) {
        windowEmployee.draw();
        windowEmployee.setVisible(true);

        windowRole.draw();
        windowRole.setVisible(true);


    }
}
