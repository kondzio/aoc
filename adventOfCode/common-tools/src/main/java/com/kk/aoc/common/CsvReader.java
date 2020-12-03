package com.kk.aoc.common;

import lombok.Builder;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.*;
import java.util.Iterator;
import java.util.function.Consumer;


public class CsvReader implements Iterator<String[]>, Closeable {
    @NonNull
    private final String separator;
    @NonNull
    private final File inputFile;
    @Builder.Default
    private boolean skipEmpty = true;
    private BufferedReader bufferedReader;

    private boolean finished;
    private boolean opened;

    @Builder
    public CsvReader(@NonNull String separator, @NonNull File inputFile, boolean skipEmpty) {
        this.separator = separator;
        this.inputFile = inputFile;
        this.skipEmpty = skipEmpty;
    }


    public void open() throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(inputFile));
        opened = true;
    }

    @Override
    public boolean hasNext() {
        requireOpen();
        return !finished;
    }

    @SneakyThrows
    @Override
    public String[] next() {
        requireOpen();
        String line = bufferedReader.readLine();
        if (line == null) {
            finished = true;
            return null;
        }
        if (line.isEmpty() && skipEmpty) {
            return next();
        }
        return line.split(separator, -1);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer<? super String[]> action) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() throws IOException {
        requireOpen();
        bufferedReader.close();
    }

    private void requireOpen() {
        if (opened == false) {
            throw new IllegalStateException("Is not opened");
        }
    }
}
