package org.example.utils;

public class Matrix {
    private final double[] values;

    public Matrix(double[] values) {
        this.values = values;
    }

    public Vertex transform(Vertex in) {
        double x = in.getX(), y = in.getY(), z = in.getZ();
        return new Vertex(
                x * values[0] + y * values[3] + z * values[6],
                x * values[1] + y * values[4] + z * values[7],
                x * values[2] + y * values[5] + z * values[8]
        );
    }

}
