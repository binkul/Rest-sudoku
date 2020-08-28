package com.example.rest.sudoku.solver.algorithm;

import com.example.rest.sudoku.sudoku.Element;
import com.example.rest.sudoku.sudoku.Sudoku;

public class StackElement {
    private final Sudoku sudoku;
    private final Element element;
    private final int index;

    public StackElement(Sudoku sudoku, Element element, int index) {
        this.sudoku = sudoku;
        this.element = element;
        this.index = index;
    }

    public Sudoku getSudokuBoard() {
        return sudoku;
    }

    public Element getSudokuElement() {
        return element;
    }

    public int getIndex() {
        return index;
    }

    public int getCandidatesSize() {
        return element.getCandidatesSize();
    }
}
