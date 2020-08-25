package com.example.rest.sudoku.mapper;

import com.example.rest.sudoku.entity.dto.*;
import com.example.rest.sudoku.sudoku.Data;
import com.example.rest.sudoku.sudoku.Element;
import com.example.rest.sudoku.sudoku.Sudoku;
import com.example.rest.sudoku.sudoku.Validator;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SudokuMapper {

    public Sudoku mapToSudoku(AbsoluteSudokuDto sudokuDto) {
        Sudoku sudoku = new Sudoku();

        for (AbsolutePosition value : sudokuDto.getPositions()) {
            int row = value.getRow();
            int column = value.getColumn();
            Validator.validateRow(row);
            Validator.validateColumn(column);
            Validator.validateNumber(value.getNumber());

            sudoku.getElement(row, column).setNumber(value.getNumber());
        }
        return sudoku;
    }

    public Sudoku mapToSudoku(RelativeSudokuDto sudokuDto) {
        Sudoku sudoku = new Sudoku();

        for (RelativePosition value : sudokuDto.getPositions()) {
            Validator.validateMaxPosition(value.getPosition());
            Validator.validateNumber(value.getNumber());
            int row = (value.getPosition() - 1) / Data.SIZE + 1;
            int column = (value.getPosition() - 1) % Data.SIZE + 1;

            sudoku.getElement(row, column).setNumber(value.getNumber());
        }

        return sudoku;
    }

    public SudokuDto mapToSudokuDto(Sudoku sudoku) {
        SudokuDto sudokuDto = new SudokuDto();

        for (int i = 1; i <= Data.SIZE; i++) {
            String row = sudoku.getRow(i)
                    .values()
                    .stream()
                    .map(Element::getNumber)
                    .map(e -> Integer.toString(e))
                    .collect(Collectors.joining("|"));
            sudokuDto.add(row);
        }

        return sudokuDto;
    }
}
