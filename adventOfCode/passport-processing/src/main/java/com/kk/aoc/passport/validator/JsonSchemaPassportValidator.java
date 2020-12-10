package com.kk.aoc.passport.validator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.kk.aoc.passport.model.BasePassport;

import java.io.File;

public class JsonSchemaPassportValidator implements PassportValidator<BasePassport> {
    private JsonSchema schema;
    private final ObjectMapper objectMapper;

    public JsonSchemaPassportValidator() {
        this.objectMapper = new ObjectMapper();
        try {
            JsonNode schemaJsonNode = objectMapper.readTree(new File("D:\\poligon\\aoc\\adventOfCode\\passport-processing\\src\\main\\resources\\passport-schema.json"));
            this.schema = JsonSchemaFactory.byDefault().getJsonSchema(schemaJsonNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validate(BasePassport input) throws Exception {
        JsonNode json = objectMapper.valueToTree(input);
        ProcessingReport report = schema.validate(json);
        return report.isSuccess();
    }
}
