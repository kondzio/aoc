package com.kk.aoc.halting.program;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoopDetector {
    private final int maxLoops;
    private int detectionCount = 0;

    public boolean detectLoop(Operation operation, ProgramContext context) {
        boolean detected = context.getExecutedOperations().contains(operation.getOperationId());
        if (detected) {
            detectionCount++;
        }
        if (detectionCount >= maxLoops) {
            throw new LoopDetectException("Loop was detected");
        }
        return detected;
    }
}
