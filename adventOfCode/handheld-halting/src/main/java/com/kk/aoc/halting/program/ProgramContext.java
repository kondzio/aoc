package com.kk.aoc.halting.program;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@ToString
@Builder(toBuilder = true)
public class ProgramContext {

    private int currentPosition;
    private int accumulator;

    @Builder.Default
    private Set<String> executedOperations = new HashSet<>();

    void incrementPosition() {
        incrementPosition(1);
    }

    void incrementPosition(int i) {
        currentPosition += i;
    }

    public void add(Integer value) {
        accumulator += value;
    }

    public void registerExecutedOperation(Operation operation) {
        executedOperations.add(operation.getOperationId());
    }

    public void restore(ProgramContext backupContext) {
        currentPosition = backupContext.currentPosition;
        accumulator = backupContext.accumulator;
    }
}
