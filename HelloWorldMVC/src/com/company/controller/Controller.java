package com.company.controller;


import com.company.model.*;
import com.company.view.*;


public class Controller {
    private Model model;
    private View view;
    private ConsoleReader reader;

    private final String FIRST_WORD_PATTERN = "Hello";
    private final String SECOND_WORD_PATTERN = "world!";

    public Controller (Model model, View view) {
        this.model = model;
        this.view = view;
        this.reader = new ConsoleReader();
    }

    public void userProcess() {

        String firstAnswer = this.getWord(FIRST_WORD_PATTERN, this.view.INPUT_FIRST_WORD);
        String secondAnswer = this.getWord(SECOND_WORD_PATTERN, this.view.INPUT_SECOND_WORD);
        String result = this.model.concatStrings(firstAnswer, secondAnswer);

        this.view.printMessage("Answer is: " + result);

    }

    private String getWord(String pattern, String inputMessage) {
        String answer;
        while (true) {
            this.view.printMessage(inputMessage);
            answer = this.reader.readString();

            if (answer.equals(pattern)) {
                this.view.printMessage(this.view.CORRECT_DATA);
                return answer;
            } else {
                this.view.printMessage(this.view.INCORRECT_DATA);
            }
        }
    }
}
