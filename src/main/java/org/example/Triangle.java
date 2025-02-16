package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Triangle implements IShape {
    private final Vertex v1;
    private final Vertex v2;
    private final Vertex v3;
    private final Color color;
    Triangle(Vertex v1, Vertex v2, Vertex v3, Color color) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public double getAverageZ() {
        return (v1.z + v2.z + v3.z) / 3;
    }

    @Override
    public ArrayList<Vertex> getVertices() {
        return new ArrayList<>(Arrays.asList(v1, v2, v3));
    }
}
