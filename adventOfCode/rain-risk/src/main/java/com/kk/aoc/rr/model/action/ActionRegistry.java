package com.kk.aoc.rr.model.action;

import com.google.common.base.Preconditions;
import com.kk.aoc.rr.model.ExecutionContext;
import com.kk.aoc.rr.model.Side;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import static com.kk.aoc.rr.model.action.ActionType.*;

public class ActionRegistry {
    private final Map<ActionType, Action> registry = new EnumMap<>(ActionType.class);

    public ActionRegistry() {
        registry.put(NORTH, ExecutionContext::moveToTheNorth);
        registry.put(EAST, ExecutionContext::moveToTheEast);
        registry.put(SOUTH, ExecutionContext::moveToTheSouth);
        registry.put(WEST, ExecutionContext::moveToTheWest);
        registry.put(FORWARD, ExecutionContext::moveForward);
        registry.put(LEFT, (context, arg) -> context.rotate(Side.LEFT, arg));
        registry.put(RIGHT, (context, arg) -> context.rotate(Side.RIGHT, arg));
    }

    public void register(ActionType type, Action action) {
        Preconditions.checkNotNull(action, "Action cannot be null");
        Preconditions.checkState(registry.containsKey(type));
        registry.put(type, action);
    }

    public Optional<Action> getByType(ActionType type) {
        Preconditions.checkNotNull(type);
        return Optional.ofNullable(registry.get(type));
    }
}
