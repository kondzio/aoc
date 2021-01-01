package com.kk.aoc.dd.model.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BitmapUtils {
    public static long sumUpValues(Map<Long, Long> bitmap) {
        return bitmap.values().stream().reduce(0L, Long::sum);
    }
}
