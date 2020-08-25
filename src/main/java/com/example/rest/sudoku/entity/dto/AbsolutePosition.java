package com.example.rest.sudoku.entity.dto;

import java.util.Objects;

public class AbsolutePosition {

    private Integer row;
    private Integer column;
    private Integer number;

    public AbsolutePosition() {
    }

    public AbsolutePosition(Integer row, Integer column, Integer number) {
        this.row = row;
        this.column = column;
        this.number = number;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getNumber() {
        return number;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof AbsolutePosition)) return false;
        AbsolutePosition that = (AbsolutePosition) o;
        return Objects.equals(row, that.row) &&
                Objects.equals(column, that.column) &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, number);
    }

    @Override
    public String toString() {
        return "AbsolutePosition{" +
                "row=" + row +
                ", column=" + column +
                ", number=" + number +
                '}';
    }
}
