package org.example.utils;

import org.example.shapes.IShape;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Utilities {
    private static Utilities instance;

    private Utilities() {
    }

    public static Utilities getInstance() {
        if(instance == null) {
            instance = new Utilities();
        }
        return instance;
    }

    //Calculates double the area of the triangle (Dont need it to be the real area)
    public double shoelaceTriangleArea(Vertex a, Vertex b, Vertex c) {
        return a.getX()*b.getY() + b.getX()*c.getY() + c.getX()*a.getY() - (a.getY()*b.getX() + b.getY()*c.getX() + c.getY()*a.getX());
    }

    //Calculates double the trapezoid area
    public double shoelaceTrapezoidArea(Vertex a, Vertex b, Vertex c, Vertex d) {
        return a.getX()*b.getY() + b.getX()*c.getY() + c.getX()*d.getY() + d.getX()*a.getY() - (a.getY()*b.getX() + b.getY()*c.getX() + c.getY()*d.getX() + d.getY()*a.getX());
    }

    public ArrayList<IShape> sortByZ(ArrayList<IShape> shapes) {
        ArrayList<IShape> sorted = new ArrayList<>();
        ArrayList<IShape> temp = shapes;
        while (!shapes.isEmpty()) {
            double max = temp.getFirst().getAverageZ();
            int maxIndex = 0;
            for (int i = 1; i < temp.size(); i++) {
                double avgZ = temp.get(i).getAverageZ();
                if (avgZ > max) {
                    max = avgZ;
                    maxIndex = i;
                }
            }
            sorted.add(temp.get(maxIndex));
            temp.remove(maxIndex);
        }
        return sorted;

    }

    public void translateShapes(ArrayList<IShape> shapes, BufferedImage image) {
        for (IShape shape : shapes){
            for(Vertex vertex : shape.getVertices()){
                vertex.setX(vertex.getX() + (double) image.getWidth()/2);
                vertex.setY(vertex.getY() + (double) image.getHeight()/2);
            }
        }
    }
}
