package com.kk.aoc.bb.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Builder
@RequiredArgsConstructor
@Getter
@ToString
public final class Ticket {
    private final String code;
    private final int rowNum;
    private final int seatNum;
    private final int seatId;
}
