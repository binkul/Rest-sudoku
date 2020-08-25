package com.example.rest.sudoku.entity.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SudokuDto {
    private List<String> rows = new ArrayList<>();

    public List<String> getRows() {
        return rows;
    }

    public void setRows(List<String> rows) {
        this.rows = rows;
    }

    public void add(String row) {
        rows.add(row);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof SudokuDto)) return false;
        SudokuDto sudokuDto = (SudokuDto) o;
        return Objects.equals(rows, sudokuDto.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows);
    }

    @Override
    public String toString() {
        return String.join("\n", rows);
    }
}
