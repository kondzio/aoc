package com.kk.aoc.ss.model;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
@EqualsAndHashCode
public class Connection implements Comparable<Connection> {
    private final int busNo;
    private final int delay;

    @Override
    public int compareTo(Connection connection) {
        return Integer.compare(this.busNo, connection.getBusNo());
    }


}
