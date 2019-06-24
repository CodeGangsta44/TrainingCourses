package com.company.view;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class that has methods for displaying
 * various information on the screen (localized).
 * Main class of project view.
 *
 * @author Roman Dovhopoliuk
 * @version 1.0.0
 */

public class View {
    /** Attribute resource bundle for
     * receiving localized messages by a given key
     * @see ResourceBundle
     */
    private ResourceBundle resourceBundle;

    /** Attribute name of resource bundle */
    private static final String RESOURCE_NAME = "message";


    /**
     * Constructor for creating new object
     */
    public View() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, Locale.getDefault());
    }


    /**
     * Constructor for creating new object
     * @param locale - locale of current user
     */
    public View(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }

    /**
     * Method for changing resource by giving new locale
     * @param locale - new locale for resource
     */
    public void changeResource(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }

    /**
     * Method for getting message by given key
     * @param key - key of message in resource bundle
     * @return localized message from resource bundle
     */
    public String getString(String key) {
        return resourceBundle.getString(key);
    }

    /**
     * Method for printing string on screen
     * @param str - string to print
     */
    public void printString(String str) {
        System.out.print(str);
    }

    /**
     * Method for printing line on screen (with carriage return)
     * @param line - line to print
     */
    public void printLine(String line) {
        System.out.println(line);
    }

    /**
     * Method for printing collection on screen
     * @param list - list to print
     * @see List
     */
    public void printCollection(List<?> list){
        for (Object i : list) {
            printString(i.toString() + '\n');
        }
    }
}
