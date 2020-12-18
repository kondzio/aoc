package com.kk.aoc.ee.main.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IndexDescriptor {
    private final int value;
    private final int index;
}
