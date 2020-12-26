package com.kk.aoc.rr.model;

import lombok.Getter;
import lombok.ToString;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static com.kk.aoc.rr.model.Orientation.*;

@Getter
@ToString
public class ExecutionContext {
    private Orientation currentOrientation;
    private Position currentPosition = Position.builder().horizontal(0).vertical(0).build();
    private final Map<Orientation, Consumer<Integer>> relativeMoves = new EnumMap<>(Orientation.class);
    private final Map<Orientation, Rotations> rotations = new EnumMap<>(Orientation.class);


    public ExecutionContext(Orientation initialOrientation) {
        currentOrientation = initialOrientation;

        relativeMoves.put(NORTH, this::moveToTheNorth);
        relativeMoves.put(SOUTH, this::moveToTheSouth);
        relativeMoves.put(EAST, this::moveToTheEast);
        relativeMoves.put(WEST, this::moveToTheWest);

        rotations.put(NORTH, new Rotations(NORTH, EAST, SOUTH, WEST));
        rotations.put(SOUTH, new Rotations(SOUTH, WEST, NORTH, EAST));
        rotations.put(EAST, new Rotations(EAST, SOUTH, WEST, NORTH));
        rotations.put(WEST, new Rotations(WEST, NORTH, EAST, SOUTH));
    }

    public void changeOrientation(Orientation orientation) {
        currentOrientation = orientation;
    }

    public void moveToTheNorth(int units) {
        currentPosition = Position.builder().vertical(currentPosition.getVertical() + units).horizontal(currentPosition.getHorizontal()).build();
    }

    public void moveToTheSouth(int units) {
        currentPosition = Position.builder().vertical(currentPosition.getVertical() - units).horizontal(currentPosition.getHorizontal()).build();
    }

    public void moveToTheEast(int units) {
        currentPosition = Position.builder().vertical(currentPosition.getVertical()).horizontal(currentPosition.getHorizontal() + units).build();
    }

    public void moveToTheWest(int units) {
        currentPosition = Position.builder().vertical(currentPosition.getVertical()).horizontal(currentPosition.getHorizontal() - units).build();
    }

    public void moveForward(int units) {
        relativeMoves.get(currentOrientation).accept(units);
    }

    public void rotate(Side side, int inputDegrees) {
        int degrees = side.getDegreesFactor() * inputDegrees;
        currentOrientation = rotations.get(currentOrientation).rotate(degrees);
    }

    private static class Rotations {
        public static final int DG_0 = 0;
        public static final int DG_90 = 90;
        public static final int DG_180 = 180;
        public static final int DG_270 = 270;
        private final Map<Integer, Orientation> orientations = new HashMap<>();

        public Rotations(Orientation dg0, Orientation dg90, Orientation dg180, Orientation dg270) {
            orientations.put(DG_0, dg0);
            orientations.put(DG_90, dg90);
            orientations.put(DG_180, dg180);
            orientations.put(DG_270, dg270);
        }

        public Orientation rotate(int degrees) {
            int normalizedDegrees = degrees % 360;
            if (normalizedDegrees < 0) {
                normalizedDegrees = 360 + normalizedDegrees;
            }
            return orientations.get(normalizedDegrees);
        }
    }
}
