package com.kk.aoc.ss.simulator;

public interface Simulator<T> {
    SimulationResult<T> simulateNextRound(T currentState);
}
