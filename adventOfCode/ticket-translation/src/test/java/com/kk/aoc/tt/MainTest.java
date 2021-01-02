package com.kk.aoc.tt;

import com.google.common.base.Preconditions;
import com.kk.aoc.common.LineByLineFileReader;
import com.kk.aoc.common.LineByLineReader;
import com.kk.aoc.common.LineByLineStringReader;
import com.kk.aoc.tt.ticket.Field;
import com.kk.aoc.tt.ticket.Ticket;
import com.kk.aoc.tt.utils.ParseUtils;
import com.kk.aoc.tt.utils.ValidationUtils;
import com.kk.aoc.tt.validation.ValidationResult;
import com.kk.aoc.tt.validation.Validator;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class MainTest {

    @Test
    public void sampleTest1() throws FileNotFoundException {
        String inputText = "class: 1-3 or 5-7\n" +
                "row: 6-11 or 33-44\n" +
                "seat: 13-40 or 45-50\n" +
                "\n" +
                "your ticket:\n" +
                "7,1,14\n" +
                "\n" +
                "nearby tickets:\n" +
                "7,3,47\n" +
                "40,4,50\n" +
                "55,2,20\n" +
                "38,6,12";

        Input input = initialize(LineByLineStringReader.builder().inputString(inputText).separator("\\n").build());

        int index = ValidationUtils.calculateValidationIndex(input.getValidators(), input.getNearbyTickets());
        Assertions.assertEquals(71, index);
    }

    @Test
    public void part1Test() throws FileNotFoundException {
        Input input = initialize(LineByLineFileReader.builder().inputFile(new File("D:\\poligon\\aoc\\adventOfCode\\src\\main\\resources\\day16\\input.txt")).separator("\\n").build());

        int index = ValidationUtils.calculateValidationIndex(input.getValidators(), input.getNearbyTickets());
        Assertions.assertEquals(22073, index);
    }

    @Test
    public void part2Test() throws FileNotFoundException {
        Input input = initialize(LineByLineFileReader.builder().inputFile(new File("D:\\poligon\\aoc\\adventOfCode\\src\\main\\resources\\day16\\input.txt")).separator("\\n").build());
        List<ValidationResult<Integer>> validResults = ValidationUtils.collectValidationResults(input.getValidators(), input.getNearbyTickets()).stream().filter(ValidationResult::isSuccess).collect(Collectors.toList());
        for (String validatorName : input.getValidators().keySet()) {
            Set<Integer> intersection = null;
            for (ValidationResult<Integer> result : validResults) {
                Set<Integer> positions = result.getValidationDetails().get(validatorName).stream().map(Field::getPosition).collect(Collectors.toSet());
                if (intersection == null) {
                    intersection = positions;
                } else {
                    intersection = intersection.stream().filter(positions::contains).collect(Collectors.toSet());
                }
            }
            if (validatorName.startsWith("departure")) {
                System.err.println(validatorName + " : " + intersection);
            }
        }

        //here manual reduction was applied :)

        long multiplication = 1;
        multiplication *= input.getYourTicket().getFields().get(3).getValue();
        multiplication *= input.getYourTicket().getFields().get(6).getValue();
        multiplication *= input.getYourTicket().getFields().get(7).getValue();
        multiplication *= input.getYourTicket().getFields().get(9).getValue();
        multiplication *= input.getYourTicket().getFields().get(14).getValue();
        multiplication *= input.getYourTicket().getFields().get(16).getValue();

        Assertions.assertEquals(1346570764607L, multiplication);
    }

    private Input initialize(LineByLineReader lineByLineReader) throws FileNotFoundException {
        lineByLineReader.open();
        Input input = new Input();
        while (lineByLineReader.hasNext()) {
            String[] tokens = lineByLineReader.next();
            if (!isEmpty(tokens)) {
                Validator<Boolean, Field<Integer>> validator = ParseUtils.parseValidator(tokens[0]);
                input.insertValidator(validator.getName(), validator);
            } else {
                break;
            }
        }

        String[] yourTicketTokens = lineByLineReader.next(2).get(1);
        Ticket yourTicket = ParseUtils.parseTicket(yourTicketTokens[0]);
        input.setYourTicket(yourTicket);

        lineByLineReader.next(2);

        while (lineByLineReader.hasNext()) {
            String[] tokens = lineByLineReader.next();
            if (tokens != null) {
                Ticket ticket = ParseUtils.parseTicket(tokens[0]);
                input.addNearbyTicket(ticket);
            } else {
                break;
            }
        }

        return input;
    }

    @Getter
    private static class Input {
        private Map<String, Validator<Boolean, Field<Integer>>> validators = new HashMap<>();
        @Setter
        private Ticket yourTicket;
        private List<Ticket> nearbyTickets = new ArrayList<>();

        public void insertValidator(String validatorName, Validator<Boolean, Field<Integer>> validator) {
            Preconditions.checkNotNull(validatorName);
            Preconditions.checkNotNull(validator);
            validators.put(validatorName, validator);
        }

        public void addNearbyTicket(Ticket ticket) {
            Preconditions.checkNotNull(ticket);
            nearbyTickets.add(ticket);
        }

    }

    private static boolean isEmpty(String[] token) {
        return token == null || (token.length == 1 && StringUtils.isBlank(token[0]));
    }
}
