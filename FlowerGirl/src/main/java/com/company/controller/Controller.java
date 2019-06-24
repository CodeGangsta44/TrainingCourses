package com.company.controller;

import java.util.List;
import java.util.Locale;
import com.company.model.*;
import com.company.model.entities.Flower;
import com.company.view.*;
import java.util.InputMismatchException;
import static com.company.view.MessageConstants.*;


/**
 * Class that has methods for receiving,
 * verifying user data and
 * transferring their to model.
 *
 * @author Roman Dovhopoliuk
 * @version 1.0.0
 */

public class Controller {

    /**
     * Attribute locale of current user
     * @see Locale
     */
    private Locale locale;

    /**
     * Attribute bouquet for current
     * executable process (model)
     * @see Bouquet
     */
    private Bouquet model;

    /**
     * Attribute view for current
     * executable process (View)
     * @see View
     */
    private View view;

    /**
     * Attribute reader for reading
     * information from console
     * @see ConsoleReader
     */
    private ConsoleReader reader;

    /**
     * Constructor for creating new object
     * @param model - bouquet for current executable process
     * @param view - view for current executable process
     */
    public Controller(Bouquet model, View view) {
        this.model = model;
        this.view = view;

        reader = new ConsoleReader();
        locale = new Locale("en");
        view.changeResource(locale);
    }

    /**
     * Method for starting user session.
     * Displays a menu and tracks the user's choice.
     */
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
                    view.printCollection(this.getFlowersByStemLength());

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

    /**
     * Method for changing bouquet type.
     * Displays a bouquet-menu and tracks the user's choice.
     */
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

    /**
     * Method for changing language.
     * Displays a language-menu and tracks the user's choice.
     */
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

    /**
     * Method for getting list of flowers by stem length.
     * Receives from user lower and upper bounds.
     * @return - list of flowers filtered by stem length.
     */
    private List<Flower> getFlowersByStemLength() {

        boolean boundsInitialized = false;
        int lowerBound = 0;
        int upperBound = 0;

        while (!boundsInitialized) {
            try {
                view.printString(view.getString(INPUT_LOWER_BOUND));
                lowerBound =  reader.readInt();

                view.printString(view.getString(INPUT_UPPER_BOUND));
                upperBound = reader.readInt();

            } catch(InputMismatchException e){
                view.printLine(view.getString(INPUT_MISMATCH));
                reader.resetReader();
                continue;
            }

            if (lowerBound < upperBound) {
                boundsInitialized = true;

            } else {
                view.printLine(view.getString(INPUT_BOUNDS_MISMATCH));
            }
        }


        return model.getFlowersByStemLength(lowerBound, upperBound);
    }
}
