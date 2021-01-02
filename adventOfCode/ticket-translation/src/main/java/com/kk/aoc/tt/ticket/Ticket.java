package com.kk.aoc.tt.ticket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
public class Ticket {
    private final List<Field<Integer>> fields;
}
