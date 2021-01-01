package com.kk.aoc.dd.model.instruction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public final class Instruction {
    private final InstructionType type;
    private final Object[] arguments;
}
