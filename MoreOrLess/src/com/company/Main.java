package com.company;

import com.company.model.*;
import com.company.view.*;
import com.company.controller.*;

public class Main {

    public static void main(String[] args) {
        Model model = new Model(0, 100);
        View view = new View();

        Controller controller = new Controller(model, view);

        controller.userProcess();
	// write your code here
    }
}
