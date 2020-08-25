package com.example.rest.sudoku.sudoku;

public enum FontColor {
    BLACK(0x000000),
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF);

    private int color;

    FontColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
