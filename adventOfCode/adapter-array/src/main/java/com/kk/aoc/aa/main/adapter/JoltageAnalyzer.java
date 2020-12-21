package com.kk.aoc.aa.main.adapter;

import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class JoltageAnalyzer {
    private final int maxDifference;

    public AdapterChain analyze(List<Integer> inputJolts) {
        Map<Integer, AtomicInteger> joltDifferences = new HashMap<>();
        for (int i = 0; i <= maxDifference; i++) {
            joltDifferences.put(i, new AtomicInteger(0));
        }
        List<Integer> jolts = new ArrayList<>(inputJolts);
        Collections.sort(jolts);
        List<Integer> joltChain = new ArrayList<>();
        int previousJoltage = 0;
        for (Integer joltage : jolts) {
            int difference = joltage - previousJoltage;
            if (difference <= maxDifference) {
                joltDifferences.get(difference).incrementAndGet();
                joltChain.add(joltage);
            }
            previousJoltage = joltage;
        }

        Map<Integer, Integer> diffs = new HashMap<>();
        joltDifferences.forEach((key, value) -> diffs.put(key, value.get()));
        return AdapterChain.builder().chain(joltChain).joltDifferences(diffs).build();
    }
}
