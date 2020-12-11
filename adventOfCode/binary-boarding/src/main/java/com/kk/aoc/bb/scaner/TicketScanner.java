package com.kk.aoc.bb.scaner;

import com.kk.aoc.bb.model.Ticket;

public interface TicketScanner {
    Ticket scan(String code);
}
