package com.company;

import com.company.model.*;
import com.company.view.*;
import com.company.controller.*;

public class Main {

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();

        Controller controller = new Controller(model, view);

        controller.userProcess();
    }
}
