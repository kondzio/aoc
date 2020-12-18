package com.kk.aoc.halting.program;

import com.kk.aoc.halting.log.Logger;

import java.util.Arrays;

public class JumpOperation extends AbstractOperation {
    protected JumpOperation(String... params) {
        super(OperationType.JMP, 1, params);
    }

    @Override
    public void doExecute(ProgramContext context) {
        int param = Integer.parseInt(getParams()[0]);
        context.incrementPosition(param);
    }

    @Override
    public void handleLoop(ProgramContext context) {
        System.err.println(String.format("***** *** %s %s => %s %s", getType().getName(), Arrays.toString(getParams()), OperationType.NOP.getName(), Arrays.toString(getParams())));
        NoOperation noOperation = new NoOperation(getParams());
        Logger.log(context.getCurrentPosition(), noOperation, context);
        noOperation.doExecute(context);
    }
}
