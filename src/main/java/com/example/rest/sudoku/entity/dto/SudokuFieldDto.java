package com.example.rest.sudoku.entity.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class SudokuFieldDto {
    private Long id;
    private LocalDate date;
    private boolean correct;
    private List<String> rows;

    public SudokuFieldDto(Long id, LocalDate date, boolean correct, List<String> rows) {
        this.id = id;
        this.date = date;
        this.correct = correct;
        this.rows = rows;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isCorrect() {
        return correct;
    }

    public List<String> getRows() {
        return rows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof SudokuFieldDto)) return false;
        SudokuFieldDto that = (SudokuFieldDto) o;
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
        return "SudokuFieldDto{" +
                "id=" + id +
                ", date=" + date +
                ", correct=" + correct +
                ", rows=" + rows +
                '}';
    }
}
