package org.example;

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

        path.moveTo(vertices.get(0).x, vertices.get(0).y);
        path.lineTo(vertices.get(1).x, vertices.get(1).y);
        path.lineTo(vertices.get(2).x, vertices.get(2).y);
        path.closePath();
        g2.setColor(Shader.getShade(unitary, shape.getColor()));
        g2.fill(path);
        g2.setColor(Shader.getShade(unitary, shape.getColor()));
        g2.draw(path);
    }
}
