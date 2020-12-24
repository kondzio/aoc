package com.kk.aoc.ss.simulator;

import com.kk.aoc.ss.model.Lobby;
import com.kk.aoc.ss.model.SeatState;
import com.kk.aoc.ss.utils.SeatUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class LobbyStateSimulator implements Simulator<Lobby> {
    @Override
    public SimulationResult<Lobby> simulateNextRound(Lobby lobby) {
        int stateChangeCounter = 0;
        Lobby newLobbyState = new Lobby(lobby.getWidth(), lobby.getLength());
        for (int x = 0; x < lobby.getWidth(); x++) {
            for (int y = 0; y < lobby.getLength(); y++) {
                EvaluatedState evaluatedState = evaluateState(lobby.getWithSurrounding(x, y));
                newLobbyState.put(x, y, evaluatedState.getState());
                if (evaluatedState.isChanged()) {
                    stateChangeCounter++;
                }
            }
        }
        return new SimulationResult<>(newLobbyState, stateChangeCounter);
    }

    private EvaluatedState evaluateState(SeatState[][] surrounding) {
        SeatState currentSeatState = surrounding[1][1];
        if (SeatState.AVAILABLE == currentSeatState && 0 == occupiedAdjacentSeatsNumber(surrounding, currentSeatState)) {
            return new EvaluatedState(SeatState.OCCUPIED, true);
        }
        if (SeatState.OCCUPIED == currentSeatState && 4 <= occupiedAdjacentSeatsNumber(surrounding, currentSeatState)) {
            return new EvaluatedState(SeatState.AVAILABLE, true);
        }
        return new EvaluatedState(currentSeatState, false);
    }

    private int occupiedAdjacentSeatsNumber(SeatState[][] surrounding, SeatState currentSate) {
        int adjacentCount = SeatUtils.calculateNumberOf(surrounding, SeatState.OCCUPIED);
        if (currentSate == SeatState.OCCUPIED) {
            adjacentCount--;
        }
        return adjacentCount;
    }

    @RequiredArgsConstructor
    @Getter
    private static class EvaluatedState {
        private final SeatState state;
        private final boolean changed;
    }
}
