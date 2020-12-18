package com.kk.aoc.model;

import com.kk.aoc.analyzer.Analyzer;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ExpenseJournal implements Journal<Integer> {
    private final List<Position<Integer>> positions;

    @Override
    public <R> R analyze(Analyzer<Integer, R> analyzer) {
        return analyzer.evaluate(this);
    }

    @Override
    public List<Position<Integer>> getPositions() {
        return positions;
    }
}
