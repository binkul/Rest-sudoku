package com.example.rest.sudoku.solver.algorithm;


import com.example.rest.sudoku.sudoku.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SolverStandard extends SolverSingle {

    public Result process(Sudoku sudoku) {
        boolean repeat;

        do {
            repeat = false;
            for (int row = 1; row <= Data.SIZE; row++) {
                for (int column = 1; column <= Data.SIZE; column++) {
                    Result result = getResult(sudoku, row, column);
                    if (result == Result.ERROR) {
                        return result;
                    } else if (result == Result.ADDED) {
                        repeat = true;
                    }
                }
            }
        } while (repeat);

        return Validator.isFilled(sudoku) ? Result.FULL_FILLED : Result.NONE;
    }

    private Result getResult(Sudoku sudoku, int row, int column) {
        if (sudoku.getNumber(row, column) == Data.EMPTY) {
            return analyze(sudoku, row, column);
        }
        return Result.NONE;
    }

    private Result analyze(Sudoku sudoku, int row, int column) {
        Result result = super.process(sudoku, row, column);
        if (result != Result.NONE) return result;

        Element element = sudoku.getElement(row, column);
        Set<Integer> existingNumbers = super.getNumbersFromSudokuSection(sudoku, row, column);
        if (addUniqueFromExisting(sudoku.getRow(row), element, existingNumbers)) return Result.ADDED;
        if (addUniqueFromExisting(sudoku.getColumn(column), element, existingNumbers)) return Result.ADDED;
        if (addUniqueFromExisting(sudoku.getSection(row, column), element, existingNumbers)) return Result.ADDED;

        return Result.NONE;
    }

    private boolean addUniqueFromExisting(Map<Position, Element> elements, Element element, Set<Integer> existingNumbers) {
        Boolean[] result = {false};
        findUniqueCandidates(elements)
                .stream()
                .filter(element::containCandidate)
                .filter(i -> !existingNumbers.contains(i))
                .findFirst()
                .ifPresent((i) -> {
                    element.setNumber(i);
                    element.setFontColor(FontColor.GREEN);
                    result[0] = true;
                });
        return result[0];
    }

    private List<Integer> findUniqueCandidates(Map<Position, Element> elements) {
        return elements.values()
                .stream()
                .filter(i -> i.getNumber() == Data.EMPTY)
                .map(Element::getCandidates)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(i -> i.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
