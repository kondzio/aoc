package com.kk.aoc.passwords.validator;

import com.kk.aoc.passwords.model.Policy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LiteralPolicyValidator implements Validator {
    @Override
    public boolean validate(Policy policy, String password) {
        Pattern pattern = Pattern.compile(policy.getLiteral());
        Matcher matcher = pattern.matcher(password);
        int counter = 0;
        while (matcher.find()) {
            counter++;
        }
        return counter >= policy.getMin() && counter <= policy.getMax();
    }
}
