package com.company;

import com.company.controller.Controller;
import com.company.view.*;
import com.company.model.*;

public class Main {

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();

        Controller controller = new Controller(model, view);

        controller.execute();
    }
}
