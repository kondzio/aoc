package com.kk.aoc.analyzer;

import com.kk.aoc.model.Journal;
import com.kk.aoc.model.Position;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ReportAnalyzer implements Analyzer<Integer, List<ReportAnalyzer.MultiplicationResult>> {
    private final int expectedSum;

    @Override
    public List<MultiplicationResult> evaluate(Journal<Integer> journal) {
        List<Position<Integer>> positions = journal.getPositions();
        List<MultiplicationResult> results = new ArrayList<>();
        Collections.sort(positions, (t1, t2) -> t2.getValue().compareTo(t1.getValue()));
        for (int i = 0; i < positions.size() - 1; i++) {
            Position<Integer> position = positions.get(i);
            check(expectedSum - position.getValue(), positions.subList(i + 1, positions.size()), new ArrayList<>(List.of(position)), results);
        }
        return results;
    }

    private void check(Integer capacity, List<Position<Integer>> toCheck, List<Position<Integer>> vector, List<MultiplicationResult> results) {
        for (int i = 0; i < toCheck.size(); i++) {
            Position<Integer> t = toCheck.get(i);
            if (t.getValue() < capacity) {
                List<Position<Integer>> newVector = new ArrayList<>(vector);
                newVector.add(t);
                if (i < (toCheck.size() - 1)) {
                    check(capacity - t.getValue(), toCheck.subList(i + 1, toCheck.size()), newVector, results);
                }
            } else if (t.getValue().equals(capacity)) {
                vector.add(t);
                results.add(new MultiplicationResult(vector.stream().map(Position::getValue).collect(Collectors.toList())));
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
