package com.kk.aoc.hh.main;

import com.kk.aoc.common.LineByLineFileReader;
import com.kk.aoc.hh.model.BagContainer;
import com.kk.aoc.hh.register.BagRegister;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        LineByLineFileReader lineByLineFileReader = LineByLineFileReader.builder().separator(" contain |, ").inputFile(new File("src/main/resources/day7/input.txt")).build();
        lineByLineFileReader.open();

        BagRegister bagRegister = new BagRegister();
        while (lineByLineFileReader.hasNext()) {
            String[] tokens = lineByLineFileReader.next();
            if (!isBlank(tokens)) {
                BagContainer bagContainer = bagRegister.getOrRegister(tokens[0]);
                if (tokens.length > 1) {
                    for (int i = 1; i < tokens.length; i++) {
                        boolean emptyMarker = BagContainer.emptyPattern.matcher(tokens[i]).find();
                        if (!emptyMarker) {
                            Matcher contentMatcher = BagContainer.contentPattern.matcher(tokens[i]);
                            if (contentMatcher.find()) {
                                int count = Integer.parseInt(contentMatcher.group(1));
                                String name = contentMatcher.group(3);
                                if (count == 1) {
                                    name += "s";
                                }
                                BagContainer bag = bagRegister.getOrRegister(name);
                                bagContainer.addContent(bag, count);
                                bag.addReference(bagContainer.getName());
                            }
                        }
                    }
                }
            }
        }
        System.err.println(bagRegister.getReferencesCount("shiny gold bags"));
        System.err.println(bagRegister.getContentBagCount("shiny gold bags"));
    }

    private static boolean isBlank(String[] tokens) {
        return tokens == null || tokens.length == 0 || (tokens.length == 1 && isBlank(tokens[0]));
    }

    private static boolean isBlank(String token) {
        return token == null || token.length() == 0;
    }
}
