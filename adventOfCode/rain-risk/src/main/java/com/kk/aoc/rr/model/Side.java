package com.kk.aoc.rr.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Side {
    LEFT(-1),
    RIGHT(1);

    final int degreesFactor;
}
