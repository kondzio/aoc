package com.kk.aoc.dd.model.address;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class Position {
    private final Position parent;
    private final char charAtPosition;

    private final List<Position> nextPositions = new ArrayList<>();

    public void extendAddress(char... chars) {
        if (nextPositions.isEmpty()) {
            for (char c : chars) {
                nextPositions.add(new Position(this, c));
            }
        } else {
            for (Position nextPos : nextPositions) {
                nextPos.extendAddress(chars);
            }
        }
    }
}
