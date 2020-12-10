package com.kk.aoc.passport.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class JsonPassport implements Passport {
    private final JsonNode jsonNode;
}
