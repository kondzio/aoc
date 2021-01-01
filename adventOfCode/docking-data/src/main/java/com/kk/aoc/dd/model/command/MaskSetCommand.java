package com.kk.aoc.dd.model.command;

import com.google.common.base.Preconditions;
import com.kk.aoc.dd.model.instruction.InstructionType;
import com.kk.aoc.dd.model.program.ExecutionContext;

public class MaskSetCommand implements Command {

    @Override
    public void execute(ExecutionContext context, Object... args) {
        Preconditions.checkState(args.length == 1, "Expected single parameter");
        String newMask = (String) args[0];
        context.setMask(newMask);
    }

    @Override
    public InstructionType handledInstructionType() {
        return InstructionType.MASK_SET;
    }
}
