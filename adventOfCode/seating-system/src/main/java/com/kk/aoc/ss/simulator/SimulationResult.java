package com.kk.aoc.ss.simulator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SimulationResult<T> {
    private final T result;
    private final int changesCount;
}
