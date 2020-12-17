package com.kk.aoc.halting.program;

public class JumpOperation extends AbstractOperation {
    protected JumpOperation(String... params) {
        super(1, params);
    }

    @Override
    public OperationType getType() {
        return OperationType.JMP;
    }

    @Override
    public void doExecute(OperationContext context) {
        int param = Integer.parseInt(getParams()[0]);
        context.incrementPosition(param);
    }
}
