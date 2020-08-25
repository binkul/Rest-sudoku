package com.example.rest.sudoku.service;

import com.example.rest.sudoku.entity.dto.AbsoluteSudokuDto;
import com.example.rest.sudoku.entity.dto.RelativeSudokuDto;
import com.example.rest.sudoku.entity.dto.SudokuDto;
import com.example.rest.sudoku.mapper.SudokuMapper;
import com.example.rest.sudoku.sudoku.Sudoku;
import org.springframework.stereotype.Service;

@Service
public class SudokuService {

    private final SudokuMapper mapper;

    public SudokuService(SudokuMapper mapper) {
        this.mapper = mapper;
    }

    public SudokuDto solve(AbsoluteSudokuDto sudokuDto) {
        Sudoku sudoku = mapper.mapToSudoku(sudokuDto);
        return mapper.mapToSudokuDto(sudoku);
    }

    public SudokuDto solve(RelativeSudokuDto sudokuDto) {
        Sudoku sudoku = mapper.mapToSudoku(sudokuDto);
        return mapper.mapToSudokuDto(sudoku);
    }
}
