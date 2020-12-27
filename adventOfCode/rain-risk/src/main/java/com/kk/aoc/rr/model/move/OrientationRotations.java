package com.kk.aoc.rr.model.move;

import com.kk.aoc.rr.model.Orientation;

import java.util.HashMap;
import java.util.Map;

public class OrientationRotations implements Rotations<Orientation> {
    public static final int DG_0 = 0;
    public static final int DG_90 = 90;
    public static final int DG_180 = 180;
    public static final int DG_270 = 270;
    private final Map<Integer, Orientation> orientations = new HashMap<>();

    public OrientationRotations(Orientation dg0, Orientation dg90, Orientation dg180, Orientation dg270) {
        orientations.put(DG_0, dg0);
        orientations.put(DG_90, dg90);
        orientations.put(DG_180, dg180);
        orientations.put(DG_270, dg270);
    }

    @Override
    public Orientation rotate(int degrees) {
        int normalizedDegrees = degrees % 360;
        if (normalizedDegrees < 0) {
            normalizedDegrees = 360 + normalizedDegrees;
        }
        return orientations.get(normalizedDegrees);
    }
}
