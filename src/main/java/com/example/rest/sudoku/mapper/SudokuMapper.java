package com.example.rest.sudoku.mapper;

import com.example.rest.sudoku.entity.SudokuField;
import com.example.rest.sudoku.entity.SudokuValues;
import com.example.rest.sudoku.entity.dto.*;
import com.example.rest.sudoku.sudoku.Data;
import com.example.rest.sudoku.sudoku.Element;
import com.example.rest.sudoku.sudoku.Sudoku;
import com.example.rest.sudoku.sudoku.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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

    public SudokuFieldDto mapToSudokuDto(SudokuField sudokuField) {
        Sudoku sudoku = new Sudoku();

        for (SudokuValues value : sudokuField.getSudokuValues()) {
            sudoku.getElement(value.getRow(), value.getColumn()).setNumber(value.getNumber());
        }

        return new SudokuFieldDto(
                sudokuField.getId(),
                sudokuField.getDate(),
                sudokuField.isCorrect(),
                getRows(sudoku));
    }

    public List<SudokuFieldDto> mapToSudokuDtoList(List<SudokuField> fields) {
        return fields.stream()
                .map(this::mapToSudokuDto)
                .collect(Collectors.toList());
    }

    private List<String> getRows(Sudoku sudoku) {
        List<String> rows = new ArrayList<>();

        for (int i = 1; i <= Data.SIZE; i++) {
            String row = sudoku.getRow(i)
                    .values()
                    .stream()
                    .map(Element::getNumber)
                    .map(e -> Integer.toString(e))
                    .collect(Collectors.joining("|"));
            rows.add(row);
        }

        return rows;
    }

    public SudokuField mapToSudokuField(Sudoku sudoku) {
        SudokuField sudokuField = new SudokuField();

        List<SudokuValues> values = sudoku.getField()
                .entrySet()
                .stream()
                .filter(i -> i.getValue().getNumber() != Data.EMPTY)
                .map(i -> new SudokuValues(i.getKey().getRow(), i.getKey().getColumn(), i.getValue().getNumber(), sudokuField))
                .collect(Collectors.toList());
        sudokuField.setSudokuValues(values);
        return sudokuField;
    }
}
