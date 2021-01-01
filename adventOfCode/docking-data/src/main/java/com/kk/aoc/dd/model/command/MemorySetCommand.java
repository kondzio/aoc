package com.kk.aoc.dd.model.command;

import com.google.common.base.Preconditions;
import com.kk.aoc.dd.model.instruction.InstructionType;
import com.kk.aoc.dd.model.program.ExecutionContext;

public class MemorySetCommand implements Command {
    @Override
    public void execute(ExecutionContext context, Object... args) {
        Preconditions.checkState(args.length == 2, "Expected exact 2 parameter");
        long address = (long) args[0];
        long value = (long) args[1];
        context.setValue(address, value);
    }

    @Override
    public InstructionType handledInstructionType() {
        return InstructionType.MEMORY_SET;
    }
}
