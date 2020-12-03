package com.kk.aoc.toboggan.model.trajectory;

import com.kk.aoc.toboggan.model.map.Coordinates;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class AbstractTrajectory implements Trajectory {
    @NonNull
    private final Coordinates startingPoint;

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Coordinates next() {
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove is not supported by Trajectory");
    }

    @Override
    public void forEachRemaining(Consumer<? super Coordinates> action) {
        //noop
    }
}
