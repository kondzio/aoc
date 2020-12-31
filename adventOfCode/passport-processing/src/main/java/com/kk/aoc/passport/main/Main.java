package com.kk.aoc.passport.main;

import com.kk.aoc.common.LineByLineFileReader;
import com.kk.aoc.passport.model.MapPassport;
import com.kk.aoc.passport.parser.JsonPassportParser;
import com.kk.aoc.passport.validator.JsonSchemaPassportValidator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<MapPassport> passports = new ArrayList<>();
        JsonPassportParser jsonPassportParser = new JsonPassportParser();
        try (LineByLineFileReader reader = LineByLineFileReader.builder().separator("\\s").inputFile(new File("src/main/resources/day4/input.txt")).build()) {
            reader.open();
            MapPassport passport = new MapPassport();
            while (reader.hasNext()) {
                String[] tokens = reader.next();
                if (isBlank(tokens)) {
                    passports.add(passport);
                    passport = new MapPassport();
                } else {
                    MapPassport finalPassport = passport;
                    Arrays.stream(tokens).forEach(token -> {
                        String[] keyValuePair = token.split(":");
                        String field = keyValuePair[0];
                        String value = keyValuePair[1];
                        if ("hgt".equals(field)) {
                            String unit = value.substring(value.length() - 2);
                            String hgtValue = value.substring(0, value.length() - 2);
                            if ("cm".equals(unit)) {
                                finalPassport.addField("hgtCmValue", hgtValue);
                                finalPassport.addField("hgtUnit", unit);
                            } else if ("in".equals(unit)) {
                                finalPassport.addField("hgtInValue", hgtValue);
                                finalPassport.addField("hgtUnit", unit);
                            }
                        }
                        finalPassport.addField(keyValuePair[0], keyValuePair[1]);
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonSchemaPassportValidator passportValidator = new JsonSchemaPassportValidator();
        long count = passports.stream().filter(passport -> {
            try {
                return passportValidator.validate(jsonPassportParser.parse(passport));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }).count();

        System.err.println(count);
    }

    private static boolean isBlank(String[] tokens) {
        return tokens == null || tokens.length == 0 || (tokens.length == 1 && isBlank(tokens[0]));
    }

    private static boolean isBlank(String token) {
        return token == null || token.length() == 0;
    }
}
