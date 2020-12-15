package com.kk.aoc.passwords.main;

import com.kk.aoc.common.LineByLineReader;
import com.kk.aoc.passwords.model.Policy;
import com.kk.aoc.passwords.model.PolicyAndPasswordEntry;
import com.kk.aoc.passwords.validator.PositionPolicyValidator;
import com.kk.aoc.passwords.validator.Validator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<PolicyAndPasswordEntry> entries = new ArrayList<>();
        try (LineByLineReader lineByLineReader = LineByLineReader.builder().separator("\\s").inputFile(new File("src/main/resources/day2/input.txt")).build()) {
            lineByLineReader.open();
            while (lineByLineReader.hasNext()) {
                String[] tokens = lineByLineReader.next();
                if (tokens != null) {
                    String[] minMax = tokens[0].split("-");
                    entries.add(PolicyAndPasswordEntry.builder().
                            policy(Policy.builder()
                                    .min(Integer.parseInt(minMax[0]))
                                    .max(Integer.parseInt(minMax[1]))
                                    .literal(tokens[1].replaceAll(":", "")).build())
                            .password(tokens[2]).build());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int validPasswordsCount = 0;
        Validator validator = new PositionPolicyValidator();
        for (PolicyAndPasswordEntry entry : entries) {
            if (validator.validate(entry.getPolicy(), entry.getPassword())) {
                validPasswordsCount++;
            }
        }
        System.err.println(validPasswordsCount);
    }
}
