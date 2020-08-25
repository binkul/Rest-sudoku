package com.example.rest.sudoku.entity.dto;

import java.util.Objects;

public class RelativePosition {

    private Integer position;
    private Integer number;

    public RelativePosition() {
    }

    public RelativePosition(Integer position, Integer number) {
        this.position = position;
        this.number = number;
    }

    public Integer getPosition() {
        return position;
    }

    public Integer getNumber() {
        return number;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof RelativePosition)) return false;
        RelativePosition that = (RelativePosition) o;
        return Objects.equals(position, that.position) &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, number);
    }

    @Override
    public String toString() {
        return "RelativePosition{" +
                "position=" + position +
                ", number=" + number +
                '}';
    }
}
