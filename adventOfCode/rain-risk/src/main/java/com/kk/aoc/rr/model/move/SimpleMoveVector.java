package com.kk.aoc.rr.model.move;

import com.kk.aoc.rr.model.Position;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SimpleMoveVector implements MoveVector {
    private final Position vector;
}
