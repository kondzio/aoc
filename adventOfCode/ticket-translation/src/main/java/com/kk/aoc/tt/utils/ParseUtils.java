package com.kk.aoc.tt.utils;

import com.kk.aoc.tt.ticket.Field;
import com.kk.aoc.tt.ticket.Ticket;
import com.kk.aoc.tt.validation.ComposedValidator;
import com.kk.aoc.tt.validation.RangeValidator;
import com.kk.aoc.tt.validation.Validator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParseUtils {
    private static final Pattern VALIDATOR_PATTERN = Pattern.compile("^(.*: )(\\d*)(-)(\\d*)( or )(\\d*)(-)(\\d*)$");

    public static Validator<Boolean, Field<Integer>> parseValidator(String line) {
        Matcher validatorMatcher = VALIDATOR_PATTERN.matcher(line);
        if (validatorMatcher.find()) {
            String validatorName = validatorMatcher.group(1);

            String from1 = validatorMatcher.group(2);
            String to1 = validatorMatcher.group(4);
            RangeValidator rangeValidator1 = new RangeValidator(validatorName + "1", Integer.parseInt(from1), Integer.parseInt(to1));

            String from2 = validatorMatcher.group(6);
            String to2 = validatorMatcher.group(8);
            RangeValidator rangeValidator2 = new RangeValidator(validatorName + "2", Integer.parseInt(from2), Integer.parseInt(to2));

            ComposedValidator<Field<Integer>> composedValidator = new ComposedValidator<>(validatorName);
            composedValidator.addValidator(rangeValidator1);
            composedValidator.addValidator(rangeValidator2);

            return composedValidator;
        }
        return null;
    }

    public static Ticket parseTicket(String line) {
        List<Integer> yourTicketValues = Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        List<Field<Integer>> fields = new ArrayList<>();
        for (int i = 0; i < yourTicketValues.size(); i++) {
            fields.add(Field.<Integer>builder().position(i).value(yourTicketValues.get(i)).build());
        }
        return new Ticket(fields);
    }
}
