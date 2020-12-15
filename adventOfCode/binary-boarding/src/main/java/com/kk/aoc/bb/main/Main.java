package com.kk.aoc.bb.main;

import com.kk.aoc.bb.model.Ticket;
import com.kk.aoc.bb.scaner.BinaryCodeTicketScanner;
import com.kk.aoc.bb.scaner.TicketScanner;
import com.kk.aoc.bb.scaner.TicketUtils;
import com.kk.aoc.common.LineByLineReader;
import lombok.Builder;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        boolean[][] seatsOccupation = new boolean[128][8];
        TicketScanner ticketScanner = new BinaryCodeTicketScanner(7, 3);
        LineByLineReader lineByLineReader = LineByLineReader.builder().separator("\\s").inputFile(new File("src/main/resources/day5/input.txt")).build();
        lineByLineReader.open();
        int maxId = Integer.MIN_VALUE;
        while (lineByLineReader.hasNext()) {
            String[] tokens = lineByLineReader.next();
            if (tokens != null && tokens.length > 0) {
                String code = tokens[0];
                if (code != null && !code.isEmpty()) {
                    Ticket ticket = ticketScanner.scan(code);
                    seatsOccupation[ticket.getRowNum()][ticket.getSeatNum()] = true;
                    maxId = Math.max(maxId, ticket.getSeatId());
                }
            }
        }


        for (int i = 0; i < 128; i++) {
            boolean hasSingleGap = false;
            int gapSeatNum = -1;
            for (int j = 0; j < 8; j++) {
                if (!seatsOccupation[i][j]) {
                    if (hasSingleGap) {
                        hasSingleGap = false;
                        break;
                    }
                    hasSingleGap = true;
                    gapSeatNum = j;
                }
            }
            if (hasSingleGap) {
                System.err.println(String.format("Row '%s' seat '%s', seatId '%s'", i, gapSeatNum, TicketUtils.calculateSeatId(i, gapSeatNum)));
            }
        }
    }

    @Builder
    static class TicketBoardingState {
        private Ticket ticket;
        private boolean boarded;
    }
}
