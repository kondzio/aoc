package com.kk.aoc.ss.model;

import com.google.common.base.Preconditions;

public abstract class Lobby {
    private final int width;
    private final int length;
    private SeatState[][] lobbyMatrix;

    public Lobby(int width, int length) {
        this.width = width;
        this.length = length;
        this.lobbyMatrix = new SeatState[length][width];
    }

    public void put(int x, int y, SeatState state) {
        checkIfIsValidPosition(x, y);
        lobbyMatrix[y][x] = state;
    }

    public SeatState getByPosition(int x, int y) {
        checkIfIsValidPosition(x, y);
        return lobbyMatrix[y][x];
    }

    protected void checkIfIsValidPosition(int x, int y) {
        Preconditions.checkState(x >= 0 && x < width, String.format("X position: '%s' exceeds max: '%s'", x, width));
        Preconditions.checkState(y >= 0 && y < length, String.format("Y position: '%s' exceeds max '%s'", y, length));
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public SeatState[][] getLobbyMatrix() {
        return lobbyMatrix;
    }

    public abstract SeatState[][] getWithSurrounding(int x, int y);

    public abstract Lobby newLobby(int width, int length);
}
