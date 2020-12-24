package com.kk.aoc.ss.model;

import lombok.ToString;

public enum SeatState {
    OCCUPIED("#"),
    AVAILABLE("L"),
    FLOOR(".");

    private final String marker;

    SeatState(String marker) {
        this.marker = marker;
    }

    public String getMarker() {
        return marker;
    }

    public static SeatState fromValue(String value) {
        for (SeatState seatState : SeatState.values()) {
            if (seatState.getMarker().equals(value)) {
                return seatState;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getMarker();
    }
}
