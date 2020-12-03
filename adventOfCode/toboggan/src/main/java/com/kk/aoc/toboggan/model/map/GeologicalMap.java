package com.kk.aoc.toboggan.model.map;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class GeologicalMap {
    private final Map<String, MapLayer> layers = new HashMap<>();

    private final int xSize;
    private final int ySize;

    public void addLayer(MapLayer layer) {
        Preconditions.checkNotNull(layer.getLayerName());
        Preconditions.checkArgument(layer.getXLength() == xSize, "Incorrect x size");
        Preconditions.checkArgument(layer.getYLength() == ySize, "Incorrect y size");

        layers.put(layer.getLayerName(), layer);
    }

    public Optional<MapLayer> getLayer(String layerName) {
        Preconditions.checkNotNull(layerName);
        return Optional.ofNullable(layers.get(layerName));
    }

    public MapObject get(String layerName, Coordinates coordinates) {
        return getLayer(layerName).map(layer -> layer.get(coordinates)).orElse(null);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String layerName : layers.keySet()) {
            builder.append(String.format("-------------%s------------\n", layerName));
            for (int yi = 0; yi < ySize; yi++) {
                for (int xi = 0; xi < xSize; xi++) {
                    builder.append(get(layerName, Coordinates.builder().x(xi).y(yi).build()).getMarker());
                }
                builder.append("\n");
            }
            builder.append("_______________________________");
        }
        return builder.toString();
    }
}
