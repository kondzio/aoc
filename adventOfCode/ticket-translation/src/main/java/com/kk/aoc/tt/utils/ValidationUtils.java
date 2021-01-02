package com.kk.aoc.tt.utils;

import com.kk.aoc.tt.ticket.Ticket;
import com.kk.aoc.tt.validation.TicketValidator;
import com.kk.aoc.tt.validation.ValidationResult;
import com.kk.aoc.tt.validation.Validator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationUtils {
    public static int calculateValidationIndex(Map<String, Validator<Boolean, Integer>> validators, List<Ticket> tickets) {
        TicketValidator ticketValidator = new TicketValidator(validators);
        Integer index = 0;
        for (Ticket ticket : tickets) {
            ValidationResult<Integer> result = ticketValidator.validate(ticket);
            index += result.getIncorrectValue();
        }
        return index;
    }
}
