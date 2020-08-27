package com.example.rest.sudoku.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "sudoku_values")
public class SudokuValues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "s_row", nullable = false)
    private int row = 1;

    @Column(name = "s_column", nullable = false)
    private int column = 1;

    @Column(name = "s_number", nullable = false)
    private int number = 1;

    @ManyToOne
    @JoinColumn(nullable = false)
    private SudokuField sudokuField;

    public SudokuValues() {}

    public SudokuValues(int row, int column, int number, SudokuField sudokuField) {
        this.row = row;
        this.column = column;
        this.number = number;
        this.sudokuField = sudokuField;
    }

    public Long getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public SudokuField getSudokuField() {
        return sudokuField;
    }

    public int getNumber() {
        return number;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSudokuField(SudokuField sudokuField) {
        this.sudokuField = sudokuField;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof SudokuValues)) return false;
        SudokuValues that = (SudokuValues) o;
        return row == that.row &&
                column == that.column &&
                number == that.number &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, row, column, number);
    }

    @Override
    public String toString() {
        return "SudokuValues{" +
                "id=" + id +
                ", row=" + row +
                ", column=" + column +
                ", number=" + number +
                ", sudokuField=" + sudokuField +
                '}';
    }
}
