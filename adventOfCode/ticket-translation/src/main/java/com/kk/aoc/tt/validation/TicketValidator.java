package com.kk.aoc.tt.validation;

import com.kk.aoc.tt.ticket.Field;
import com.kk.aoc.tt.ticket.Ticket;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class TicketValidator implements Validator<ValidationResult<Integer>, Ticket> {
    private final Map<String, Validator<Boolean, Field<Integer>>> validators;

    @Override
    public String getName() {
        return "Ticket-Validator";
    }

    public ValidationResult<Integer> validate(Ticket ticket) {
        ValidationResult<Integer> validationResult = new ValidationResult<>();
        validationResult.setSuccess(true);
        for (Field<Integer> field : ticket.getFields()) {
            boolean finalValidationStatus = false;
            for (Validator<Boolean, Field<Integer>> validator : validators.values()) {
                boolean validationStatus = validator.validate(field);
                if (validationStatus) {
                    validationResult.addValidationDetails(validator.getName(), field);
                }
                finalValidationStatus |= validationStatus;
            }
            if (!finalValidationStatus) {
                validationResult.setSuccess(false);
                validationResult.setIncorrectField(field);
                return validationResult;
            }
        }
        return validationResult;
    }
}
