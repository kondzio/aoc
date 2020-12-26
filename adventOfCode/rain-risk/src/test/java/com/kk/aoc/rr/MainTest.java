package com.kk.aoc.rr;


import com.kk.aoc.common.LineByLineReader;
import com.kk.aoc.rr.model.ExecutionContext;
import com.kk.aoc.rr.model.Instruction;
import com.kk.aoc.rr.model.Orientation;
import com.kk.aoc.rr.model.action.Action;
import com.kk.aoc.rr.model.action.ActionRegistry;
import com.kk.aoc.rr.model.action.ActionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

    private List<Instruction> initialize(String path) throws FileNotFoundException {
        LineByLineReader lineByLineReader = LineByLineReader.builder().separator("\\s").inputFile(new File(path)).build();
        lineByLineReader.open();
        List<Instruction> instructions = new ArrayList<>();
        while (lineByLineReader.hasNext()) {
            String[] tokens = lineByLineReader.next();
            if (tokens != null) {
                String actionId = tokens[0].substring(0, 1);
                int arg = Integer.parseInt(tokens[0].substring(1));
                instructions.add(Instruction.builder().actionType(ActionType.fromValue(actionId)).arg(arg).build());
            }
        }
        return instructions;
    }

    private void runTest(ExecutionContext context, List<Instruction> instructions) {
        ActionRegistry registry = new ActionRegistry();
        for (Instruction instruction : instructions) {
            Action action = registry.getByType(instruction.getActionType()).orElseThrow(() -> new IllegalArgumentException(String.format("Action: '%s' is not supported", instruction.getActionType())));
            action.execute(context, instruction.getArg());
        }
    }

    @Test
    public void sampleTest() throws FileNotFoundException {
        ExecutionContext context = new ExecutionContext(Orientation.EAST);
        runTest(context, initialize("src/test/resources/input.txt"));

        Assertions.assertEquals(Orientation.SOUTH, context.getCurrentOrientation());
        Assertions.assertEquals(17, Math.abs(context.getCurrentPosition().getHorizontal()));
        Assertions.assertEquals(8, Math.abs(context.getCurrentPosition().getVertical()));
    }

    @Test
    public void mainTest() throws FileNotFoundException {
        ExecutionContext context = new ExecutionContext(Orientation.EAST);
        runTest(context, initialize("D:\\poligon\\aoc\\adventOfCode\\src\\main\\resources\\day12\\input.txt"));
    }

}
