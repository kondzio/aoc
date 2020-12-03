package com.kk.aoc.main;

import com.kk.aoc.analyzer.BackPackAnalyzer;
import com.kk.aoc.common.CsvReader;
import com.kk.aoc.model.Expense;
import com.kk.aoc.model.ExpenseJournal;
import com.kk.aoc.model.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Transaction<Integer>> expenses = new ArrayList<>();
        try (CsvReader csvReader = CsvReader.builder().separator(";").inputFile(new File("src/main/resources/day1/input.txt")).build()) {
            csvReader.open();
            while (csvReader.hasNext()) {
                String[] tokens = csvReader.next();
                if (tokens != null) {
                    expenses.add(new Expense(Integer.parseInt(tokens[0])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExpenseJournal expenseSheet = new ExpenseJournal(expenses);
        List<BackPackAnalyzer.MultiplicationResult> results = expenseSheet.analyze(new BackPackAnalyzer(2020));
        System.err.println(results);
    }
}