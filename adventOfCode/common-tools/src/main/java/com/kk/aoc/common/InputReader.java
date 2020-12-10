package com.kk.aoc.common;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.util.Iterator;

public interface InputReader<T> extends Iterator<T>, Closeable {
    void open() throws FileNotFoundException;
}
