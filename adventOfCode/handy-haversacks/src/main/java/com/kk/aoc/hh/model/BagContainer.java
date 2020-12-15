package com.kk.aoc.hh.model;

import java.util.Collection;
import java.util.Set;
import java.util.regex.Pattern;

public interface BagContainer {
    Pattern emptyPattern = Pattern.compile("^no other bags\\.?$");
    Pattern contentPattern = Pattern.compile("^(\\d+)(\\s)([a-z\\s]+)([,.])?$");

    String getName();

    Collection<BagContainer> getContent();

    Integer getContentBagMultiplier(BagContainer bag);

    void addContent(BagContainer bagContainer, Integer count);

    void addReference(String name);

    Set<String> getReferences();

    boolean isEmpty();
}
