package com.kk.aoc.rr.model.move;

import com.kk.aoc.rr.model.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public class PositionRotations implements Rotations<UnaryOperator<Position>> {
    public static final int DG_0 = 0;
    public static final int DG_90 = 90;
    public static final int DG_180 = 180;
    public static final int DG_270 = 270;
    private final Map<Integer, UnaryOperator<Position>> rotations = new HashMap<>();

    public PositionRotations(UnaryOperator<Position> dg0, UnaryOperator<Position> dg90, UnaryOperator<Position> dg180, UnaryOperator<Position> dg270) {
        rotations.put(DG_0, dg0);
        rotations.put(DG_90, dg90);
        rotations.put(DG_180, dg180);
        rotations.put(DG_270, dg270);
    }

    @Override
    public UnaryOperator<Position> rotate(int degrees) {
        int normalizedDegrees = degrees % 360;
        if (normalizedDegrees < 0) {
            normalizedDegrees = 360 + normalizedDegrees;
        }
        return rotations.get(normalizedDegrees);
    }
}
