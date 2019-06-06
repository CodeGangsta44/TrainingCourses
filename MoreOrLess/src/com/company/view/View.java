package com.company.view;

import java.util.ArrayList;

public class View {
    public final String INPUT_NUMBER = "Please, enter the number: ";

    public final String SMALLER_ANSWER = "Your answer is smaller than target number.\n";
    public final String BIGGER_ANSWER = "Your answer is bigger than target number.\n";
    public final String CORRECT_ANSWER = "Сongratulations! Your answer is equal to the target number.\n" +
                                         "The stats of your game: \n";

    public final String INCORRECT_INPUT = "Your input is incorrect.\n";
    public final String UNRECOGNIZED_SYMBOL = "Data, entered by you, is not recognized.\n" +
                                              "Make sure to enter an integer.\n";
    public final String OUT_OF_BOUNDS = "The number, entered by you, is outside the permissible range.\n";

    private final String INTERVAL_DESCRIPTION = "\nSelect an INTEGER from the interval: ";

    public void printMessage(String message){
        System.out.print(message);
    }

    public void printInterval(int min, int max) {
        System.out.print(this.INTERVAL_DESCRIPTION + "[" + min + ", " + max + "]\n");
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void printStats(ArrayList<String> stats) {
        for (String i : stats) {
            System.out.println(i);
        }
    }
}
