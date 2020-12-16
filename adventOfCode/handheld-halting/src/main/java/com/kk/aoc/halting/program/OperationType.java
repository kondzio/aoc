package com.kk.aoc.halting.program;

public enum OperationType {
    ACC("acc"),
    JMP("jmp"),
    NOP("nop");

    private final String name;

    OperationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static OperationType valueFrom(String from) {
        for (OperationType type : OperationType.values()) {
            if (type.getName().equals(from)) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("Not supported operation '%s'", from));
    }
}
