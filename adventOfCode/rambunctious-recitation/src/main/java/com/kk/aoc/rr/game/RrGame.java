package com.kk.aoc.rr.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RrGame {
    private int lastTurn;
    private final Map<Integer, Integer> previousTurns = new HashMap<>();
    private int latestNumber;

    public RrGame(List<Integer> initNumbers) {
        lastTurn = 0;
        for (int i = 0; i < initNumbers.size(); i++) {
            latestNumber = initNumbers.get(i);
            insertLatestNumber();
        }
        latestNumber = 0;
    }

    private void insertLatestNumber() {
        lastTurn++;
        previousTurns.put(latestNumber, lastTurn);
    }

    public int nextNumber() {
        Integer prevTurn = previousTurns.get(latestNumber);
        insertLatestNumber();

        int nextNum = 0;
        if (prevTurn != null) {
            nextNum = lastTurn - prevTurn;
        }
        latestNumber = nextNum;
        return nextNum;
    }

    public int nextNumber(int turnNo) {
        int num = -1;
        while (lastTurn < turnNo) {
            num = nextNumber();
        }
        return num;
    }
}
