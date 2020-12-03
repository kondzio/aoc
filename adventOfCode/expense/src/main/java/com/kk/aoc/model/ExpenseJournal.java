package com.kk.aoc.model;

import com.kk.aoc.analyzer.Analyzer;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ExpenseJournal implements Journal<Integer> {
    private final List<Transaction<Integer>> transactions;

    @Override
    public <R> R analyze(Analyzer<Integer, R> analyzer) {
        return analyzer.evaluate(this);
    }

    @Override
    public List<Transaction<Integer>> getTransactions() {
        return transactions;
    }
}
