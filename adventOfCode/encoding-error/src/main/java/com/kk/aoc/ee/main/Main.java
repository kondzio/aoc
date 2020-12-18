package com.kk.aoc.ee.main;

import com.kk.aoc.common.LineByLineReader;
import com.kk.aoc.ee.main.model.PreambleWindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        LineByLineReader lineByLineReader = LineByLineReader.builder().separator("\\s").inputFile(new File("src/main/resources/day9/input.txt")).build();
        lineByLineReader.open();
        List<String[]> initialRecords = lineByLineReader.next(25);

        List<Integer> preambleAsList = initialRecords.stream().map(token -> Integer.parseInt(token[0])).collect(Collectors.toList());
        int[] preamble = convertIntegers(preambleAsList);

        PreambleWindow preambleWindow = PreambleWindow.initialize(preamble);
        List<Integer> numbers = new ArrayList<>();
        int incorrectValue = Integer.MIN_VALUE;
        while (lineByLineReader.hasNext()) {
            String[] tokens = lineByLineReader.next();
            if (tokens != null) {
                int value = Integer.parseInt(tokens[0]);
                numbers.add(value);
                if (!preambleWindow.isValidNextValue(value)) {
                    incorrectValue = value;
                    break;
                }
                preambleWindow.shift(value);
            }
        }

        System.err.println(incorrectValue);
        System.err.println(numbers.size());

        boolean found = false;
        List<Integer> temp = new ArrayList<>();
        Integer sum =0;
        Iterator<Integer> numIterator = numbers.iterator();
        while(!found && numIterator.hasNext()) {
            int nextValue = numIterator.next();
            if(sum + nextValue < incorrectValue) {

            }

        }
    }

    public static int[] convertIntegers(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }
}
