package com.kk.aoc.toboggan.model.map;

import com.kk.aoc.toboggan.model.trajectory.Trajectory;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MapLayer {
    private final String layerName;
    private final MapObject[][] objects;

    public String getLayerName() {
        return layerName;
    }

    public MapObject get(Coordinates coordinates) {
        return objects[coordinates.getY() % getYLength()][coordinates.getX() % getXLength()];
    }

    public int getXLength() {
        if (objects.length > 0) {
            return objects[0].length;
        }
        return -1;
    }

    public int getYLength() {
        return objects.length;
    }

    public List<MapObject> checkTrajectory(Trajectory trajectory) {
        List<MapObject> results = new ArrayList<>();
        while (trajectory.hasNext()) {
            Coordinates coordinates = trajectory.next();
            results.add(get(coordinates));
        }
        return results;
    }
}
