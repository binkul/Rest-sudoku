package com.example.rest.sudoku.exception;

public class SudokuCollisionNumberException extends RuntimeException {
    public SudokuCollisionNumberException(String message) {
        super(message);
    }
}
