package com.kk.aoc.tt.validation;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@Builder
public class ValidationResult<T> {
    private final boolean success;
    private final T incorrectValue;
}
