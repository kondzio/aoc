package com.kk.aoc.halting.program;

import lombok.ToString;

@ToString
public abstract class AbstractOperation implements Operation {
    private final String[] params;
    private int executionCounter = 0;

    protected AbstractOperation(int expectedParamNumber, String... params) {
        if (expectedParamNumber >= 0) {
            OperationUtils.hasExpectedNumberOfParams(expectedParamNumber, params);
        }
        this.params = params;
    }

    public String[] getParams() {
        return params;
    }

    @Override
    public void execute(OperationContext context) {
        executionCounter++;
        if (executionCounter > 1) {
            throw new IllegalStateException(String.format("Loop Found, stackTrace: %s", context));
        }
        doExecute(context);
    }

    protected abstract void doExecute(OperationContext context);

}
