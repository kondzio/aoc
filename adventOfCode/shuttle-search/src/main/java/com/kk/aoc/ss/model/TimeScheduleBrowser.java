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
        int nextTry = max.getBusNo() - max.getDelay();
        while (true) {
            int finalNextTry = nextTry;
            boolean found = connections.stream().filter(connection -> connection != max).noneMatch(connection -> 0 != ((finalNextTry + connection.getDelay()) % connection.getBusNo()));
            if (found) {
                return nextTry;
            }
            nextTry += max.getBusNo();
        }
    }
}
