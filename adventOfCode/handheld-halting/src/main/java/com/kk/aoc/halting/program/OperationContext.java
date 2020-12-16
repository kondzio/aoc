package com.kk.aoc.halting.program;

import lombok.Getter;

@Getter
public class OperationContext {

    private int currentPosition = 0;
    private int accumulator = 0;

    void incrementPosition() {
        incrementPosition(1);
    }

    void incrementPosition(int i) {
        currentPosition += i;
    }

    public void add(Integer value) {
        accumulator += value;
    }
}
