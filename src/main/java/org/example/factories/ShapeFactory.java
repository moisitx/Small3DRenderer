package org.example.factories;

import org.example.shapes.IShape;
import org.example.shapes.Rectangle;
import org.example.shapes.Triangle;
import org.example.utils.Vertex;

import java.util.ArrayList;

public class ShapeFactory {
    public static IShape createShape(IShape shape, ArrayList<Vertex> transformedVertices) {
        if (shape instanceof Triangle) {
            return new Triangle(
                    transformedVertices.get(0),
                    transformedVertices.get(1),
                    transformedVertices.get(2),
                    shape.getColor()
            );
        } else if (shape instanceof Rectangle) {
            return new Rectangle(
                    transformedVertices.get(0),
                    transformedVertices.get(1),
                    transformedVertices.get(2),
                    transformedVertices.get(3),
                    shape.getColor()
            );
        }
        throw new IllegalArgumentException("Unsupported shape type: " + shape.getClass().getSimpleName());
    }
}
