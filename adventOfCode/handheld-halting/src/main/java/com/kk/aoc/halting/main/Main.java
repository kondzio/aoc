package com.kk.aoc.halting.main;

import com.kk.aoc.common.LineByLineReader;
import com.kk.aoc.halting.program.Compiler;
import com.kk.aoc.halting.program.Operation;
import com.kk.aoc.halting.program.OperationContext;
import com.kk.aoc.halting.program.Program;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        LineByLineReader lineByLineReader = LineByLineReader.builder().separator("\\s").inputFile(new File("src/main/resources/day8/input.txt")).build();
        lineByLineReader.open();
        List<Operation> operations = new ArrayList<>();
        Compiler compiler = new Compiler();
        while (lineByLineReader.hasNext()) {
            String[] code = lineByLineReader.next();
            if (code != null) {
                operations.add(compiler.compile(code));
            }
        }

        Operation[] instructions = new Operation[operations.size()];
        operations.toArray(instructions);
        Program program = new Program(instructions);
        program.execute(new OperationContext());
    }
}
