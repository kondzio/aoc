package com.kk.aoc.tt.validation;

import com.kk.aoc.tt.ticket.Ticket;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class TicketValidator implements Validator<ValidationResult<Integer>, Ticket> {
    private final Map<String, Validator<Boolean, Integer>> validators;

    @Override
    public String getName() {
        return "Ticket-Validator";
    }

    public ValidationResult<Integer> validate(Ticket ticket) {
        for (Integer value : ticket.getValues()) {
            boolean valid = false;
            for (Validator<Boolean, Integer> validator : validators.values()) {
                valid |= validator.validate(value);
                if (!valid) {
                    return ValidationResult.<Integer>builder().result(false).incorrectValue(value).build();
                }
            }
        }
        return ValidationResult.<Integer>builder().result(true).build();
    }
}
