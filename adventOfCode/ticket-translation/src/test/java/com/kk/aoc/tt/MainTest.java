package com.kk.aoc.tt;

import com.google.common.base.Preconditions;
import com.kk.aoc.common.LineByLineReader;
import com.kk.aoc.common.LineByLineStringReader;
import com.kk.aoc.tt.ticket.Ticket;
import com.kk.aoc.tt.utils.ParseUtils;
import com.kk.aoc.tt.utils.ValidationUtils;
import com.kk.aoc.tt.validation.Validator;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Assertions.assertEquals(72, index);
    }

    private Input initialize(LineByLineReader lineByLineReader) throws FileNotFoundException {
        lineByLineReader.open();
        Input input = new Input();
        while (lineByLineReader.hasNext()) {
            String[] tokens = lineByLineReader.next();
            if (!isEmpty(tokens)) {
                Validator<Boolean, Integer> validator = ParseUtils.parseValidator(tokens[0]);
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
        private Map<String, Validator<Boolean, Integer>> validators = new HashMap<>();
        @Setter
        private Ticket yourTicket;
        private List<Ticket> nearbyTickets = new ArrayList<>();

        public void insertValidator(String validatorName, Validator<Boolean, Integer> validator) {
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
