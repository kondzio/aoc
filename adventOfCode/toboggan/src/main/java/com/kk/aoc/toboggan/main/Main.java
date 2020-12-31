package com.kk.aoc.toboggan.main;

import com.kk.aoc.common.LineByLineFileReader;
import com.kk.aoc.toboggan.model.map.*;
import com.kk.aoc.toboggan.model.trajectory.TobogganTrajectory;
import com.kk.aoc.toboggan.model.trajectory.Trajectory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String[]> inputRows = new ArrayList<>();
        try (LineByLineFileReader lineByLineFileReader = LineByLineFileReader.builder().separator("").inputFile(new File("src/main/resources/day3/input.txt")).build()) {
            lineByLineFileReader.open();
            while (lineByLineFileReader.hasNext()) {
                String[] tokens = lineByLineFileReader.next();
                if (tokens != null) {
                    inputRows.add(Arrays.copyOf(tokens, tokens.length - 1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        MapObject[][] inputMatrix = new MapObject[inputRows.size()][];
        for (int yi = 0; yi < inputRows.size(); yi++) {
            String[] row = inputRows.get(yi);
            inputMatrix[yi] = new MapObject[row.length];
            for (int xi = 0; xi < row.length; xi++) {
                inputMatrix[yi][xi] = MapObjects.objectFrom(row[xi]);
            }

        }

        GeologicalMap geologicalMap = new GeologicalMap(inputMatrix[0].length, inputMatrix.length);

        MapLayer layer = new MapLayer("trees", inputMatrix);
        geologicalMap.addLayer(layer);

        Trajectory x1y1trajectory = new TobogganTrajectory(
                Coordinates.builder().x(0).y(0).build(),
                layer.getYLength() - 2,
                from -> Coordinates.builder().x(from.getX() + 1).y(from.getY() + 1).build());
        List<MapObject> result = layer.checkTrajectory(x1y1trajectory);
        System.err.println("x1y1: " + countTrees(result));

        Trajectory x3y1trajectory = new TobogganTrajectory(
                Coordinates.builder().x(0).y(0).build(),
                layer.getYLength() - 2,
                from -> Coordinates.builder().x(from.getX() + 3).y(from.getY() + 1).build());
        result = layer.checkTrajectory(x3y1trajectory);
        System.err.println("x3y1: " + countTrees(result));

        Trajectory x5y1trajectory = new TobogganTrajectory(
                Coordinates.builder().x(0).y(0).build(),
                layer.getYLength() - 2,
                from -> Coordinates.builder().x(from.getX() + 5).y(from.getY() + 1).build());
        result = layer.checkTrajectory(x5y1trajectory);
        System.err.println("x5y1: " + countTrees(result));

        Trajectory x7y1trajectory = new TobogganTrajectory(
                Coordinates.builder().x(0).y(0).build(),
                layer.getYLength() - 2,
                from -> Coordinates.builder().x(from.getX() + 7).y(from.getY() + 1).build());
        result = layer.checkTrajectory(x7y1trajectory);
        System.err.println("x7y1: " + countTrees(result));

        Trajectory x1y2trajectory = new TobogganTrajectory(
                Coordinates.builder().x(0).y(0).build(),
                (layer.getYLength() - 2) / 2,
                from -> Coordinates.builder().x(from.getX() + 1).y(from.getY() + 2).build());
        result = layer.checkTrajectory(x1y2trajectory);
        System.err.println("x1y2: " + countTrees(result));
    }

    private static long countTrees(List<MapObject> objects) {
        return objects.stream().filter(mapObject -> MapObjects.TREE == mapObject).count();
    }
}
