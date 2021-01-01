package com.kk.aoc.dd;

import com.kk.aoc.common.LineByLineFileReader;
import com.kk.aoc.common.LineByLineReader;
import com.kk.aoc.common.LineByLineStringReader;
import com.kk.aoc.dd.model.address.AddressTree;
import com.kk.aoc.dd.model.program.Compiler;
import com.kk.aoc.dd.model.program.*;
import com.kk.aoc.dd.model.utils.BitmapUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class MainTest {
    private Input initialize(LineByLineReader lineByLineReader) throws FileNotFoundException {
        lineByLineReader.open();
        Input input = new Input();
        while (lineByLineReader.hasNext()) {
            String[] line = lineByLineReader.next();
            if (line != null) {
                input.addLineOfCode(line[0]);
            }
        }
        return input;
    }

    @Test
    public void part1Test() throws FileNotFoundException {
        Input input = initialize(LineByLineFileReader.builder().separator("\\n,").inputFile(new File("D:\\poligon\\aoc\\adventOfCode\\src\\main\\resources\\day14\\input.txt")).build());
        Compiler compiler = new Compiler();
        Program program = compiler.compile(input.getCode());
        PlainAddressExecutionContext context = new PlainAddressExecutionContext();
        program.execute(context);
        Assertions.assertEquals(8471403462063L, BitmapUtils.sumUpValues(context.getBitmap()));
    }

    @Test
    public void part2Test() throws FileNotFoundException {
        Input input = initialize(LineByLineFileReader.builder().separator("\\n,").inputFile(new File("D:\\poligon\\aoc\\adventOfCode\\src\\main\\resources\\day14\\input.txt")).build());
        Compiler compiler = new Compiler();
        Program program = compiler.compile(input.getCode());
        ExecutionContext context = new FloatingAddressExecutionContext();
        program.execute(context);
        Assertions.assertEquals(2667858637669L, BitmapUtils.sumUpValues(context.getBitmap()));
    }

    @Test
    public void sample1Part2Test() throws FileNotFoundException {
        String inputString = "mask = 000000000000000000000000000000X1001X\n" +
                "mem[42] = 100\n" +
                "mask = 00000000000000000000000000000000X0XX\n" +
                "mem[26] = 1";
        Input input = initialize(LineByLineStringReader.builder().separator("\\n,").inputString(inputString).build());
        Compiler compiler = new Compiler();
        Program program = compiler.compile(input.getCode());
        FloatingAddressExecutionContext context = new FloatingAddressExecutionContext();
        program.execute(context);
        Assertions.assertEquals(100, context.getValue(58));
        Assertions.assertEquals(100, context.getValue(59));
        Assertions.assertEquals(1, context.getValue(16));
        Assertions.assertEquals(1, context.getValue(17));
        Assertions.assertEquals(1, context.getValue(18));
        Assertions.assertEquals(1, context.getValue(19));
        Assertions.assertEquals(1, context.getValue(24));
        Assertions.assertEquals(1, context.getValue(25));
        Assertions.assertEquals(1, context.getValue(26));
        Assertions.assertEquals(1, context.getValue(27));
        Assertions.assertEquals(208, BitmapUtils.sumUpValues(context.getBitmap()));
    }

    @Test
    public void sample1Part1Test() throws FileNotFoundException {
        String inputString = "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X\n" +
                "mem[8] = 11\n" +
                "mem[7] = 101\n" +
                "mem[8] = 0";
        Input input = initialize(LineByLineStringReader.builder().separator("\\n,").inputString(inputString).build());
        Compiler compiler = new Compiler();
        Program program = compiler.compile(input.getCode());
        PlainAddressExecutionContext context = new PlainAddressExecutionContext();
        program.execute(context);
        Assertions.assertEquals(64, context.getValue(8));
        Assertions.assertEquals(101, context.getValue(7));
        Assertions.assertEquals(165, BitmapUtils.sumUpValues(context.getBitmap()));
    }

    @Test
    public void memorySetupPatternTest() {
        String line = "mem[8] = 11";
        Matcher matcher = Compiler.MEMORY_SETUP_PATTERN.matcher(line);
        Assertions.assertTrue(matcher.find());
        Assertions.assertEquals("8", matcher.group(2));
        Assertions.assertEquals("11", matcher.group(4));
    }

    @Test
    public void addressTreeTest() {
        AddressTree tree = new AddressTree();
        tree.extendAddress('0', '1');
        tree.extendAddress('1');
        tree.extendAddress('0');
        tree.extendAddress('0');
        tree.extendAddress('1');
        tree.extendAddress('0', '1');

        List<Long> addresses = tree.collectAddresses().stream().map(s -> Long.parseLong(s, 2)).collect(Collectors.toList());
        Assertions.assertTrue(addresses.contains(18L));
        Assertions.assertTrue(addresses.contains(19L));
        Assertions.assertTrue(addresses.contains(50L));
        Assertions.assertTrue(addresses.contains(51L));
    }

    private static class Input {
        private final List<String> code = new ArrayList<>();

        public void addLineOfCode(String lineOfCode) {
            code.add(lineOfCode);
        }

        public List<String> getCode() {
            return code;
        }
    }
}
