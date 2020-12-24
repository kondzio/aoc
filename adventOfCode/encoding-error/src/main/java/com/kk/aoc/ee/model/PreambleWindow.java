package com.kk.aoc.ee.model;

import java.util.*;

public class PreambleWindow {
    private final int[][] valuesMatrix;
    private final Map<Integer, IndexDescriptor> valueToIndexDescriptor;
    private final List<IndexDescriptor> orderedIndexDescriptors;
    private boolean initialized;
    private int size;

    public PreambleWindow(int[][] valuesMatrix, Map<Integer, IndexDescriptor> valueToIndexDescriptor, List<IndexDescriptor> orderedIndexDescriptors) {
        this.valuesMatrix = valuesMatrix;
        this.valueToIndexDescriptor = valueToIndexDescriptor;
        this.orderedIndexDescriptors = orderedIndexDescriptors;
        this.initialized = true;
        this.size = valuesMatrix.length;
    }

    public static PreambleWindow initialize(int[] preamble) {
        int[][] valuesMatrix = new int[preamble.length][preamble.length];
        Map<Integer, IndexDescriptor> valueToIndexDescriptor = new HashMap<>();
        List<IndexDescriptor> orderedIndexDescriptors = new ArrayList<>();
        for (int i = 0; i < preamble.length; i++) {
            IndexDescriptor indexDescriptor = IndexDescriptor.builder().index(i).value(preamble[i]).build();
            orderedIndexDescriptors.add(indexDescriptor);
            valueToIndexDescriptor.put(preamble[i], indexDescriptor);
        }

        for (int i = 0; i < valuesMatrix.length; i++) {
            for (int j = i + 1; j < valuesMatrix[i].length; j++) {
                int sum = preamble[i] + preamble[j];
                valuesMatrix[i][j] = sum;
                valuesMatrix[j][i] = sum;
            }
        }
        return new PreambleWindow(valuesMatrix, valueToIndexDescriptor, orderedIndexDescriptors);
    }

    public void shift(int value) {
        isInitialized();

        IndexDescriptor firstIndexDescriptor = orderedIndexDescriptors.stream().findFirst().orElse(null);
        orderedIndexDescriptors.remove(firstIndexDescriptor);
        valueToIndexDescriptor.remove(firstIndexDescriptor.getValue());

        IndexDescriptor newIndexDescriptor = IndexDescriptor.builder().index(firstIndexDescriptor.getIndex()).value(value).build();
        orderedIndexDescriptors.add(newIndexDescriptor);
        valueToIndexDescriptor.put(value, newIndexDescriptor);

        for (int i = 0; i < size; i++) {
            if (i != newIndexDescriptor.getIndex()) {
                int sum = valuesMatrix[i][newIndexDescriptor.getIndex()] - firstIndexDescriptor.getValue() + value;
                valuesMatrix[i][newIndexDescriptor.getIndex()] = sum;
                valuesMatrix[newIndexDescriptor.getIndex()][i] = sum;
            }
        }
    }

    public boolean isValidNextValue(int value) {
        Set<Integer> values = new HashSet<>();
        for (int i = 0; i < valuesMatrix.length; i++) {
            for (int j = i + 1; j < valuesMatrix[i].length; j++) {
                values.add(valuesMatrix[i][j]);
            }
        }
        return values.contains(value);
    }

    private void isInitialized() {
        if (!initialized) {
            throw new IllegalStateException("ValidValues are not initialized");
        }
    }
}
