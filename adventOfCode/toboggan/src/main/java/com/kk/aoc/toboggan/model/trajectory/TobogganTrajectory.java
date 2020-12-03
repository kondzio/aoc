package com.kk.aoc.toboggan.model.trajectory;

import com.kk.aoc.toboggan.model.map.Coordinates;
import lombok.NonNull;

public class TobogganTrajectory extends AbstractTrajectory {
    private final int numberOfJumps;
    private final Jump<Coordinates> jump;
    private int jumpCount = 0;
    private Coordinates latestCoordinates;

    public TobogganTrajectory(@NonNull Coordinates startingPoint, int numberOfJumps, @NonNull Jump<Coordinates> jump) {
        super(startingPoint);
        this.numberOfJumps = numberOfJumps;
        this.jump = jump;
        this.latestCoordinates = startingPoint;
    }

    @Override
    public boolean hasNext() {
        return jumpCount <= numberOfJumps;
    }

    @Override
    public Coordinates next() {
        latestCoordinates = jump.next(latestCoordinates);
        jumpCount++;
        return latestCoordinates;
    }

    public interface Jump<T> {
        T next(T from);
    }
}
