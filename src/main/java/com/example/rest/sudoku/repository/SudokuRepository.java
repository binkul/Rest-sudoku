package com.example.rest.sudoku.repository;

import com.example.rest.sudoku.entity.SudokuField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SudokuRepository extends JpaRepository<SudokuField, Long> {

    List<SudokuField> findAllByCorrect(boolean correct);
}
