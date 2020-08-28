package com.example.rest.sudoku.controller;

import com.example.rest.sudoku.entity.dto.AbsoluteSudokuDto;
import com.example.rest.sudoku.entity.dto.RelativeSudokuDto;
import com.example.rest.sudoku.entity.dto.SudokuFieldDto;
import com.example.rest.sudoku.service.SudokuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/sudoku")
public class SudokuController {

    private final SudokuService service;

    public SudokuController(SudokuService service) {
        this.service = service;
    }

    @GetMapping(value = "/all")
    public List<SudokuFieldDto> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/valid")
    public List<SudokuFieldDto> getValid() {
        return service.getValid();
    }

    @GetMapping(value = "/solve/{id}")
    public SudokuFieldDto getSolved(@PathVariable Long id) {
        return service.getSolved(id, false);
    }

    @GetMapping(value =  "/solve/color/{id}")
    public SudokuFieldDto getSolvedColor(@PathVariable Long id) {
        return service.getSolved(id, true);
    }

    @PostMapping(value = "/absolute")
    public SudokuFieldDto solve(@RequestBody AbsoluteSudokuDto sudoku) {
        return service.save(sudoku);
    }

    @PostMapping(value = "/relative")
    public SudokuFieldDto solve(@RequestBody RelativeSudokuDto sudoku) {
        return service.save(sudoku);
    }

    @DeleteMapping(value = "/del/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
