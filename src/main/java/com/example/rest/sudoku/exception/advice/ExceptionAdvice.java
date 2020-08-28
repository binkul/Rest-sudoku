package com.example.rest.sudoku.exception.advice;

import com.example.rest.sudoku.exception.EntityNotFoundException;
import com.example.rest.sudoku.exception.SudokuCollisionNumberException;
import com.example.rest.sudoku.exception.SudokuUnsolvableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public SudokuException collisionNumberException(IllegalArgumentException ex) {
        return new SudokuException(LocalDateTime.now(),
                HttpStatus.NOT_ACCEPTABLE.value(),
                HttpStatus.NOT_ACCEPTABLE.toString(),
                ex.getMessage());
    }

    @ExceptionHandler(SudokuUnsolvableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public SudokuException sudokuUnsolvableException(SudokuUnsolvableException ex) {
        return new SudokuException(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.toString(),
                ex.getMessage());
    }

    @ExceptionHandler(SudokuCollisionNumberException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public SudokuException sudokuCollisionException(SudokuCollisionNumberException ex) {
        return new SudokuException(LocalDateTime.now(),
                HttpStatus.NOT_ACCEPTABLE.value(),
                HttpStatus.NOT_ACCEPTABLE.toString(),
                ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public SudokuException sudokuCollisionException(EntityNotFoundException ex) {
        return new SudokuException(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.toString(),
                ex.getMessage());
    }
}
