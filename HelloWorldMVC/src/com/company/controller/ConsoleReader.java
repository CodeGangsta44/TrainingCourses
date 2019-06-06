package com.company.controller;

import java.util.Scanner;

class ConsoleReader {
    private Scanner scanner;

    ConsoleReader(){
        this.scanner = new Scanner(System.in);
    }

    String readString() {
        return this.scanner.next();
    }
}
