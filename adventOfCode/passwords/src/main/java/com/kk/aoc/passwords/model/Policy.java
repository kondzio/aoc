package com.kk.aoc.passwords.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Policy {
    private final int min;
    private final int max;
    private final String literal;
}
