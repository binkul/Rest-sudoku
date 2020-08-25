package com.example.rest.sudoku.controller;

import com.example.rest.sudoku.entity.dto.RelativeSudokuDto;
import com.example.rest.sudoku.entity.dto.AbsoluteSudokuDto;
import com.example.rest.sudoku.entity.dto.SudokuDto;
import com.example.rest.sudoku.service.SudokuService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/sudoku")
public class SudokuController {

    private final SudokuService service;

    public SudokuController(SudokuService service) {
        this.service = service;
    }

    @PostMapping(value = "/absolute")
    public SudokuDto solve(@RequestBody AbsoluteSudokuDto sudoku) {
        return service.solve(sudoku);
    }

    @PostMapping(value = "/relative")
    public SudokuDto solve(@RequestBody RelativeSudokuDto sudoku) {
        return service.solve(sudoku);
    }
}
