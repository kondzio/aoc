package com.kk.aoc.toboggan.model.map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MapObjects implements MapObject {
    TREE("#"),
    EMPTY(".");

    private final String marker;

    public static MapObject objectFrom(String marker) {
        for (MapObject object : MapObjects.values()) {
            if (object.getMarker().equals(marker)) {
                return object;
            }
        }
        return null;
    }
}
