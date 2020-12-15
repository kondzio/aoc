package com.kk.aoc.cc.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@ToString
public class RowCustomStatistics {
    private final int groupId;
    private Set<Character> anyoneYes = new HashSet<>();
    private Set<Character> everyoneYes;
    boolean initialized;

    public void analyzeVotes(String votes) {
        for (char q : votes.toCharArray()) {
            anyoneYes.add(q);
        }

        Set<Character> votesAsSet = convert(votes.toCharArray());
        if (!initialized) {
            everyoneYes = votesAsSet;
            initialized = true;
        } else {
            everyoneYes.retainAll(votesAsSet);
        }
    }

    private Set<Character> convert(char[] votes) {
        Set<Character> votesAsSet = new HashSet<>();
        for (char v : votes) {
            votesAsSet.add(v);
        }
        return votesAsSet;
    }

    public int getAnyoneYesVotesCount() {
        return anyoneYes.size();
    }

    public int getEveryoneYesVotesCount() {
        return everyoneYes.size();
    }
}
