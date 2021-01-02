package com.kk.aoc.tt.validation;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.kk.aoc.tt.ticket.Field;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class ValidationResult<T> {
    @Setter
    private boolean success;
    private final Multimap<String, Field<T>> validationDetails = HashMultimap.create();
    @Setter
    private Field<T> incorrectField;

    public void addValidationDetails(String validatorName, Field<T> correctField) {
        validationDetails.put(validatorName, correctField);
    }

}
