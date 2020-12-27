package com.kk.aoc.ss.model;

public interface TimeSchedule {
    int getBusNo();

    int nextDeparture(int timestamp);
}
