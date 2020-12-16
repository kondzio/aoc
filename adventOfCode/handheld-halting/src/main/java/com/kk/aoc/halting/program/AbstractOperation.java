package com.kk.aoc.halting.program;

import lombok.ToString;

@ToString
public abstract class AbstractOperation implements Operation {
    private final String[] params;

    protected AbstractOperation(int expectedParamNumber, String... params) {
        if (expectedParamNumber >= 0) {
            OperationUtils.hasExpectedNumberOfParams(expectedParamNumber, params);
        }
        this.params = params;
    }

    public String[] getParams() {
        return params;
    }
}
