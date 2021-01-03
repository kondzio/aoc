package com.kk.aoc.cc.simulator;

public interface Simulator<T> {
    SimulationResult<T> simulateNextCycle(T currentState);
}
