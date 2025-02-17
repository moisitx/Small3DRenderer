package org.example.render;

import org.example.utils.Vertex;
import org.example.shapes.IShape;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class TriangleRenderer implements IRenderer {
    @Override
    public void render(Graphics g, IShape shape) {
        Graphics2D g2 = (Graphics2D) g;
        Path2D path = new Path2D.Double();

        ArrayList<Vertex> vertices = shape.getVertices();
        if (vertices.size() != 3) {
            throw new IllegalArgumentException("TriangleRenderer expects a shape with exactly 3 vertices.");
        }

        Vertex unitary = Vertex.unitaryVector(Vertex.normalVector(vertices.get(0), vertices.get(1), vertices.get(2)));

        path.moveTo(vertices.get(0).getX(), vertices.get(0).getY());
        path.lineTo(vertices.get(1).getX(), vertices.get(1).getY());
        path.lineTo(vertices.get(2).getX(), vertices.get(2).getY());
        path.closePath();
        g2.setColor(Shader.getShade(unitary, shape.getColor()));
        g2.fill(path);
        g2.setColor(Shader.getShade(unitary, shape.getColor()));
        g2.draw(path);
    }
}
