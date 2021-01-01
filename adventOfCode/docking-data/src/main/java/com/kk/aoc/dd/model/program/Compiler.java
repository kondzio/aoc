package com.kk.aoc.dd.model.program;

import com.kk.aoc.dd.model.instruction.Instruction;
import com.kk.aoc.dd.model.instruction.InstructionType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compiler {
    public static final Pattern MASK_SETUP_PATTERN = Pattern.compile("^(mask = )(.*)$");
    public static final Pattern MEMORY_SETUP_PATTERN = Pattern.compile("^(mem\\[)(\\d*)(] = )(\\d*)$");

    public Program compile(List<String> code) {
        List<Instruction> instructions = new ArrayList<>();
        for (String lineOfCode : code) {
            Matcher maskSetupMatcher = MASK_SETUP_PATTERN.matcher(lineOfCode);
            Matcher memorySetupMatcher = MEMORY_SETUP_PATTERN.matcher(lineOfCode);
            if (maskSetupMatcher.find()) {
                String mask = maskSetupMatcher.group(2);
                instructions.add(new Instruction(InstructionType.MASK_SET, new Object[]{mask}));
            } else if (memorySetupMatcher.find()) {
                long address = Long.parseLong(memorySetupMatcher.group(2));
                long value = Long.parseLong(memorySetupMatcher.group(4));
                instructions.add(new Instruction(InstructionType.MEMORY_SET, new Object[]{address, value}));
            } else {
                throw new IllegalArgumentException(String.format("Line: '%s' is not recognized", lineOfCode));
            }
        }
        return new Program(instructions);
    }
}
