package com.kk.aoc.cc.utils;

import com.kk.aoc.cc.model.CubeState;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CubeUtils {
    public static int calculateCubesInState(CubeState[][][] matrix, CubeState state) {
        int counter = 0;
        int zSize = matrix.length;
        int ySize = matrix[0].length;
        int xSize = matrix[0][0].length;
        for (int z = 0; z < zSize; z++) {
            for (int y = 0; y < ySize; y++) {
                for (int x = 0; x < xSize; x++) {
                    if (matrix[z][y][x] == state) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }
}
