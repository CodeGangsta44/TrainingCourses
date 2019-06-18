package com.company;

import com.company.controller.Controller;
import com.company.model.Bouquet;
import com.company.view.View;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller(new Bouquet(), new View());

        controller.execute();
    }
}
