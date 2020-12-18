package com.kk.aoc.halting.program;

import lombok.ToString;

import java.util.UUID;

@ToString
public abstract class AbstractOperation implements Operation {
    private final String operationId = UUID.randomUUID().toString();
    private final String[] params;
    private final OperationType type;

    protected AbstractOperation(OperationType type, int expectedParamNumber, String... params) {
        if (expectedParamNumber >= 0) {
            OperationUtils.hasExpectedNumberOfParams(expectedParamNumber, params);
        }
        this.params = params;
        this.type = type;
    }

    public String[] getParams() {
        return params;
    }

    @Override
    public String getOperationId() {
        return operationId;
    }

    @Override
    public OperationType getType() {
        return type;
    }

    @Override
    public void execute(ProgramContext context) {
        doExecute(context);
    }

    protected abstract void doExecute(ProgramContext context);
}
