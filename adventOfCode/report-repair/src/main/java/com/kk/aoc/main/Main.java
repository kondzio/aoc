package com.kk.aoc.main;

import com.kk.aoc.analyzer.ReportAnalyzer;
import com.kk.aoc.common.LineByLineFileReader;
import com.kk.aoc.model.Expense;
import com.kk.aoc.model.ExpenseJournal;
import com.kk.aoc.model.Position;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Position<Integer>> expenses = new ArrayList<>();
        try (LineByLineFileReader lineByLineFileReader = LineByLineFileReader.builder().separator(";").inputFile(new File("src/main/resources/day1/input.txt")).build()) {
            lineByLineFileReader.open();
            while (lineByLineFileReader.hasNext()) {
                String[] tokens = lineByLineFileReader.next();
                if (tokens != null) {
                    expenses.add(new Expense(Integer.parseInt(tokens[0])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExpenseJournal expenseSheet = new ExpenseJournal(expenses);
        List<ReportAnalyzer.MultiplicationResult> results = expenseSheet.analyze(new ReportAnalyzer(2020));
        System.err.println(results);
    }
}
