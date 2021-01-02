package com.kk.aoc.tt.validation;

public interface Validator<R, V> {
    String getName();

    R validate(V value);
}
