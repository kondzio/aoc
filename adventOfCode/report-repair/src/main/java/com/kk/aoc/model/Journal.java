package com.kk.aoc.model;

import com.kk.aoc.analyzer.Analyzer;

import java.util.List;

public interface Journal<T> {
    <R> R analyze(Analyzer<T, R> analyzer);

    List<Position<T>> getPositions();
}
