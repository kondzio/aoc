package com.kk.aoc.cc.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CubeState {
    ACTIVE('#'),
    INACTIVE('.');

    private final char marker;

    public static CubeState valueFrom(char marker) {
        for (CubeState state : CubeState.values()) {
            if (state.getMarker() == marker) {
                return state;
            }
        }
        return null;
    }
}
