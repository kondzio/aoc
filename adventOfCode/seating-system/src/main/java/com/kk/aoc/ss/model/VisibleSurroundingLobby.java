package com.kk.aoc.ss.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.function.UnaryOperator;

public class VisibleSurroundingLobby extends Lobby {
    public VisibleSurroundingLobby(int width, int length) {
        super(width, length);
    }

    @Override
    public Lobby newLobby(int width, int length) {
        return new VisibleSurroundingLobby(width, length);
    }

    @Override
    public SeatState[][] getWithSurrounding(int x, int y) {
        SeatState[][] surrounding = new SeatState[3][3];

        Position position = Position.builder().x(x).y(y).build();
        SurroundingEvaluator evaluator = new SurroundingEvaluator();
        surrounding[0][0] = evaluator.evaluate(this, position, pos -> Position.builder().x(pos.getX() - 1).y(pos.getY() - 1).build());
        surrounding[0][1] = evaluator.evaluate(this, position, pos -> Position.builder().x(pos.getX()).y(pos.getY() - 1).build());
        surrounding[0][2] = evaluator.evaluate(this, position, pos -> Position.builder().x(pos.getX() + 1).y(pos.getY() - 1).build());
        surrounding[1][0] = evaluator.evaluate(this, position, pos -> Position.builder().x(pos.getX() - 1).y(pos.getY()).build());
        surrounding[1][1] = getByPosition(x, y);
        surrounding[1][2] = evaluator.evaluate(this, position, pos -> Position.builder().x(pos.getX() + 1).y(pos.getY()).build());
        surrounding[2][0] = evaluator.evaluate(this, position, pos -> Position.builder().x(pos.getX() - 1).y(pos.getY() + 1).build());
        surrounding[2][1] = evaluator.evaluate(this, position, pos -> Position.builder().x(pos.getX()).y(pos.getY() + 1).build());
        surrounding[2][2] = evaluator.evaluate(this, position, pos -> Position.builder().x(pos.getX() + 1).y(pos.getY() + 1).build());
        return surrounding;
    }

    private static class SurroundingEvaluator {
        SeatState evaluate(Lobby lobby, Position position, UnaryOperator<Position> positionEvaluator) {
            Position temp = positionEvaluator.apply(position);
            SeatState seat = SeatState.FLOOR;
            while (!isOverEdge(lobby, temp) && (seat = lobby.getByPosition(temp.getX(), temp.getY())) != null && !isSeat(seat)) {
                temp = positionEvaluator.apply(temp);
            }
            return seat;
        }

        private static boolean isOverEdge(Lobby lobby, Position pos) {
            return pos.getX() < 0 || pos.getX() >= lobby.getWidth() || pos.getY() < 0 || pos.getY() >= lobby.getLength();
        }

        private static boolean isSeat(SeatState seat) {
            return seat != SeatState.FLOOR;
        }
    }

    @Getter
    @ToString
    @Builder
    private static class Position {
        private final int x;
        private final int y;
    }
}
