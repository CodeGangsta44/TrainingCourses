package com.company.controller;

import java.util.Scanner;

class ConsoleReader {
    private Scanner scanner;

    ConsoleReader(){
        scanner = new Scanner(System.in);
    }

    int readInt() {
        return scanner.nextInt();
    }

    String readString() {
        return scanner.nextLine();
    }

    boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    void resetReader() {
        scanner.nextLine();
    }
}