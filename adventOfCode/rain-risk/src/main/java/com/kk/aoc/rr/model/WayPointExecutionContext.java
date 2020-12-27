package com.kk.aoc.rr.model;

import com.kk.aoc.rr.model.move.MoveVector;
import com.kk.aoc.rr.model.move.PositionRotations;
import com.kk.aoc.rr.model.move.SimpleMoveVector;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WayPointExecutionContext extends SimpleExecutionContext {
    private Position wayPoint;
    private final PositionRotations wayPointRotations = new PositionRotations(
            position -> position,
            position -> Position.builder().horizontal(position.getVertical()).vertical(-1 * position.getHorizontal()).build(),
            position -> Position.builder().horizontal(-1 * position.getHorizontal()).vertical(-1 * position.getVertical()).build(),
            position -> Position.builder().horizontal(-1 * position.getVertical()).vertical(position.getHorizontal()).build());

    public WayPointExecutionContext(Orientation initialOrientation, Position wayPoint) {
        super(initialOrientation);
        this.wayPoint = wayPoint;
    }

    @Override
    public void moveForward(int units) {
        for (int i = 0; i < units; i++) {
            moveByVector(new SimpleMoveVector(wayPoint));
        }
    }

    @Override
    public void moveToTheNorth(int units) {
        moveWayPointByVector(new SimpleMoveVector(Position.builder().vertical(units).horizontal(0).build()));
    }

    @Override
    public void moveToTheSouth(int units) {
        moveWayPointByVector(new SimpleMoveVector(Position.builder().vertical(-1 * units).horizontal(0).build()));
    }

    @Override
    public void moveToTheEast(int units) {
        moveWayPointByVector(new SimpleMoveVector(Position.builder().vertical(0).horizontal(units).build()));
    }

    @Override
    public void moveToTheWest(int units) {
        moveWayPointByVector(new SimpleMoveVector(Position.builder().vertical(0).horizontal(-1 * units).build()));
    }

    @Override
    public void rotate(Side side, int inputDegrees) {
        int degree = side.getDegreesFactor() * inputDegrees;
        wayPoint = wayPointRotations.rotate(degree).apply(wayPoint);
    }

    public void moveWayPointByVector(MoveVector move) {
        wayPoint = Position.builder().horizontal(wayPoint.getHorizontal() + move.getVector().getHorizontal()).vertical(wayPoint.getVertical() + move.getVector().getVertical()).build();
    }
}
