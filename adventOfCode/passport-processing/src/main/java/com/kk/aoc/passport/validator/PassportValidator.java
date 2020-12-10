package com.kk.aoc.passport.validator;

public interface PassportValidator<T> {
    boolean validate(T passport) throws Exception;
}
