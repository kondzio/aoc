package com.kk.aoc.passport.parser;

public interface PassportParser<I, O> {
    O parse(I input) throws Exception;
}
