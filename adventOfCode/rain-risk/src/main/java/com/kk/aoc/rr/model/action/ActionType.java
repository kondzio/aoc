package com.kk.aoc.rr.model.action;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ActionType {
    NORTH("N", "Action N means to move north by the given value"),
    SOUTH("S", "Action S means to move south by the given value."),
    EAST("E", "Action E means to move east by the given value."),
    WEST("W", "Action W means to move west by the given value."),
    LEFT("L", "Action L means to turn left the given number of degrees."),
    RIGHT("R", "Action R means to turn right the given number of degrees."),
    FORWARD("F", "Action F means to move forward by the given value in the direction the ship is currently facing.");

    private final String id;
    private final String description;

    public static ActionType fromValue(String id) {
        for (ActionType actionType : values()) {
            if (actionType.getId().equals(id)) {
                return actionType;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Action{" +
                "id='" + id + '\'' +
                '}';
    }
}
