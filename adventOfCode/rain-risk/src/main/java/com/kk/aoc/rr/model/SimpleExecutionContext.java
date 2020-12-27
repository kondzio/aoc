package com.kk.aoc.rr.model;

import com.kk.aoc.rr.model.move.MoveVector;
import com.kk.aoc.rr.model.move.OrientationRotations;
import com.kk.aoc.rr.model.move.SimpleMoveVector;
import lombok.Getter;
import lombok.ToString;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

import static com.kk.aoc.rr.model.Orientation.*;

@Getter
@ToString
public class SimpleExecutionContext implements ExecutionContext {
    private Orientation currentOrientation;
    private Position currentPosition;
    private final Map<Orientation, Consumer<Integer>> relativeMoves = new EnumMap<>(Orientation.class);
    private final Map<Orientation, OrientationRotations> rotations = new EnumMap<>(Orientation.class);

    public SimpleExecutionContext(Orientation initialOrientation) {
        this(initialOrientation, Position.builder().horizontal(0).vertical(0).build());
    }

    public SimpleExecutionContext(Orientation initialOrientation, Position position) {
        currentOrientation = initialOrientation;
        currentPosition = position;

        relativeMoves.put(NORTH, this::moveToTheNorth);
        relativeMoves.put(SOUTH, this::moveToTheSouth);
        relativeMoves.put(EAST, this::moveToTheEast);
        relativeMoves.put(WEST, this::moveToTheWest);

        rotations.put(NORTH, new OrientationRotations(NORTH, EAST, SOUTH, WEST));
        rotations.put(SOUTH, new OrientationRotations(SOUTH, WEST, NORTH, EAST));
        rotations.put(EAST, new OrientationRotations(EAST, SOUTH, WEST, NORTH));
        rotations.put(WEST, new OrientationRotations(WEST, NORTH, EAST, SOUTH));
    }

    @Override
    public void changeOrientation(Orientation orientation) {
        currentOrientation = orientation;
    }

    @Override
    public void moveToTheNorth(int units) {
        moveByVector(new SimpleMoveVector(Position.builder().vertical(units).horizontal(0).build()));

    }

    @Override
    public void moveToTheSouth(int units) {
        moveByVector(new SimpleMoveVector(Position.builder().vertical(-1 * units).horizontal(0).build()));

    }

    @Override
    public void moveToTheEast(int units) {
        moveByVector(new SimpleMoveVector(Position.builder().vertical(0).horizontal(units).build()));
    }

    @Override
    public void moveToTheWest(int units) {
        moveByVector(new SimpleMoveVector(Position.builder().vertical(0).horizontal(-1 * units).build()));
    }

    @Override
    public void moveByVector(MoveVector move) {
        currentPosition = Position.builder().vertical(currentPosition.getVertical() + move.getVector().getVertical()).horizontal(currentPosition.getHorizontal() + move.getVector().getHorizontal()).build();

    }

    @Override
    public void moveForward(int units) {
        relativeMoves.get(currentOrientation).accept(units);
    }

    @Override
    public void rotate(Side side, int inputDegrees) {
        int degrees = side.getDegreesFactor() * inputDegrees;
        currentOrientation = rotations.get(currentOrientation).rotate(degrees);
    }
}
