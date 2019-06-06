package com.company.view;

public class View {
    public final String INPUT_FIRST_WORD = "Please, enter the first word: ";
    public final String INPUT_SECOND_WORD = "Please, enter the second word: ";
    public final String INCORRECT_DATA = "Sorry, you entered wrong word. Try again.\n";
    public final String CORRECT_DATA = "Your word is correct.\n";

    public void printMessage(String message){
        System.out.print(message);
    }
}
