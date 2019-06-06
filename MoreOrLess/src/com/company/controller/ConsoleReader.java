package com.company.controller;

import java.util.Scanner;

class ConsoleReader {
    private Scanner scanner;

    ConsoleReader(){
        this.scanner = new Scanner(System.in);
    }

    int readInt() {
        return this.scanner.nextInt();
    }

    void resetReader() {
        this.scanner.nextLine();
    }
}