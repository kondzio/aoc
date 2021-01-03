package com.kk.aoc.cc.simulator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SimulationResult<T> {
    private final T result;
    private final int changesCount;
}
