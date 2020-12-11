package com.kk.aoc.bb.scaner;

import com.kk.aoc.bb.model.CodeSign;
import com.kk.aoc.bb.model.Ticket;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BinaryCodeTicketScanner implements TicketScanner {
    private final int rowCodeLength;
    private final int seatCodeLength;

    @Override
    public Ticket scan(String code) {
        byte rowNum = 0;
        String rowCode = code.substring(0, rowCodeLength);
        String seatCode = code.substring(rowCodeLength);
        for (int i = 0; i < rowCodeLength; i++) {
            if (CodeSign.B == CodeSign.valueFrom(rowCode.charAt(i))) {
                rowNum |= 1 << (rowCodeLength - i - 1);
            }
        }

        byte seatNum = 0;
        for (int j = 0; j < seatCodeLength; j++) {
            if (CodeSign.R == CodeSign.valueFrom(seatCode.charAt(j))) {
                seatNum |= 1 << (seatCodeLength - j - 1);
            }
        }
        return Ticket.builder().rowNum(rowNum).seatNum(seatNum).seatId(TicketUtils.calculateSeatId(rowNum, seatNum)).code(code).build();
    }
}
