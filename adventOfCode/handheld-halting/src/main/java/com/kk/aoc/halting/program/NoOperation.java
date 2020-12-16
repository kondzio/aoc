package com.kk.aoc.halting.program;

public class NoOperation extends AbstractOperation {
    protected NoOperation(String... params) {
        super(-1, params);
    }

    @Override
    public OperationType getType() {
        return OperationType.NOP;
    }

    @Override
    public void execute(OperationContext context) {
        context.incrementPosition();
    }
}
