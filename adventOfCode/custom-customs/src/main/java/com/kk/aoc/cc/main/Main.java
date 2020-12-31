package com.kk.aoc.cc.main;

import com.kk.aoc.cc.model.RowCustomStatistics;
import com.kk.aoc.common.LineByLineFileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        LineByLineFileReader lineByLineFileReader = LineByLineFileReader.builder().separator("\\s").inputFile(new File("src/main/resources/day6/input.txt")).build();
        lineByLineFileReader.open();

        List<RowCustomStatistics> allStatistics = new ArrayList<>();
        RowCustomStatistics stats = new RowCustomStatistics(0);
        int rowSequence = 1;
        while (lineByLineFileReader.hasNext()) {
            String[] tokens = lineByLineFileReader.next();
            if (isBlank(tokens)) {
                allStatistics.add(stats);
                stats = new RowCustomStatistics(rowSequence++);
            } else {
                stats.analyzeVotes(tokens[0]);
            }
        }

        AtomicInteger anyoneCount = new AtomicInteger();
        allStatistics.forEach(s -> anyoneCount.addAndGet(s.getAnyoneYesVotesCount()));
        System.err.println(anyoneCount.get());

        AtomicInteger everyoneCount = new AtomicInteger();
        allStatistics.forEach(s -> everyoneCount.addAndGet(s.getEveryoneYesVotesCount()));
        System.err.println(everyoneCount.get());
    }

    private static boolean isBlank(String[] tokens) {
        return tokens == null || tokens.length == 0 || (tokens.length == 1 && isBlank(tokens[0]));
    }

    private static boolean isBlank(String token) {
        return token == null || token.length() == 0;
    }
}
