package com.kk.aoc.tt.validation;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ComposedValidator<V> implements Validator<Boolean, V> {
    private final String name;
    private List<Validator<Boolean, V>> composedValidators = new ArrayList<>();

    @Override
    public Boolean validate(V value) {
        for (Validator<Boolean, V> validator : composedValidators) {
            if (validator.validate(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return name;
    }

    public void addValidator(Validator<Boolean, V> validator) {
        Preconditions.checkNotNull(validator);
        composedValidators.add(validator);
    }
}
