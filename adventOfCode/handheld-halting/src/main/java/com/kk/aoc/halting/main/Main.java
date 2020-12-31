package com.kk.aoc.halting.main;

import com.kk.aoc.common.LineByLineFileReader;
import com.kk.aoc.halting.program.Compiler;
import com.kk.aoc.halting.program.Operation;
import com.kk.aoc.halting.program.ProgramContext;
import com.kk.aoc.halting.program.Program;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        LineByLineFileReader lineByLineFileReader = LineByLineFileReader.builder().separator("\\s").inputFile(new File("src/main/resources/day8/input.txt")).build();
        lineByLineFileReader.open();
        List<Operation> operations = new ArrayList<>();
        Compiler compiler = new Compiler();
        while (lineByLineFileReader.hasNext()) {
            String[] code = lineByLineFileReader.next();
            if (code != null) {
                operations.add(compiler.compile(code));
            }
        }

        Operation[] instructions = new Operation[operations.size()];
        operations.toArray(instructions);
        Program program = new Program(instructions);
        program.execute(ProgramContext.builder().build());
    }
}
