package org.example.utils;

public class Matrix {
    double[] values;
    public Matrix(double[] values) {
        this.values = values;
    }

    public Vertex transform(Vertex in) {
        return new Vertex(
                in.getX() * values[0] + in.getY() * values[3] + in.getZ() * values[6],
                in.getX() * values[1] + in.getY() * values[4] + in.getZ() * values[7],
                in.getX() * values[2] + in.getY() * values[5] + in.getZ() * values[8]
        );
    }
}