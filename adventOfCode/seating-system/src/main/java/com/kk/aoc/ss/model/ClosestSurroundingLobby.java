package com.kk.aoc.ss.model;


public class ClosestSurroundingLobby extends Lobby {

    public ClosestSurroundingLobby(int width, int length) {
        super(width, length);
    }

    @Override
    public Lobby newLobby(int width, int length) {
        return new ClosestSurroundingLobby(width, length);
    }

    @Override
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
        if (xTo >= getWidth()) {
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
        if (yTo >= getLength()) {
            for (int i = 0; i < 3; i++) {
                surrounding[2][i] = SeatState.FLOOR;
            }
            yTo = y;
        }

        for (int i = 0; i < yTo - yFrom + 1; i++) {
            for (int j = 0; j < xTo - xFrom + 1; j++) {
                surrounding[insertY + i][insertX + j] = getLobbyMatrix()[yFrom + i][xFrom + j];
            }
        }
        return surrounding;
    }
}
