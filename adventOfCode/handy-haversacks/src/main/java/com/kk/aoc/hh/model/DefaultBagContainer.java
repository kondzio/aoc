package com.kk.aoc.hh.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

@Builder
@Getter
@ToString
public class DefaultBagContainer implements BagContainer {
    private final String name;
    private final Map<BagContainer, Integer> definition = new HashMap<>();
    private final Set<String> references = new HashSet<>();

    @Override
    public Collection<BagContainer> getContent() {
        return definition.keySet();
    }

    @Override
    public Integer getContentBagMultiplier(BagContainer bag) {
        return definition.get(bag);
    }

    @Override
    public void addContent(BagContainer bagContainer, Integer count) {
        definition.put(bagContainer, count);
    }

    @Override
    public void addReference(String reference) {
        references.add(reference);
    }

    @Override
    public boolean isEmpty() {
        return getContent().isEmpty();
    }
}
