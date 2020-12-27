package com.kk.aoc.rr.model;

import com.kk.aoc.rr.model.move.MoveVector;

public interface ExecutionContext {
    void changeOrientation(Orientation orientation);

    void moveToTheNorth(int units);

    void moveToTheSouth(int units);

    void moveToTheEast(int units);

    void moveToTheWest(int units);

    void moveForward(int units);

    void moveByVector(MoveVector vector);

    void rotate(Side side, int inputDegrees);
}
