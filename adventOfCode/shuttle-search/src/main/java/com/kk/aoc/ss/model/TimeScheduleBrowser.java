package com.kk.aoc.ss.model;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
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

    public int findDesiredConnectionDeparture(List<Connection> connections) {
        Connection max = Collections.max(connections);
        int nexTry = max.getBusNo() - max.getDelay();
        while (true) {
            int finalNexTry = nexTry;
            boolean found = connections.stream().filter(connection -> connection != max).noneMatch(connection -> 0 != ((finalNexTry + connection.getDelay()) % connection.getBusNo()));
            if (found) {
                return nexTry;
            }
            nexTry += max.getBusNo();
        }
    }
}
