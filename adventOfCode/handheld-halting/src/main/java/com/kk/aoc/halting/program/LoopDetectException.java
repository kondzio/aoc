package com.kk.aoc.halting.program;

public class LoopDetectException extends RuntimeException {
    public LoopDetectException(String message) {
        super(message);
    }
}
