package com.kk.aoc.bb.model;

public enum CodeSign {
    F('F'),
    B('B'),
    L('L'),
    R('R');

    private final char sign;

    CodeSign(char sign) {
        this.sign = sign;
    }

    public char getSign() {
        return sign;
    }

    public static CodeSign valueFrom(char value) {
        for (CodeSign sign : CodeSign.values()) {
            if (sign.getSign() == value) {
                return sign;
            }
        }
        return null;
    }
}
