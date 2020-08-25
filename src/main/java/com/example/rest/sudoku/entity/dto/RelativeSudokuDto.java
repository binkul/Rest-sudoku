package com.example.rest.sudoku.entity.dto;

import java.util.ArrayList;
import java.util.List;

public class RelativeSudokuDto {
    private List<RelativePosition> positions = new ArrayList<>();

    public RelativeSudokuDto() {}

    public RelativeSudokuDto(List<RelativePosition> positions) {
        this.positions = positions;
    }

    public List<RelativePosition> getPositions() {
        return positions;
    }

    public void setPositions(List<RelativePosition> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "RelativeSudokuDto{" +
                "position=" + positions +
                '}';
    }
}
