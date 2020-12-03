package com.kk.aoc.passwords.validator;

import com.kk.aoc.passwords.model.Policy;

public class PositionPolicyValidator implements Validator {
    @Override
    public boolean validate(Policy policy, String password) {
        char chatAtFirstPos = password.charAt(policy.getMin() - 1);
        char charAtSecondPos = password.charAt(policy.getMax() - 1);
        int count = 0;
        if (chatAtFirstPos == policy.getLiteral().charAt(0)) {
            count++;
        }

        if (charAtSecondPos == policy.getLiteral().charAt(0)) {
            count++;
        }

        return count == 1;
    }
}
