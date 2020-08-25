package com.example.rest.sudoku.sudoku;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Validator {

    public static void validateRow(int row) {
        if (row < 1 || row > Data.SIZE) throw new IllegalArgumentException("Row should be between 1-9, but was:" + row);
    }

    public static void validateColumn(int column) {
        if (column < 1 || column > Data.SIZE) throw new IllegalArgumentException("Column should be between 1-9, but was:" + column);
    }

    public static void validateNumber(int number) {
        if (number < 1 || number > Data.MAX_NUMBER) throw new IllegalArgumentException("Number should be between 1-9, but was:" + number);
    }

    public static void validateMaxPosition(int position) {
        if (position < 1 || position > Data.MAX_POSITION) throw new IllegalArgumentException("Position should be between 1-81, but was:" + position);
    }

    public static boolean isFilled(Sudoku sudoku) {
        return sudoku.getField()
                .values()
                .stream()
                .map(Element::getNumber)
                .noneMatch(i -> i == Data.EMPTY);
    }

    public static boolean isCollision(Sudoku sudoku) {
        for (int i = 1; i <= Data.SIZE; i++) {
            if (isDuplicate(sudoku.getRow(i))) return true;
            if (isDuplicate(sudoku.getColumn(i))) return true;
            if (isDuplicate(sudoku.getSection(i))) return true;
        }
        return false;
    }

    private static boolean isDuplicate(Map<Position, Element> elements) {
        return elements.values()
                .stream()
                .map(Element::getNumber)
                .filter(i -> i != Data.EMPTY)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .anyMatch(i -> i > 1);
    }

}
