package com.kk.aoc.passport.parser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.aoc.passport.model.BasePassport;
import com.kk.aoc.passport.model.MapPassport;

public class JsonPassportParser implements PassportParser<MapPassport, BasePassport> {
    private final ObjectMapper objectMapper;

    public JsonPassportParser() {
        JsonFactory factory = new JsonFactory();
        factory.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        this.objectMapper = new ObjectMapper(factory);
    }

    @Override
    public BasePassport parse(MapPassport input) throws Exception {
        String content = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(input);
        return objectMapper.readValue(content, BasePassport.class);
    }
}
