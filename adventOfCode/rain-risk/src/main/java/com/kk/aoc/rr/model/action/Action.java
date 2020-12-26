package com.kk.aoc.rr.model.action;

import com.kk.aoc.rr.model.ExecutionContext;

public interface Action {
    void execute(ExecutionContext context, int arg);
}
