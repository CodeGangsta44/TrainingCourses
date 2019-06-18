package com.company.controller;

import java.util.ResourceBundle;
import java.util.Locale;
import java.util.regex.Pattern;
import com.company.model.*;
import com.company.view.*;
import java.util.InputMismatchException;
import static com.company.view.MessageConstants.*;
import static com.company.controller.PatternConstants.*;

public class Controller {

    private Locale locale;

    private Bouquet model;
    private View view;
    private ConsoleReader reader;

    public Controller(Bouquet model, View view) {
        this.model = model;
        this.view = view;

        reader = new ConsoleReader();
        locale = new Locale("en");
        view.changeResource(locale);
    }

    public void execute(){
        view.printLine(view.getString(GREETING));

        boolean userLeft = false;

        while (!userLeft) {
            view.printLine(view.getString(MENU));

            int answer;

            try {
                answer = reader.readInt();
            } catch(InputMismatchException e){
                view.printLine(view.getString(INPUT_MISMATCH));
                reader.resetReader();
                continue;
            }

            switch (answer){
                case 1:
                    reader.resetReader();
                    changeBouquetType();
                    break;
                case 2:
                    changeLanguage();
                    break;
                case 3:
                    view.printLine(view.getString(PRICE_OF_BOUQUET));
                    view.printLine(Integer.toString(model.getPrice()));
                    break;
                case 4:
                    view.printCollection(model.getComponents());
                    break;
                case 5:
                    view.printCollection(model.getSortedByFreshnessPlants());
                    break;
                case 6:
                    view.printString(view.getString(INPUT_LOWER_BOUND));
                    int lowerBound = reader.readInt();
                    view.printString(view.getString(INPUT_UPPER_BOUND));
                    int upperBound = reader.readInt();

                    view.printCollection(model.getFlowersByStemLength(lowerBound, upperBound));

                    break;
                case 7:
                    userLeft = true;
                    break;

                default:
                    view.printLine(view.getString(INPUT_MISMATCH));

            }
        }

        view.printLine(view.getString(FAREWELL));

    }

    private void changeBouquetType() {
        view.printLine(view.getString(BOUQUET_MENU));

        boolean bouquetChosen = false;

        while (!bouquetChosen) {

            int answer;

            try {
                answer = reader.readInt();
            } catch(InputMismatchException e){
                view.printLine(view.getString(INPUT_MISMATCH));
                reader.resetReader();
                continue;
            }


            switch (answer) {

                case 1:
                    this.model = new Bouquet(BouquetType.EXPEENCIVE.getComponents());
                    break;

                case 2:
                    this.model = new Bouquet(BouquetType.NORMAL.getComponents());
                    break;

                case 3:
                    this.model = new Bouquet(BouquetType.CHEAP.getComponents());
                    break;

                default:
                    view.printLine(view.getString(INPUT_MISMATCH));
            }

            bouquetChosen = true;
        }

    }


    private void changeLanguage(){
        view.printLine(view.getString(LANGUAGE_MENU));

        switch (reader.readInt()){
            case 1:
                locale = new Locale("en");
                break;
            case 2:
                locale = new Locale("ua");
                break;
            case 3:
                locale = new Locale("ru");
                break;
        }

        view.changeResource(locale);

    }
}
