package com.kk.aoc.halting.program;

public class AccOperation extends AbstractOperation {
    protected AccOperation(String... params) {
        super(1, params);
    }

    @Override
    public OperationType getType() {
        return OperationType.ACC;
    }

    @Override
    public void doExecute(OperationContext context) {
        Integer param = Integer.parseInt(getParams()[0]);
        context.add(param);
        context.incrementPosition();
    }
}
