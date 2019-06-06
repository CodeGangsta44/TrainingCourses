package com.company.model;

import java.util.ArrayList;
import java.util.Random;

public class Model {
    private int min;
    private int max;

    private Random random;

    private int targetNumber;

    private int currentLowerBound;
    private int currentUpperBound;
    private int attemptCounter;

    private ArrayList<String> stats;

    public Model(int min, int max){
        this.min = min;
        this.max = max;

        this.random = new Random();
    }

    public void startNewGame() {
        this.targetNumber = this.random.nextInt((this.max - this.min + 1)) + this.min;

        this.currentLowerBound = this.min;
        this.currentUpperBound = this.max;
        this.attemptCounter = 0;

        this.stats = new ArrayList<>();
    }

    public int checkUserAnswer(int answer) {
        this.attemptCounter++;
        int result;

        String initialInterval = "[" + this.currentLowerBound + ", " + this.currentUpperBound + "]";

        if (answer == this.targetNumber) {
            this.finishGame(initialInterval, answer);
            return 0;

        } else if (answer < this.targetNumber) {
            this.currentLowerBound = answer + 1;
            this.makeRecord(initialInterval, answer);
            return -1;

        } else {
            this.currentUpperBound = answer - 1;
            this.makeRecord(initialInterval, answer);
            return 1;
        }
    }

    private void makeRecord(String initialInterval, int answer) {
        String endingInterval = "[" + this.currentLowerBound + ", " + this.currentUpperBound + "]";

        this.stats.add("Step " + this.attemptCounter  + ": " +
                       initialInterval + " => " + answer + " => " + endingInterval);
    }

    private void finishGame(String initialInterval, int answer) {
        this.stats.add("Step " + this.attemptCounter  + ": " +
                initialInterval + " => " + answer + " => " + "Victory!");

        this.stats.add("Total number of attempts: " + this.attemptCounter);
    }

    public int getCurrentLowerBound() {
        return this.currentLowerBound;
    }

    public int getCurrentUpperBound() {
        return this.currentUpperBound;
    }

    public int getAttemptCounter() {
        return this.attemptCounter;
    }

    public ArrayList<String> getStats() {
        return this.stats;
    }

}
