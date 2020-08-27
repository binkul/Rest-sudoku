package com.example.rest.sudoku.service;

import com.example.rest.sudoku.entity.SudokuField;
import com.example.rest.sudoku.entity.dto.AbsoluteSudokuDto;
import com.example.rest.sudoku.entity.dto.RelativeSudokuDto;
import com.example.rest.sudoku.entity.dto.SudokuFieldDto;
import com.example.rest.sudoku.exception.SudokuCollisionNumberException;
import com.example.rest.sudoku.mapper.SudokuMapper;
import com.example.rest.sudoku.repository.SudokuRepository;
import com.example.rest.sudoku.sudoku.Sudoku;
import com.example.rest.sudoku.sudoku.Validator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SudokuService {

    private final SudokuMapper mapper;
    private final SudokuRepository repository;

    public SudokuService(SudokuMapper mapper, SudokuRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<SudokuFieldDto> getAll() {
        return mapper.mapToSudokuDtoList(repository.findAll());
    }

    public List<SudokuFieldDto> getValid() {
        return mapper.mapToSudokuDtoList(repository.findAllByCorrect(true));
    }

    public SudokuFieldDto save(AbsoluteSudokuDto sudokuDto) {
        Sudoku sudoku = mapper.mapToSudoku(sudokuDto);
        return solve(sudoku);
    }

    public SudokuFieldDto save(RelativeSudokuDto sudokuDto) {
        Sudoku sudoku = mapper.mapToSudoku(sudokuDto);
        return solve(sudoku);
    }

    private SudokuFieldDto solve(Sudoku sudoku) {
        SudokuField sudokuField = mapper.mapToSudokuField(sudoku);

        if (Validator.isCollision(sudoku)) {
            sudokuField.setCorrect(false);
            repository.save(sudokuField);
            throw new SudokuCollisionNumberException("There is collision between number in row/column/section");
        }

        repository.save(sudokuField);
        return mapper.mapToSudokuDto(sudokuField); // mapper.mapToSudokuDto(sudoku);
    }
}
