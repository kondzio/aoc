package com.kk.aoc.halting.program;

public interface Operation {
    OperationType getType();

    void execute(OperationContext context);
}
