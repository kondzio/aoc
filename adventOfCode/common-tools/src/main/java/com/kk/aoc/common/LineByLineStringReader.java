package com.kk.aoc.common;

import lombok.Builder;
import lombok.NonNull;

import java.io.BufferedReader;
import java.io.StringReader;

public class LineByLineStringReader extends LineByLineReader {
    private final String inputString;

    @Builder
    public LineByLineStringReader(@NonNull String separator, String inputString, boolean skipEmpty) {
        super(separator, skipEmpty);
        this.inputString = inputString;
    }

    @Override
    public void doOpen() {
        bufferedReader = new BufferedReader(new StringReader(inputString));
    }
}
