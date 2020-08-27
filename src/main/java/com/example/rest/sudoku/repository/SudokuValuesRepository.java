package com.example.rest.sudoku.repository;

import com.example.rest.sudoku.entity.SudokuValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SudokuValuesRepository extends JpaRepository<SudokuValues, Long> {
}
