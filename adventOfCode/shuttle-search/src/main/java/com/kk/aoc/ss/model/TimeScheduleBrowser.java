package com.kk.aoc.ss.model;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TimeScheduleBrowser {
    private final List<TimeSchedule> busSchedules;

    public DepartureDetails findNextBus(int timestamp) {
        int busNo = 0;
        int closestDeparture = Integer.MAX_VALUE;
        for (TimeSchedule busSchedule : busSchedules) {
            int nextDeparture = busSchedule.nextDeparture(timestamp);
            if (nextDeparture < closestDeparture) {
                closestDeparture = nextDeparture;
                busNo = busSchedule.getBusNo();
            }
        }
        return DepartureDetails.builder().busNo(busNo).departureTime(closestDeparture).build();
    }
}
