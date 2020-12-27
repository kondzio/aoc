package com.kk.aoc.ss.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class DepartureDetails {
    private final int busNo;
    private final int departureTime;
}
