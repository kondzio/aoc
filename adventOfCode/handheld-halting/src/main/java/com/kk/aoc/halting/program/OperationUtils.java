package com.kk.aoc.halting.program;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OperationUtils {

    public static void hasExpectedNumberOfParams(int expectedNumOfParams, Object[] params) {
        if (params.length != expectedNumOfParams) {
            throw new IllegalArgumentException("Single parameter is expected");
        }
    }
}
