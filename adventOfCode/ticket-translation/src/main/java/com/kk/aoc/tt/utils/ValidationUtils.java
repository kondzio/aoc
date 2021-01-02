package com.kk.aoc.tt.utils;

import com.kk.aoc.tt.ticket.Field;
import com.kk.aoc.tt.ticket.Ticket;
import com.kk.aoc.tt.validation.TicketValidator;
import com.kk.aoc.tt.validation.ValidationResult;
import com.kk.aoc.tt.validation.Validator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationUtils {
    public static int calculateValidationIndex(Map<String, Validator<Boolean, Field<Integer>>> validators, List<Ticket> tickets) {
        return collectValidationResults(validators, tickets).stream().filter(result -> !result.isSuccess()).map(result -> result.getIncorrectField().getValue()).reduce(0, Integer::sum);
    }

    public static List<ValidationResult<Integer>> collectValidationResults(Map<String, Validator<Boolean, Field<Integer>>> validators, List<Ticket> tickets) {
        TicketValidator ticketValidator = new TicketValidator(validators);
        List<ValidationResult<Integer>> validationResults = new ArrayList<>();
        for (Ticket ticket : tickets) {
            validationResults.add(ticketValidator.validate(ticket));
        }
        return validationResults;
    }
}
