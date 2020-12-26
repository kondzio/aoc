package com.kk.aoc.rr.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Position {
    private final int horizontal;
    private final int vertical;
}
