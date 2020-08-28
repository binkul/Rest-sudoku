package com.example.rest.sudoku.mapper;

import com.example.rest.sudoku.entity.SudokuField;
import com.example.rest.sudoku.entity.SudokuValues;
import com.example.rest.sudoku.sudoku.Sudoku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MapperTestSuite {

    @Autowired
    SudokuMapper mapper;

    @BeforeEach
    public void printStartText() {
        System.out.println("TestStart ...");
    }

    @Test
    public void testMapToSudokuField() {
        // Given
        Sudoku sudoku = new Sudoku();
        sudoku.getElement(1,1).setNumber(1);
        sudoku.getElement(1,9).setNumber(2);
        sudoku.getElement(9,1).setNumber(3);
        sudoku.getElement(9,9).setNumber(4);
        sudoku.getElement(5,5).setNumber(5);

        // When
        SudokuField sudokuField = mapper.mapToSudokuField(sudoku, 1L);

        // Then
        assertEquals(5, sudokuField.getSudokuValues().size());
        assertTrue(sudokuField.isCorrect());
        assertEquals(sudokuField.getDate(), LocalDate.now());
        assertTrue(sudokuField.getSudokuValues().contains(new SudokuValues(1,1,1, sudokuField)));
        assertTrue(sudokuField.getSudokuValues().contains(new SudokuValues(1,9,2, sudokuField)));
        assertTrue(sudokuField.getSudokuValues().contains(new SudokuValues(9,1,3, sudokuField)));
        assertTrue(sudokuField.getSudokuValues().contains(new SudokuValues(9,9,4, sudokuField)));
        assertTrue(sudokuField.getSudokuValues().contains(new SudokuValues(5,5,5, sudokuField)));
        assertFalse(sudokuField.getSudokuValues().contains(new SudokuValues(5,5,1, sudokuField)));
    }
}
