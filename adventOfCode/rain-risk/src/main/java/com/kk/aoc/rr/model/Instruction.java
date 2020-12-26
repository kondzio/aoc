package com.kk.aoc.rr.model;

import com.kk.aoc.rr.model.action.ActionType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Instruction {
    private final ActionType actionType;
    private final int arg;
}
