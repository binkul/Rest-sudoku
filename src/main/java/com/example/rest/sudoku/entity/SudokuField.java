package com.example.rest.sudoku.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "sudoku")
public class SudokuField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date = LocalDate.now();

    @Column(nullable = false)
    private boolean correct = true;

    @OneToMany(
            mappedBy = "sudokuField",
            cascade = CascadeType.ALL)
    private List<SudokuValues> sudokuValues = new ArrayList<>();

    public SudokuField() {}

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isCorrect() {
        return correct;
    }

    public List<SudokuValues> getSudokuValues() {
        return sudokuValues;
    }

    public void add(SudokuValues value) {
        sudokuValues.add(value);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public void setSudokuValues(List<SudokuValues> sudokuValues) {
        this.sudokuValues = sudokuValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof SudokuField)) return false;
        SudokuField that = (SudokuField) o;
        return correct == that.correct &&
                Objects.equals(id, that.id) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, correct);
    }

    @Override
    public String toString() {
        return "SudokuField{" +
                "id=" + id +
                ", date=" + date +
                ", correct=" + correct +
                ", sudokuValues=" + sudokuValues +
                '}';
    }
}
