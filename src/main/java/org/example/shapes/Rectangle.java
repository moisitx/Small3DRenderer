package org.example.shapes;

import org.example.utils.Vertex;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Rectangle implements IShape {
    private final Vertex v1;
    private final Vertex v2;
    private final Vertex v3;
    private final Vertex v4;
    private final Color color;

    public Rectangle(Vertex v1, Vertex v2, Vertex v3, Vertex v4, Color color) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public double getAverageZ(){
        return (v1.getZ() + v2.getZ() + v3.getZ() + v4.getZ()) / 4;
    }

    @Override
    public ArrayList<Vertex> getVertices() {
        return new ArrayList<>(Arrays.asList(v1, v2, v3, v4));
    }
}
