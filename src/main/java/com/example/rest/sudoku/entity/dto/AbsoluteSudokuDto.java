package com.example.rest.sudoku.entity.dto;

import java.util.ArrayList;
import java.util.List;

public class AbsoluteSudokuDto {
    private List<AbsolutePosition> positions = new ArrayList<>();

    public AbsoluteSudokuDto() {}

    public AbsoluteSudokuDto(List<AbsolutePosition> positions) {
        this.positions = positions;
    }

    public List<AbsolutePosition> getPositions() {
        return positions;
    }

    public void setPositions(List<AbsolutePosition> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "AbsoluteSudokuDto{" +
                "positions=" + positions +
                '}';
    }
}
