package com.example.rest.sudoku.mapper;

import com.example.rest.sudoku.entity.SudokuField;
import com.example.rest.sudoku.entity.SudokuValues;
import com.example.rest.sudoku.entity.dto.*;
import com.example.rest.sudoku.sudoku.*;
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

    public Sudoku mapToSudoku(SudokuField sudokuField) {
        Sudoku sudoku = new Sudoku();

        for (SudokuValues value : sudokuField.getSudokuValues()) {
            int row = value.getRow();
            int column = value.getColumn();
            Validator.validateRow(row);
            Validator.validateColumn(column);
            Validator.validateNumber(value.getNumber());

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
                getRows(sudoku, 1));
    }

    public SudokuFieldDto mapToSudokuDto(Sudoku sudoku, Long id) {
        SudokuField sudokuField = mapToSudokuField(sudoku, id);
        return mapToSudokuDto(sudokuField);
    }

    public SudokuFieldDto mapToColorSudokuDto(Sudoku sudoku, SudokuField sudokuField) {
        return new SudokuFieldDto(
                sudokuField.getId(),
                sudokuField.getDate(),
                sudokuField.isCorrect(),
                getRows(sudoku, -1));
    }

    public List<SudokuFieldDto> mapToSudokuDtoList(List<SudokuField> fields) {
        return fields.stream()
                .map(this::mapToSudokuDto)
                .collect(Collectors.toList());
    }

    public SudokuField mapToSudokuField(Sudoku sudoku, Long id) {
        SudokuField sudokuField = new SudokuField();
        sudokuField.setId(id);

        List<SudokuValues> values = sudoku.getField()
                .entrySet()
                .stream()
                .filter(i -> i.getValue().getNumber() != Data.EMPTY)
                .map(i -> new SudokuValues(i.getKey().getRow(), i.getKey().getColumn(), i.getValue().getNumber(), sudokuField))
                .collect(Collectors.toList());
        sudokuField.setSudokuValues(values);
        return sudokuField;
    }

/*
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
*/

    private List<String> getRows(Sudoku sudoku, int neg) {
        List<String> rows = new ArrayList<>();

        for (int i = 1; i <= Data.SIZE; i++) {
            String row = sudoku.getRow(i)
                    .values()
                    .stream()
                    .map(e -> {
                        if (e.getFontColor() == FontColor.BLACK) return e.getNumber();
                        else return neg * e.getNumber();
                    })
                    .map(e -> Integer.toString(e))
                    .collect(Collectors.joining("|"));
            rows.add(row);
        }
        return rows;
    }
}
