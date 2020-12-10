package com.kk.aoc.common;

import lombok.Builder;
import lombok.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class ScannerReader implements InputReader<String> {
    @NonNull
    private final String separator;
    @NonNull
    private final File inputFile;
    private final Pattern pattern;
    @Builder.Default
    private boolean skipEmpty = true;
    private Scanner scanner;

    @Builder
    public ScannerReader(@NonNull String separator, @NonNull File inputFile) {
        this.separator = separator;
        this.inputFile = inputFile;
        this.pattern = Pattern.compile(separator);
    }

    @Override
    public void open() throws FileNotFoundException {
        this.scanner = new Scanner(inputFile);
        this.scanner.useDelimiter(pattern);
    }

    @Override
    public void close() {
        if (scanner != null) {
            this.scanner.close();
        }
    }

    @Override
    public boolean hasNext() {
        isOpen();
        return scanner.hasNext();
    }

    @Override
    public String next() {
        isOpen();
        return scanner.next();
    }

    private void isOpen() {
        if (scanner == null) {
            throw new IllegalStateException("Is not open");
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer<? super String> action) {
        throw new UnsupportedOperationException();
    }
}
