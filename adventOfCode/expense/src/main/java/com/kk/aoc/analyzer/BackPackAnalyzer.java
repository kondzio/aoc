package com.kk.aoc.analyzer;

import com.kk.aoc.model.Journal;
import com.kk.aoc.model.Transaction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BackPackAnalyzer implements Analyzer<Integer, List<BackPackAnalyzer.MultiplicationResult>> {
    private final int capacity;

    @Override
    public List<MultiplicationResult> evaluate(Journal<Integer> journal) {
        List<Transaction<Integer>> transactions = journal.getTransactions();
        List<MultiplicationResult> results = new ArrayList<>();
        Collections.sort(transactions, (t1, t2) -> t2.getAmount().compareTo(t1.getAmount()));
        for (int i = 0; i < transactions.size() - 1; i++) {
            Transaction<Integer> transaction = transactions.get(i);
            check(capacity - transaction.getAmount(), transactions.subList(i + 1, transactions.size()), new ArrayList<>(List.of(transaction)), results);
        }
        return results;
    }

    private void check(Integer capacity, List<Transaction<Integer>> toCheck, List<Transaction<Integer>> vector, List<MultiplicationResult> results) {
        for (int i = 0; i < toCheck.size(); i++) {
            Transaction<Integer> t = toCheck.get(i);
            if (t.getAmount() < capacity) {
                List<Transaction<Integer>> newVector = new ArrayList<>(vector);
                newVector.add(t);
                if (i < (toCheck.size() - 1)) {
                    check(capacity - t.getAmount(), toCheck.subList(i + 1, toCheck.size()), newVector, results);
                }
            } else if (t.getAmount().equals(capacity)) {
                vector.add(t);
                results.add(new MultiplicationResult(vector.stream().map(Transaction::getAmount).collect(Collectors.toList())));
            }
        }
    }


    @Getter
    @ToString
    public static class MultiplicationResult {
        private final List<Integer> amounts;
        private int multiplication = 1;

        public MultiplicationResult(List<Integer> amounts) {
            this.amounts = new ArrayList<>(amounts);
            amounts.forEach(a -> multiplication *= a);
        }

        public void addAmount(Integer amount) {
            amounts.add(amount);
            multiplication *= amount;
        }
    }
}
