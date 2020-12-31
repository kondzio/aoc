package com.kk.aoc.common;

import lombok.Builder;
import lombok.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class LineByLineFileReader extends LineByLineReader {
    @NonNull
    private final File inputFile;

    @Builder
    public LineByLineFileReader(@NonNull String separator, @NonNull File inputFile, boolean skipEmpty) {
        super(separator, skipEmpty);
        this.inputFile = inputFile;
    }


    @Override
    public void doOpen() throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(inputFile));
    }
}
