package com.kk.aoc.ss.utils;

import com.kk.aoc.ss.model.SeatState;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SeatUtils {
    public static int calculateNumberOf(SeatState[][] matrix, SeatState state) {
        int counter = 0;
        int width = matrix[0].length;
        for (int x = 0; x < width; x++) {
            for (SeatState[] seatStates : matrix) {
                if (seatStates[x] == state) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
