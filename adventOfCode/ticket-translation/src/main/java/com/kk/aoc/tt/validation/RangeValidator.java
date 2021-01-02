package com.kk.aoc.tt.validation;

import com.kk.aoc.tt.ticket.Field;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RangeValidator implements Validator<Boolean, Field<Integer>> {
    private final String name;
    private final int from;
    private final int to;

    @Override
    public Boolean validate(Field<Integer> field) {
        return field.getValue() >= from && field.getValue() <= to;
    }

    @Override
    public String getName() {
        return name;
    }
}
