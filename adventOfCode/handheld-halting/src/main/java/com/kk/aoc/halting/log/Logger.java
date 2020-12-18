package com.kk.aoc.halting.log;

import com.kk.aoc.halting.program.Operation;
import com.kk.aoc.halting.program.ProgramContext;
import com.kk.aoc.halting.program.OperationType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Logger {
    public static void log(int position, Operation operation, ProgramContext context) {
        if (operation.getType() == OperationType.NOP || operation.getType() == OperationType.JMP) {
            System.err.println(position + ": " + operation + ": " + context);
        } else {
            System.out.println(position + ": " + operation + ": " + context);
        }
    }
}
