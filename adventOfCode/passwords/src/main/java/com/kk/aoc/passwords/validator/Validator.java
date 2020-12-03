package com.kk.aoc.passwords.validator;

import com.kk.aoc.passwords.model.Policy;

public interface Validator {
    boolean validate(Policy policy, String password);
}
