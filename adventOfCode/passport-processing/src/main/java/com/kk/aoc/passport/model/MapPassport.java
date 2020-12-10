package com.kk.aoc.passport.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.NonNull;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
public class MapPassport implements Passport {
    @JsonAnyGetter
    private final Map<String, Object> fields = new HashMap<>();

    public void addField(@NonNull String field, String value) {
        fields.put(field, value);
    }

    public <T> T getValue(@NonNull String field) {
        return (T) fields.get(field);
    }
}
