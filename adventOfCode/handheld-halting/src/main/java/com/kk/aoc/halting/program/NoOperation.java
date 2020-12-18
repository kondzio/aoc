package com.kk.aoc.halting.program;

import com.kk.aoc.halting.log.Logger;

public class NoOperation extends AbstractOperation {
    protected NoOperation(String... params) {
        super(OperationType.NOP, -1, params);
    }

    @Override
    public void doExecute(ProgramContext context) {
        context.incrementPosition();
    }

    @Override
    public void handleLoop(ProgramContext context) {
        System.err.println(String.format("***** *** %s %s => %s %s", getType().getName(), getParams(), OperationType.JMP.getName(), getParams()));
        JumpOperation jumpOperation = new JumpOperation(getParams());
        Logger.log(context.getCurrentPosition(), jumpOperation, context);
        jumpOperation.doExecute(context);
    }
}
