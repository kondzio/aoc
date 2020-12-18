package com.kk.aoc.halting.program;

public interface Operation {
    String getOperationId();

    OperationType getType();

    void execute(ProgramContext context);

    void handleLoop(ProgramContext context);
}
