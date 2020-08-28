package com.example.rest.sudoku.service;

import com.example.rest.sudoku.entity.SudokuField;
import com.example.rest.sudoku.entity.dto.AbsoluteSudokuDto;
import com.example.rest.sudoku.entity.dto.RelativeSudokuDto;
import com.example.rest.sudoku.entity.dto.SudokuFieldDto;
import com.example.rest.sudoku.exception.EntityNotFoundException;
import com.example.rest.sudoku.exception.SudokuCollisionNumberException;
import com.example.rest.sudoku.mapper.SudokuMapper;
import com.example.rest.sudoku.repository.SudokuRepository;
import com.example.rest.sudoku.solver.Solver;
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

    public SudokuFieldDto getSolved(Long id, boolean colored) {
        SudokuField sudokuField = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sudoku with id=" + id + " was not found."));

        if (!sudokuField.isCorrect()) throw new SudokuCollisionNumberException("There is collision between number in row/column/section");

        Sudoku sudoku = mapper.mapToSudoku(sudokuField);
        Solver solver = new Solver(sudoku);
        solver.process();

        if (colored) return mapper.mapToColorSudokuDto(solver.getSudoku(), sudokuField);
        else return mapper.mapToSudokuDto(solver.getSudoku(), id);
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
        SudokuField sudokuField = mapper.mapToSudokuField(sudoku, null);

        if (Validator.isCollision(sudoku)) {
            sudokuField.setCorrect(false);
            repository.save(sudokuField);
            throw new SudokuCollisionNumberException("There is collision between number in row/column/section");
        }

        repository.save(sudokuField);
        Solver solver = new Solver(sudoku);
        solver.process();
        sudokuField = mapper.mapToSudokuField(solver.getSudoku(), sudokuField.getId());
        return mapper.mapToSudokuDto(sudokuField);
    }

    public void delete(Long id) {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sudoku with id=" + id + " was not found."));
        repository.deleteById(id);
    }
}
