package com.kk.aoc.dd.model.program;

import java.util.HashMap;
import java.util.Map;

public class PlainAddressExecutionContext implements ExecutionContext {
    private String mask;

    private Map<Long, Long> bitmap = new HashMap<>();

    public PlainAddressExecutionContext(String mask) {
        this.mask = mask;
    }

    public PlainAddressExecutionContext() {
        this(null);
    }

    @Override
    public long getValue(long address) {
        return bitmap.get(address);
    }

    @Override
    public void setValue(long address, long value) {
        bitmap.put(address, applyValueMask(value));
    }

    @Override
    public void setMask(String mask) {
        this.mask = mask;
    }

    @Override
    public Map<Long, Long> getBitmap() {
        return bitmap;
    }

    private long applyValueMask(long value) {
        String binaryValueRep = String.format("%36s", Long.toBinaryString(value)).replace(' ', '0');
        StringBuilder finalValueBuilder = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char maskChar = mask.charAt(i);
            if ('X' == maskChar) {
                finalValueBuilder.append(binaryValueRep.charAt(i));
            } else {
                finalValueBuilder.append(maskChar);
            }
        }
        return Long.parseLong(finalValueBuilder.toString(), 2);
    }
}
