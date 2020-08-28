package com.example.rest.sudoku.solver;

import com.example.rest.sudoku.exception.SudokuCollisionNumberException;
import com.example.rest.sudoku.solver.algorithm.Result;
import com.example.rest.sudoku.solver.algorithm.SolverBackTrack;
import com.example.rest.sudoku.solver.algorithm.SolverStandard;
import com.example.rest.sudoku.sudoku.Sudoku;
import com.example.rest.sudoku.sudoku.Validator;


public class Solver {
    private Sudoku sudoku;
    private final SolverStandard solverStandard;
    private final SolverBackTrack solverBackTrack;

    public Solver(Sudoku sudoku) {
        this.sudoku = sudoku;
        solverStandard = new SolverStandard();
        solverBackTrack = new SolverBackTrack(this);
    }

    public void process() {
        if (Validator.isCollision(sudoku)) throw new SudokuCollisionNumberException("There are the same numbers in row/column/section");
        solverStandard.process(sudoku);
        if (solverStandard.process(sudoku) != Result.FULL_FILLED) {
            solverBackTrack.process(sudoku);
        }
    }

    public SolverStandard getSolverStandard() {
        return solverStandard;
    }

    public Sudoku getSudoku() {
        return sudoku;
    }

    public void setSudokuBoard(Sudoku sudoku) {
        this.sudoku = sudoku;
    }
}
