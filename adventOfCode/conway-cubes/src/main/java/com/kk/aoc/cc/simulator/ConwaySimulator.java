package com.kk.aoc.cc.simulator;

import com.kk.aoc.cc.model.Conway;
import com.kk.aoc.cc.model.CubeState;
import com.kk.aoc.cc.utils.CubeUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ConwaySimulator implements Simulator<Conway> {
    @Override
    public SimulationResult<Conway> simulateNextCycle(Conway currentState) {
        Conway evaluatedConway = new Conway(currentState.getXSize(), currentState.getYSize(), currentState.getZSize());
        int stateChangeCounter = 0;
        for (int x = 0; x < currentState.getXSize(); x++) {
            for (int y = 0; y < currentState.getYSize(); y++) {
                for (int z = 0; z < currentState.getZSize(); z++) {
                    EvaluatedState<CubeState> evaluatedState = evaluateState(currentState.getWithSurrounding(x, y, z));
                    evaluatedConway.put(x, y, z, evaluatedState.getState());
                    if (evaluatedState.isChanged()) {
                        stateChangeCounter++;
                    }
                }
            }
        }
        return new SimulationResult<>(evaluatedConway, stateChangeCounter);
    }

    private EvaluatedState<CubeState> evaluateState(CubeState[][][] surrounding) {
        CubeState currentState = surrounding[1][1][1];
        int activeCubesCount = activeAdjacentCubesCount(surrounding, currentState);
        if (CubeState.ACTIVE == currentState) {
            if (activeCubesCount >= 2 && activeCubesCount <= 3) {
                return new EvaluatedState<>(CubeState.ACTIVE, false);
            } else {
                return new EvaluatedState<>(CubeState.INACTIVE, true);
            }
        } else if (CubeState.INACTIVE == currentState) {
            if (activeCubesCount == 3) {
                return new EvaluatedState<>(CubeState.ACTIVE, true);
            } else {
                return new EvaluatedState<>(CubeState.INACTIVE, false);
            }
        } else {
            throw new IllegalStateException(String.format("State: '%s' is not supported", currentState));
        }
    }

    private int activeAdjacentCubesCount(CubeState[][][] surrounding, CubeState currentSate) {
        int adjacentCount = CubeUtils.calculateCubesInState(surrounding, CubeState.ACTIVE);
        if (currentSate == CubeState.ACTIVE) {
            adjacentCount--;
        }
        return adjacentCount;
    }


    @RequiredArgsConstructor
    @Getter
    private static class EvaluatedState<S> {
        private final S state;
        private final boolean changed;
    }
}