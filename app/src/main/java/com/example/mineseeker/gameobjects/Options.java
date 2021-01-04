package com.example.mineseeker.gameobjects;
//CLASS DEFINING THE OPTIONS REGARDING THE GAME

public class Options {
    private int row;
    private int column;
    private int numberOfMines;
    private static Options instance;
    private int numberOfScans;

    private Options() {
        this.row = 4;
        this.column = 6;
        this.numberOfMines = 5;
        this.numberOfScans = 0;
    }

    public static Options getInstance() {
        if (instance == null) {
            instance = new Options();
        }
        return instance;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getNumberOfScans() {
        return numberOfScans;
    }

    public void setNumberOfScans(int numberOfScans) {
        this.numberOfScans = numberOfScans;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    public void setNumberOfMines(int numberOfMines) {
        this.numberOfMines = numberOfMines;
    }
}
