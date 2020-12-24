package com.kk.aoc.ee.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IndexDescriptor {
    private final int value;
    private final int index;
}
