package com.kk.aoc.analyzer;

import com.kk.aoc.model.Journal;

public interface Analyzer<T, R> {
    R evaluate(Journal<T> journal);
}
