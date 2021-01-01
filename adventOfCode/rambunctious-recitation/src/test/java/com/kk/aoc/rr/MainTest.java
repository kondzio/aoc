package com.kk.aoc.rr;

import com.kk.aoc.rr.game.RrGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MainTest {
    @Test
    public void samplePart1Test() {
        RrGame game = new RrGame(List.of(0, 3, 6));
        Assertions.assertEquals(3, game.nextNumber());
        Assertions.assertEquals(3, game.nextNumber());
        Assertions.assertEquals(1, game.nextNumber());
        Assertions.assertEquals(0, game.nextNumber());
        Assertions.assertEquals(4, game.nextNumber());
        Assertions.assertEquals(0, game.nextNumber());

        Assertions.assertEquals(436, game.nextNumber(2020 - 1));
    }

    @Test
    public void samplePart2Test1() {
        RrGame game = new RrGame(List.of(0, 3, 6));
        Assertions.assertEquals(175594, game.nextNumber(30000000 - 1));
    }

    @Test
    public void samplePart2Test2() {
        RrGame game = new RrGame(List.of(1, 3, 2));
        Assertions.assertEquals(2578, game.nextNumber(30000000 - 1));
    }

    @Test
    public void samplePart2Test3() {
        RrGame game = new RrGame(List.of(2, 1, 3));
        Assertions.assertEquals(3544142, game.nextNumber(30000000 - 1));
    }

    @Test
    public void samplePart2Test4() {
        RrGame game = new RrGame(List.of(1, 2, 3));
        Assertions.assertEquals(261214, game.nextNumber(30000000 - 1));
    }

    @Test
    public void samplePart2Test5() {
        RrGame game = new RrGame(List.of(2, 3, 1));
        Assertions.assertEquals(6895259, game.nextNumber(30000000 - 1));
    }

    @Test
    public void samplePart2Test6() {
        RrGame game = new RrGame(List.of(3, 2, 1));
        Assertions.assertEquals(18, game.nextNumber(30000000 - 1));
    }

    @Test
    public void samplePart2Test7() {
        RrGame game = new RrGame(List.of(3, 1, 2));
        Assertions.assertEquals(362, game.nextNumber(30000000 - 1));
    }

    @Test
    public void part1Test() {
        RrGame game = new RrGame(List.of(16, 11, 15, 0, 1, 7));
        Assertions.assertEquals(662, game.nextNumber(2020 - 1));
    }

    @Test
    public void part2Test() {
        RrGame game = new RrGame(List.of(16, 11, 15, 0, 1, 7));
        Assertions.assertEquals(37312, game.nextNumber(30000000 - 1));
    }
}
