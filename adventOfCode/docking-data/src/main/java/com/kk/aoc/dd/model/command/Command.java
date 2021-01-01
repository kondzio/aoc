package com.kk.aoc.dd.model.command;

import com.kk.aoc.dd.model.instruction.InstructionType;
import com.kk.aoc.dd.model.program.ExecutionContext;

public interface Command {
    InstructionType handledInstructionType();

    void execute(ExecutionContext context, Object... args);
}
