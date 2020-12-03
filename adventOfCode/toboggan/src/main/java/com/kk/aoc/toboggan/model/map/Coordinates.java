package com.kk.aoc.toboggan.model.map;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Builder
@ToString
public class Coordinates {
    private final int x;
    private final int y;
}
