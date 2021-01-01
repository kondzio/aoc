package com.kk.aoc.dd.model.program;

import java.util.Map;

public interface ExecutionContext {
    long getValue(long address);

    void setValue(long address, long value);

    void setMask(String mask);

    Map<Long, Long> getBitmap();
}
