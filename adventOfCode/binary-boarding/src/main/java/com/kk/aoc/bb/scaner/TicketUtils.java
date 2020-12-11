package com.kk.aoc.bb.scaner;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketUtils {
    public static int calculateSeatId(int rowNum, int seatNum) {
        return rowNum * 8 + seatNum;
    }
}
