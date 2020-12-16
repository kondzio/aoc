package com.kk.aoc.halting.program;

import java.util.Arrays;

public class Compiler {
    public Operation compile(String[] code) {
        if (code.length < 1) {
            throw new IllegalArgumentException("Incorrect input length " + code.length);
        }
        OperationType type = OperationType.valueFrom(code[0]);
        String[] params = Arrays.copyOfRange(code, 1, code.length);

        switch (type) {
            case ACC:
                return new AccOperation(params);
            case JMP:
                return new JumpOperation(params);
            case NOP:
                return new NoOperation(params);
            default:
                throw new IllegalStateException(String.format("Operation '%s' is not supported", Arrays.toString(code)));
        }
    }
}
