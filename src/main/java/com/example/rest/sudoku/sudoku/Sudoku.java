package com.example.rest.sudoku.sudoku;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Sudoku {
    private Map<Position, Element> field;

    public Sudoku() {
        field = new TreeMap<>(createField());
    }

    private Map<Position, Element> createField() {
        return IntStream.rangeClosed(1, Data.SIZE)
                .mapToObj(this::createRow)
                .flatMap(i -> i.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<Position, Element> createRow(int row) {
        return IntStream.rangeClosed(1, Data.SIZE)
                .collect(TreeMap::new, (m, i) -> m.put(new Position(row, i), new Element()), Map::putAll);
    }

    public Element getElement(int row, int column) {
        Validator.validateRow(row);
        Validator.validateColumn(column);

        return field.get(new Position(row, column));
    }

    public Integer getNumber(int row, int column) {
        return getElement(row, column).getNumber();
    }

    public Map<Position, Element> getRow(int row) {
        Validator.validateRow(row);

        return new TreeMap<>(field.entrySet().stream()
                .filter(i -> i.getKey().getRow() == row)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    public Map<Position, Element> getColumn(int column) {
        Validator.validateColumn(column);

        return new TreeMap<>(field.entrySet().stream()
                .filter(i -> i.getKey().getColumn() == column)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    public Map<Position, Element> getSection(int row, int column) {
        Validator.validateRow(row);
        Validator.validateColumn(column);

        int x = ((row - 1) / Data.SECTION) * Data.SECTION + 1;
        int y = ((column - 1) / Data.SECTION) * Data.SECTION + 1;

        return field.entrySet().stream()
                .filter(i -> i.getKey().getRow() >= x && i.getKey().getRow() < x + Data.SECTION)
                .filter(i -> i.getKey().getColumn() >= y && i.getKey().getColumn() < y + Data.SECTION)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Position, Element> getSection(int section) {
        int[] map = {1, 4, 7};
        return getSection(map[(section - 1) / Data.SECTION], map[(section - 1) % Data.SECTION]);
    }

    public Map<Position, Element> getSudokuSection(int row, int column) {
        return Stream.of(getRow(row), getColumn(column), getSection(row, column))
                .flatMap(i -> i.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a));
    }

    public Sudoku deepCopy() {
        Sudoku sudokuCopy = new Sudoku();
        Map<Position, Element> fieldCopy = new HashMap<>();

        for (Map.Entry<Position, Element> entry : field.entrySet()) {
            int number = entry.getValue().getNumber();
            List<Integer> candidates = new ArrayList<>(entry.getValue().getCandidates());
            FontColor fontColor = entry.getValue().getFontColor();
            Position position = new Position(entry.getKey().getRow(), entry.getKey().getColumn());
            Element element = new Element(number, candidates, fontColor);
            fieldCopy.put(position, element);
        }
        sudokuCopy.setField(fieldCopy);
        return sudokuCopy;
    }

    public Map<Position, Element> getField() {
        return field;
    }

    private void setField(Map<Position, Element> field) {
        this.field = field;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= Data.SIZE; i++) {
            result.append(getRow(i)
                    .values()
                    .stream()
                    .map(Element::getNumber)
                    .map(String::valueOf)
                    .collect(Collectors.joining("|", "|", "|\n")));
        }
        return result.toString();
    }
}
