package com.kk.aoc.tt.validation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RangeValidator implements Validator<Boolean, Integer> {
    private final String name;
    private final int from;
    private final int to;

    @Override
    public Boolean validate(Integer value) {
        return value >= from && value <= to;
    }

    @Override
    public String getName() {
        return name;
    }
}
