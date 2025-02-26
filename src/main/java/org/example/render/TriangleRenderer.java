package org.example.render;

import org.example.utils.Utilities;
import org.example.utils.Vertex;
import org.example.shapes.IShape;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TriangleRenderer implements IRenderer {
    @Override
    public void render(Graphics g, IShape shape, BufferedImage image) {
        Graphics2D g2 = (Graphics2D) g;
        Path2D path = new Path2D.Double();

        ArrayList<Vertex> vertices = shape.getVertices();
        if (vertices.size() != 3) {
            throw new IllegalArgumentException("TriangleRenderer expects a shape with exactly 3 vertices.");
        }

        Vertex unitary = Vertex.unitaryVector(Vertex.normalVector(vertices.get(0), vertices.get(1), vertices.get(2)));


        //I rasterize the triangles myself, less efficient, cylinder might be laggy
        drawTrianglePixels(vertices.get(0), vertices.get(1), vertices.get(2), image, Shader.getShade(unitary, shape.getColor()));
        g2.drawImage(image, 0, 0, null);


        //Previous approach using the Graphics2D library
        /*path.moveTo(vertices.get(0).getX(), vertices.get(0).getY());
        path.lineTo(vertices.get(1).getX(), vertices.get(1).getY());
        path.lineTo(vertices.get(2).getX(), vertices.get(2).getY());
        path.closePath();
        g2.setColor(Shader.getShade(unitary, shape.getColor()));
        g2.fill(path);
        g2.setColor(Shader.getShade(unitary, shape.getColor()));
        g2.draw(path);*/

    }

    public void drawTrianglePixels(Vertex a, Vertex b, Vertex c, BufferedImage image, Color color) {

        Vertex pixel = new Vertex(0,0,0);

        //Bounds of the square outside the triangle (Maxmimum and minimum values of the triangle)
        int maxY = (int) Math.max(0, Math.floor(Math.max(Math.max(a.getY(), b.getY()), c.getY())));
        int maxX = (int) Math.max(0, Math.floor(Math.max(Math.max(a.getX(), b.getX()), c.getX())));
        int minY = (int) Math.min(image.getHeight() - 1, Math.ceil(Math.min(Math.min(a.getY(), b.getY()), c.getY())));
        int minX = (int) Math.min(image.getWidth() - 1, Math.ceil(Math.min(Math.min(a.getX(), b.getX()), c.getX())));


        for (int i = minX; i < maxX; i++) {
            for (int j = minY; j < maxY; j++) {
                pixel.setX(i);
                pixel.setY(j);
                //Value is positive if the pixel is right of the line a->b
                double ab = Math.abs(Utilities.getInstance().shoelaceTriangleArea(a, b, pixel));
                double bc = Math.abs(Utilities.getInstance().shoelaceTriangleArea(b, c, pixel));
                double ca = Math.abs(Utilities.getInstance().shoelaceTriangleArea(c, a, pixel));


                //Approach comparing the three areas with the total one
                double totalArea = Math.abs(Utilities.getInstance().shoelaceTriangleArea(a, b, c));
                double epsilon = 1e-6; // Small tolerance for floating-point errors

                if (Math.abs((ab + bc + ca) - totalArea) < epsilon) {
                    image.setRGB(i, j, color.getRGB());
                }

                /*double ab = Utilities.getInstance().shoelaceTriangleArea(a, b, pixel);
                double bc = Utilities.getInstance().shoelaceTriangleArea(b, c, pixel);
                double ca = Utilities.getInstance().shoelaceTriangleArea(c, a, pixel);
                //Some of the triangles are being calculated counterclockwise so I check the opposite conditions for them so they are drawn
                if((ab>0 && bc>0 && ca>0) || ab<0 && bc<0 && ca<0){
                    image.setRGB(i,j, color.getRGB());
                }*/

            }
        }
    }
}
