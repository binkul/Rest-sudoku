package com.example.rest.sudoku.exception;

public class SudokuUnsolvableException extends RuntimeException {
    public SudokuUnsolvableException(String message) {
        super(message);
    }
}
