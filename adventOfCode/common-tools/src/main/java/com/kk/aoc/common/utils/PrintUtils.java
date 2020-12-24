package com.kk.aoc.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PrintUtils {
    public static <T> String toString(T[][] array, int desiredNumOfRows, int desiredNumOfCols) {
        StringBuilder builder = new StringBuilder();
        if (array != null) {
            int numOfRows = Math.min(array[0].length, desiredNumOfRows);
            int numOfCols = Math.min(array.length, desiredNumOfCols);

            for (int row = 0; row < numOfRows; row++) {
                for (int col = 0; col < numOfCols; col++) {
                    builder.append(array[row][col].toString());
                    if (col < numOfCols - 1) {
                        builder.append(" | ");
                    }
                }
                if (row < numOfRows - 1) {
                    builder.append("\n");
                }
            }
        }
        return builder.toString();
    }
}
