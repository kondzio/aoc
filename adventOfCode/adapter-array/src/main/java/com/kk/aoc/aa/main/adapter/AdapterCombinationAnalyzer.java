package com.kk.aoc.aa.main.adapter;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class AdapterCombinationAnalyzer {
    private final int maxDifference;
    private final int expectedJoltage;

    public int analyze(List<Integer> inputJolts) {
        List<Integer> jolts = new ArrayList<>(inputJolts);
        jolts.add(0, 0);
        Collections.sort(jolts);
        AtomicInteger counter = new AtomicInteger(0);
        analyzePossibilities(counter, jolts, 0);
        return counter.get();
    }

    private void analyzePossibilities(AtomicInteger counter, List<Integer> jolts, int startAt) {
        Integer startJoltage = jolts.get(startAt);
        Integer optionJoltage;
        int index = startAt + 1;
        while (index < jolts.size() && ((optionJoltage = jolts.get(index)) != null && optionJoltage - startJoltage <= maxDifference)) {
            if (optionJoltage == expectedJoltage) {
                counter.incrementAndGet();
            } else {
                analyzePossibilities(counter, jolts, index);
            }
            index++;
        }
    }


}
