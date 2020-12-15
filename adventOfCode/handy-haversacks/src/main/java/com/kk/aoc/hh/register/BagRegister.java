package com.kk.aoc.hh.register;

import com.kk.aoc.hh.model.BagContainer;
import com.kk.aoc.hh.model.DefaultBagContainer;
import lombok.NonNull;

import java.util.*;

public final class BagRegister {
    private final Map<String, BagContainer> bags = new HashMap<>();

    public BagContainer getOrRegister(@NonNull String name) {
        if (!bags.containsKey(name)) {
            BagContainer bag = DefaultBagContainer.builder().name(name).build();
            bags.put(name, bag);
        }
        return bags.get(name);
    }

    public int getContentBagCount(@NonNull String name) {
        if (!bags.containsKey(name)) {
            return 0;
        }

        BagContainer bag = bags.get(name);
        return countContent(bag);
    }

    private int countContent(BagContainer container) {
        if (container.isEmpty()) {
            return 1;
        }
        Iterator<BagContainer> contentBagIter = container.getContent().iterator();
        int count = 0;
        while (contentBagIter.hasNext()) {
            BagContainer contentBag = contentBagIter.next();
            Integer multiplier = container.getContentBagMultiplier(contentBag);
            count += multiplier * countContent(contentBag);
            if(!contentBag.isEmpty()) {
                count += multiplier;
            }
        }
        return count;
    }

    public int getReferencesCount(@NonNull String name) {
        if (!bags.containsKey(name)) {
            return 0;
        }
        Set<String> refsCollection = new HashSet<>();
        collectReferences(name, refsCollection);
        return refsCollection.size() - 1;
    }

    private void collectReferences(String name, Set<String> refsCollection) {
        Iterator<String> refIterator = bags.get(name).getReferences().iterator();
        refsCollection.add(name);
        while (refIterator.hasNext()) {
            String nextRef = refIterator.next();
            if (!refsCollection.contains(nextRef)) {
                collectReferences(nextRef, refsCollection);
            }
        }
    }
}
