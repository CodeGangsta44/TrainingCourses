package com.company.controller;

import com.company.model.*;
import com.company.view.*;
import java.util.InputMismatchException;


public class Controller {
    private Model model;
    private View view;
    private ConsoleReader reader;

    public Controller (Model model, View view) {
        this.model = model;
        this.view = view;
        this.reader = new ConsoleReader();
    }

    public void userProcess() {

        this.model.startNewGame();

        int answer;
        int currentLowerBound;
        int currentUpperBound;

        boolean finished = false;

        while (!finished) {
            currentLowerBound = this.model.getCurrentLowerBound();
            currentUpperBound = this.model.getCurrentUpperBound();

            this.view.clearScreen();
            this.view.printInterval(currentLowerBound, currentUpperBound);
            this.view.printMessage(this.view.INPUT_NUMBER);

            try {
                answer = this.reader.readInt();

                if (answer < currentLowerBound || answer > currentUpperBound) {
                    this.view.printMessage(this.view.INCORRECT_INPUT + this.view.OUT_OF_BOUNDS);
                    continue;
                }

                finished = this.checkUserAnswer(answer);

            } catch (InputMismatchException e) {
                this.view.printMessage(this.view.INCORRECT_INPUT + this.view.UNRECOGNIZED_SYMBOL);
                this.reader.resetReader();
            }


        }

    }

    private boolean checkUserAnswer(int answer){
        int result = this.model.checkUserAnswer(answer);

        switch (result) {
            case 0:
                this.view.printMessage(this.view.CORRECT_ANSWER);
                this.view.printStats(this.model.getStats());
                return true;
            case -1:
                this.view.printMessage(this.view.SMALLER_ANSWER);
                return false;
            case 1:
                this.view.printMessage(this.view.BIGGER_ANSWER);
                return false;
            default:
                return false;
        }

    }

}
