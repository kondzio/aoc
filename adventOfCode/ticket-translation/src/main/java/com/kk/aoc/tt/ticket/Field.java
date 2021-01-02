package com.kk.aoc.tt.ticket;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class Field<T> {
    private final int position;
    private final T value;
}
