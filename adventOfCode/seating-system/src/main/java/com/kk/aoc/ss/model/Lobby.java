package com.kk.aoc.ss.model;


import com.google.common.base.Preconditions;

public class Lobby {
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

    private void checkIfIsValidPosition(int x, int y) {
        Preconditions.checkState(x >= 0 && x < width, String.format("X position: '%s' exceeds max: '%s'", x, width));
        Preconditions.checkState(y >= 0 && y < length, String.format("Y position: '%s' exceeds max '%s'", y, length));
    }

    public SeatState[][] getWithSurrounding(int x, int y) {
        checkIfIsValidPosition(x, y);

        SeatState[][] surrounding = new SeatState[3][3];
        int insertX = 0;
        int insertY = 0;
        int xFrom = x - 1;
        int xTo = x + 1;
        int yFrom = y - 1;
        int yTo = y + 1;

        if (xFrom < 0) {
            for (int i = 0; i < 3; i++) {
                surrounding[i][0] = SeatState.FLOOR;
            }
            xFrom = x;
            insertX = 1;
        }
        if (xTo >= width) {
            for (int i = 0; i < 3; i++) {
                surrounding[i][2] = SeatState.FLOOR;
            }
            xTo = x;
        }

        if (yFrom < 0) {
            for (int i = 0; i < 3; i++) {
                surrounding[0][i] = SeatState.FLOOR;
            }
            yFrom = y;
            insertY = 1;
        }
        if (yTo >= length) {
            for (int i = 0; i < 3; i++) {
                surrounding[2][i] = SeatState.FLOOR;
            }
            yTo = y;
        }

        for (int i = 0; i < yTo - yFrom + 1; i++) {
            for (int j = 0; j < xTo - xFrom + 1; j++) {
                surrounding[insertY + i][insertX + j] = lobbyMatrix[yFrom + i][xFrom + j];
            }
        }
        return surrounding;
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
}
