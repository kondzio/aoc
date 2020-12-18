package com.kk.aoc.halting.program;

public class AccOperation extends AbstractOperation {
    protected AccOperation(String... params) {
        super(OperationType.ACC, 1, params);
    }

    @Override
    public void doExecute(ProgramContext context) {
        Integer param = Integer.parseInt(getParams()[0]);
        context.add(param);
        context.incrementPosition();
    }

    @Override
    public void handleLoop(ProgramContext context) {
        throw new IllegalStateException(String.format("Loop Found, stackTrace: %s", context));
    }
}
