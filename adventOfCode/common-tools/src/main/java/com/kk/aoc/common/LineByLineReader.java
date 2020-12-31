package com.kk.aoc.common;

import lombok.Builder;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class LineByLineReader implements InputReader<String[]> {
    @NonNull
    private final String separator;

    @Builder.Default
    private boolean skipEmpty = true;
    protected BufferedReader bufferedReader;

    private boolean finished;
    private boolean opened;

    public LineByLineReader(@NonNull String separator, boolean skipEmpty) {
        this.separator = separator;
        this.skipEmpty = skipEmpty;
    }

    @Override
    public void open() throws FileNotFoundException {
        opened = true;
        doOpen();
    }

    public abstract void doOpen() throws FileNotFoundException;

    @Override
    public boolean hasNext() {
        requireOpen();
        return !finished;
    }

    @SneakyThrows
    @Override
    public String[] next() {
        requireOpen();
        return doNext();
    }

    public List<String[]> next(int batchSize) {
        requireOpen();
        List<String[]> batch = new ArrayList<>();
        int i = 0;
        while (hasNext() && i < batchSize) {
            batch.add(next());
            i++;
        }
        return batch;
    }

    private String[] doNext() throws IOException {
        String line = bufferedReader.readLine();
        if (line == null) {
            finished = true;
            return null;
        }
        if (line.isEmpty() && skipEmpty) {
            return next();
        }
        return separator != null ? line.split(separator, -1) : new String[]{line};
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
        if (!opened) {
            throw new IllegalStateException("Is not opened");
        }
    }
}
