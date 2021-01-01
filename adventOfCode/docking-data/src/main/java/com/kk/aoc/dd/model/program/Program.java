package com.kk.aoc.dd.model.program;

import com.kk.aoc.dd.model.command.Command;
import com.kk.aoc.dd.model.command.MaskSetCommand;
import com.kk.aoc.dd.model.command.MemorySetCommand;
import com.kk.aoc.dd.model.instruction.Instruction;
import com.kk.aoc.dd.model.instruction.InstructionType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Program {
    private Map<InstructionType, Command> commandRegistry = new EnumMap<>(InstructionType.class);
    private final List<Instruction> instructions;

    public Program(List<Instruction> instructions) {
        this.instructions = instructions;
        commandRegistry.put(InstructionType.MASK_SET, new MaskSetCommand());
        commandRegistry.put(InstructionType.MEMORY_SET, new MemorySetCommand());
    }

    public void execute(ExecutionContext context) {
        for (Instruction instruction : instructions) {
            commandRegistry.get(instruction.getType()).execute(context, instruction.getArguments());
        }
    }
}
