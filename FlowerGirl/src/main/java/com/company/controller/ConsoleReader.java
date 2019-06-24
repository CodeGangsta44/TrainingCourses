package com.company.controller;

import java.util.Scanner;

/**
 * Class that has methods for receiving,
 * verifying user data.
 * Some of their methods delegate to Scanner
 *
 * @see Scanner
 *
 * @author Roman Dovhopoliuk
 * @version 1.0.0
 */
class ConsoleReader {

    /** Attribute scanner for reading data from console */
    private Scanner scanner;

    /**
     * Constructor for creating new object
     */
    ConsoleReader(){
        scanner = new Scanner(System.in);
    }

    /**
     * Method for reading an integer value from console input.
     * @return integer value, entered by user
     */
    int readInt() {
        return scanner.nextInt();
    }

    /**
     * Method for reading a string from console input.
     * @return string, entered by user
     */
    String readString() {
        return scanner.nextLine();
    }

    /**
     * Method for checking the presence of string for reading.
     * @return boolean result
     */
    boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * Method for resetting the reader after a failed reading.
     */
    void resetReader() {
        scanner.nextLine();
    }
}