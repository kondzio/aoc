package com.kk.aoc.passwords.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class PolicyAndPasswordEntry {
    private final Policy policy;
    private final String password;
}
