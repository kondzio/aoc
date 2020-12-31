package com.kk.aoc.rr;


import com.kk.aoc.common.LineByLineFileReader;
import com.kk.aoc.rr.model.*;
import com.kk.aoc.rr.model.action.Action;
import com.kk.aoc.rr.model.action.ActionRegistry;
import com.kk.aoc.rr.model.action.ActionType;
import com.kk.aoc.rr.model.move.PositionRotations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

    private List<Instruction> initialize(String path) throws FileNotFoundException {
        LineByLineFileReader lineByLineFileReader = LineByLineFileReader.builder().separator("\\s").inputFile(new File(path)).build();
        lineByLineFileReader.open();
        List<Instruction> instructions = new ArrayList<>();
        while (lineByLineFileReader.hasNext()) {
            String[] tokens = lineByLineFileReader.next();
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
    public void sample1Test() throws FileNotFoundException {
        SimpleExecutionContext context = new SimpleExecutionContext(Orientation.EAST);
        runTest(context, initialize("src/test/resources/input.txt"));

        Assertions.assertEquals(Orientation.SOUTH, context.getCurrentOrientation());
        Assertions.assertEquals(17, Math.abs(context.getCurrentPosition().getHorizontal()));
        Assertions.assertEquals(8, Math.abs(context.getCurrentPosition().getVertical()));
    }

    @Test
    public void part1Test() throws FileNotFoundException {
        SimpleExecutionContext context = new SimpleExecutionContext(Orientation.EAST);
        runTest(context, initialize("D:\\poligon\\aoc\\adventOfCode\\src\\main\\resources\\day12\\input.txt"));
    }

    @Test
    public void sample2Test() throws FileNotFoundException {
        WayPointExecutionContext context = new WayPointExecutionContext(Orientation.EAST, Position.builder().horizontal(10).vertical(1).build());
        runTest(context, initialize("src/test/resources/input.txt"));

        Assertions.assertEquals(Orientation.EAST, context.getCurrentOrientation());
        Assertions.assertEquals(214, Math.abs(context.getCurrentPosition().getHorizontal()));
        Assertions.assertEquals(72, Math.abs(context.getCurrentPosition().getVertical()));

        Assertions.assertEquals(4, Math.abs(context.getWayPoint().getHorizontal()));
        Assertions.assertEquals(10, Math.abs(context.getWayPoint().getVertical()));
    }

    @Test
    public void part2Test() throws FileNotFoundException {
        WayPointExecutionContext context = new WayPointExecutionContext(Orientation.EAST, Position.builder().horizontal(10).vertical(1).build());
        runTest(context, initialize("D:\\poligon\\aoc\\adventOfCode\\src\\main\\resources\\day12\\input.txt"));
        System.err.println(context);
    }

    @Test
    public void party2MovesTest() {
        Position wayPoint = Position.builder().horizontal(10).vertical(1).build();
        WayPointExecutionContext context = new WayPointExecutionContext(Orientation.EAST, wayPoint);

        context.moveForward(5);
        Assertions.assertEquals(50, context.getCurrentPosition().getHorizontal());
        Assertions.assertEquals(5, context.getCurrentPosition().getVertical());

        context.moveToTheWest(20);
        Assertions.assertEquals(-10, context.getWayPoint().getHorizontal());
        Assertions.assertEquals(1, context.getWayPoint().getVertical());

        context.moveToTheSouth(2);
        Assertions.assertEquals(-10, context.getWayPoint().getHorizontal());
        Assertions.assertEquals(-1, context.getWayPoint().getVertical());

        context.moveToTheEast(10);
        Assertions.assertEquals(0, context.getWayPoint().getHorizontal());
        Assertions.assertEquals(-1, context.getWayPoint().getVertical());

        context.moveToTheNorth(1);
        Assertions.assertEquals(0, context.getWayPoint().getHorizontal());
        Assertions.assertEquals(0, context.getWayPoint().getVertical());

        context.moveForward(100000);
        Assertions.assertEquals(50, context.getCurrentPosition().getHorizontal());
        Assertions.assertEquals(5, context.getCurrentPosition().getVertical());
    }

    @Test
    public void part2RotationTest() {
        Position wayPoint = Position.builder().horizontal(10).vertical(1).build();
        WayPointExecutionContext context = new WayPointExecutionContext(Orientation.EAST, wayPoint);
        context.rotate(Side.RIGHT, PositionRotations.DG_90);

        Assertions.assertEquals(1, context.getWayPoint().getHorizontal());
        Assertions.assertEquals(-10, context.getWayPoint().getVertical());

        context.rotate(Side.RIGHT, PositionRotations.DG_90);

        Assertions.assertEquals(-10, context.getWayPoint().getHorizontal());
        Assertions.assertEquals(-1, context.getWayPoint().getVertical());

        context.rotate(Side.RIGHT, PositionRotations.DG_90);
        Assertions.assertEquals(-1, context.getWayPoint().getHorizontal());
        Assertions.assertEquals(10, context.getWayPoint().getVertical());

        context.rotate(Side.RIGHT, PositionRotations.DG_90);
        Assertions.assertEquals(10, context.getWayPoint().getHorizontal());
        Assertions.assertEquals(1, context.getWayPoint().getVertical());

        context.rotate(Side.LEFT, PositionRotations.DG_90);
        Assertions.assertEquals(-1, context.getWayPoint().getHorizontal());
        Assertions.assertEquals(10, context.getWayPoint().getVertical());

        context.rotate(Side.LEFT, PositionRotations.DG_90);
        Assertions.assertEquals(-10, context.getWayPoint().getHorizontal());
        Assertions.assertEquals(-1, context.getWayPoint().getVertical());

        context.rotate(Side.LEFT, PositionRotations.DG_90);
        Assertions.assertEquals(1, context.getWayPoint().getHorizontal());
        Assertions.assertEquals(-10, context.getWayPoint().getVertical());

        context.rotate(Side.LEFT, PositionRotations.DG_90);
        Assertions.assertEquals(10, context.getWayPoint().getHorizontal());
        Assertions.assertEquals(1, context.getWayPoint().getVertical());

        context.rotate(Side.RIGHT, PositionRotations.DG_180);
        Assertions.assertEquals(-10, context.getWayPoint().getHorizontal());
        Assertions.assertEquals(-1, context.getWayPoint().getVertical());

        context.rotate(Side.RIGHT, PositionRotations.DG_180);
        Assertions.assertEquals(10, context.getWayPoint().getHorizontal());
        Assertions.assertEquals(1, context.getWayPoint().getVertical());

        context.rotate(Side.LEFT, PositionRotations.DG_180);
        Assertions.assertEquals(-10, context.getWayPoint().getHorizontal());
        Assertions.assertEquals(-1, context.getWayPoint().getVertical());

        context.rotate(Side.LEFT, PositionRotations.DG_180);
        Assertions.assertEquals(10, context.getWayPoint().getHorizontal());
        Assertions.assertEquals(1, context.getWayPoint().getVertical());

        context.rotate(Side.RIGHT, PositionRotations.DG_270);
        Assertions.assertEquals(-1, context.getWayPoint().getHorizontal());
        Assertions.assertEquals(10, context.getWayPoint().getVertical());
    }

}
