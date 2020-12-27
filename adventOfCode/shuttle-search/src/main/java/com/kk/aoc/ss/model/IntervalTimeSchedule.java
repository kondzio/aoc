package com.kk.aoc.ss.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
@Getter
public class IntervalTimeSchedule implements TimeSchedule {
    private final int interval;

    public int nextDeparture(int timestamp) {
        int mod = timestamp % interval;
        return timestamp + (interval - mod);
    }

    @Override
    public int getBusNo() {
        return interval;
    }
}
