package org.example.shapes;

import org.example.utils.Vertex;

import java.awt.*;
import java.util.ArrayList;

public interface IShape {
    public  abstract Color getColor();
    public abstract double getAverageZ();
    public abstract ArrayList<Vertex> getVertices();

}
