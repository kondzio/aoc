package com.kk.aoc.aa.main.adapter;

import lombok.Builder;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Builder
@ToString
public class AdapterChain {
    private final List<Integer> chain;
    private final Map<Integer, Integer> joltDifferences;

    public List<Integer> getChain() {
        return Collections.unmodifiableList(chain);
    }

    public Map<Integer, Integer> getJoltDifferences() {
        return Collections.unmodifiableMap(joltDifferences);
    }
}
