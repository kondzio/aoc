package com.kk.aoc.dd.model.program;

import com.kk.aoc.dd.model.address.AddressTree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FloatingAddressExecutionContext implements ExecutionContext {
    private String mask;

    private Map<Long, Long> bitmap = new HashMap<>();

    public FloatingAddressExecutionContext(String mask) {
        this.mask = mask;
    }

    public FloatingAddressExecutionContext() {
        this(null);
    }

    @Override
    public long getValue(long address) {
        return bitmap.get(address);
    }

    @Override
    public void setValue(long address, long value) {
        Iterator<Long> addressIterator = applyAddressMask(address);
        while (addressIterator.hasNext()) {
            long nextAddress = addressIterator.next();
            bitmap.put(nextAddress, value);
        }
    }

    @Override
    public void setMask(String mask) {
        this.mask = mask;
    }

    @Override
    public Map<Long, Long> getBitmap() {
        return bitmap;
    }

    private Iterator<Long> applyAddressMask(long address) {
        String binaryValueRep = String.format("%36s", Long.toBinaryString(address)).replace(' ', '0');
        AddressTree addressTree = new AddressTree();
        for (int i = 0; i < mask.length(); i++) {
            char maskChar = mask.charAt(i);
            if ('1' == maskChar) {
                addressTree.extendAddress('1');
            } else if ('X' == maskChar) {
                addressTree.extendAddress('0', '1');
            } else {
                addressTree.extendAddress(binaryValueRep.charAt(i));
            }
        }
        return addressTree.collectAddresses().stream().map(addressAsString -> Long.parseLong(addressAsString, 2)).iterator();
    }
}
