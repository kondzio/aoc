package com.kk.aoc.cc.model;

import com.google.common.base.Preconditions;
import lombok.Getter;

@Getter
public final class Conway {
    private final CubeState[][][] state;
    private final int xSize;
    private final int ySize;
    private final int zSize;

    public Conway(int xSize, int ySize, int zSize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.zSize = zSize;

        state = new CubeState[zSize][ySize][xSize];
        for (int i = 0; i < zSize; i++) {
            for (int j = 0; j < ySize; j++) {
                for (int k = 0; k < xSize; k++) {
                    put(k, j, i, CubeState.INACTIVE);
                }
            }
        }
    }

    public CubeState getByPosition(int x, int y, int z) {
        checkIfIsValidPosition(x, y, z);
        return state[z][y][x];
    }

    public CubeState[][][] getWithSurrounding(int x, int y, int z) {
        checkIfIsValidPosition(x, y, z);

        CubeState[][][] surrounding = new CubeState[3][3][3];
        int insertX = 0;
        int insertY = 0;
        int insertZ = 0;
        int xFrom = x - 1;
        int xTo = x + 1;
        int yFrom = y - 1;
        int yTo = y + 1;
        int zFrom = y - 1;
        int zTo = y + 1;

        if (xFrom < 0) {
            for (int i = 0; i < 3; i++) {
                surrounding[0][0][i] = CubeState.INACTIVE;
            }
            xFrom = x;
            insertX = 1;
        }
        if (xTo >= getXSize()) {
            for (int i = 0; i < 3; i++) {
                surrounding[2][2][i] = CubeState.INACTIVE;
            }
            xTo = x;
        }

        if (yFrom < 0) {
            for (int i = 0; i < 3; i++) {
                surrounding[0][i][0] = CubeState.INACTIVE;
            }
            yFrom = y;
            insertY = 1;
        }
        if (yTo >= getYSize()) {
            for (int i = 0; i < 3; i++) {
                surrounding[2][i][2] = CubeState.INACTIVE;
            }
            yTo = y;
        }

        if (zFrom < 0) {
            for (int i = 0; i < 3; i++) {
                surrounding[i][0][0] = CubeState.INACTIVE;
            }
            zFrom = z;
            insertZ = 1;
        }
        if (zTo >= getZSize()) {
            for (int i = 0; i < 3; i++) {
                surrounding[i][2][2] = CubeState.INACTIVE;
            }
            zTo = z;
        }

        for (int i = 0; i < zTo - zFrom + 1; i++) {
            for (int j = 0; j < yTo - yFrom + 1; j++) {
                for (int k = 0; k < xTo - xFrom + 1; k++) {
                    surrounding[insertZ + i][insertY + j][insertX + k] = getState()[zFrom + i][yFrom + j][xFrom + k];
                }
            }
        }
        return surrounding;
    }

    public void put(int x, int y, int z, CubeState cubeState) {
        checkIfIsValidPosition(x, y, z);
        Preconditions.checkNotNull(state);
        state[z][y][x] = cubeState;
    }

    protected void checkIfIsValidPosition(int x, int y, int z) {
        Preconditions.checkState(x >= 0 && x < xSize, String.format("X position: '%s' exceeds max: '%s'", x, xSize));
        Preconditions.checkState(y >= 0 && y < ySize, String.format("Y position: '%s' exceeds max '%s'", y, ySize));
        Preconditions.checkState(z >= 0 && z < zSize, String.format("Z position: '%s' exceeds max '%s'", z, zSize));
    }
}
