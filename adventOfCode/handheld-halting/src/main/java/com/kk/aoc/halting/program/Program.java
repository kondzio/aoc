package com.kk.aoc.halting.program;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Program {
    private final Operation[] operationVector;

    public void execute(OperationContext context) {
        while (context.getCurrentPosition() < operationVector.length) {
            operationVector[context.getCurrentPosition()].execute(context);
            System.err.println("pos: " + context.getCurrentPosition() + ", acc: " + context.getAccumulator());
        }
    }
}
