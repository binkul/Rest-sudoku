package com.example.rest.sudoku.solver.algorithm;


import com.example.rest.sudoku.exception.SudokuUnsolvableException;
import com.example.rest.sudoku.solver.Solver;
import com.example.rest.sudoku.sudoku.Data;
import com.example.rest.sudoku.sudoku.Element;
import com.example.rest.sudoku.sudoku.FontColor;
import com.example.rest.sudoku.sudoku.Sudoku;

import java.util.ArrayDeque;
import java.util.Deque;

public class SolverBackTrack {

    private final Solver solver;
    private Sudoku sudokuCopy;
    private Element element;

    public SolverBackTrack(Solver solver) {
        this.solver = solver;
    }

    public void process(Sudoku sudoku) {
        this.sudokuCopy = sudoku;
        Deque<StackElement> stack = new ArrayDeque<>();
        StackElement stackElement;
        Result result = Result.NONE;
        int index;

        while(true) {

            switch (result) {
                case FULL_FILLED:
                    solver.setSudokuBoard(sudokuCopy);
                    return;
                case ERROR:
                    boolean loop;
                    do {
                        if (stack.size() == 0) {
                            throw new SudokuUnsolvableException("There is no solution for this Sudoku.");
                        }
                        stackElement = stack.pop();
                        index = (stackElement.getIndex()) + 1;
                        if (index < stackElement.getCandidatesSize()) {
                            // recover present state
                            sudokuCopy = stackElement.getSudokuBoard();
                            element = stackElement.getSudokuElement();
                            putOnStack(stack, index);
                            loop = false;
                        } else {
                            loop = true;
                        }
                    } while (loop);
                    break;
                default:
                    element = findFirstEmpty(sudokuCopy);
                    putOnStack(stack, 0);
            }
            result = solver.getSolverStandard().process(sudokuCopy);
        }
    }

    private void putOnStack(Deque<StackElement> stack, int index) {

        StackElement stackElement = new StackElement(sudokuCopy, element, index);
        stack.push(stackElement);

        sudokuCopy = sudokuCopy.deepCopy();
        element = findFirstEmpty(sudokuCopy);

        int number = element.getCandidate(index);
        element.setNumber(number);
        element.setFontColor(FontColor.BLUE);
    }

    private Element findFirstEmpty(Sudoku sudoku) {
        return sudoku.getField()
                .values()
                .stream()
                .filter(i -> i.getNumber() == Data.EMPTY)
                .findFirst()
                .orElseThrow(() -> new SudokuUnsolvableException("There is no solution for this Sudoku."));
    }
}
