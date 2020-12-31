package com.kk.aoc.aa.main;

import com.kk.aoc.aa.main.adapter.AdapterChain;
import com.kk.aoc.aa.main.adapter.AdapterCombinationAnalyzer;
import com.kk.aoc.aa.main.adapter.JoltageAnalyzer;
import com.kk.aoc.common.LineByLineFileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        LineByLineFileReader lineByLineFileReader = LineByLineFileReader.builder().separator("\\s").inputFile(new File("src/main/resources/day10/input.txt")).build();
        lineByLineFileReader.open();
        List<Integer> jolts = new ArrayList<>();
        while (lineByLineFileReader.hasNext()) {
            String[] tokens = lineByLineFileReader.next();
            if (tokens != null) {
                jolts.add(Integer.parseInt(tokens[0]));
            }
        }
        JoltageAnalyzer analyzer = new JoltageAnalyzer(3);
        AdapterChain adapterChain = analyzer.analyze(jolts);
        System.err.println(adapterChain);

        AdapterCombinationAnalyzer combinationAnalyzer = new AdapterCombinationAnalyzer(3, Collections.max(jolts));
        int counter = combinationAnalyzer.analyze(jolts);
        System.err.println(counter);
    }
}
