package org.example.utils;

public class Vertex {
    private double x, y, z;

    public Vertex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }


    public Vertex subtract(Vertex v) {
        return new Vertex(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    public static Vertex normalVector(Vertex common, Vertex v2, Vertex v3) {
        Vertex v1v2 = v2.subtract(common);
        Vertex v1v3 = v3.subtract(common);
        return new Vertex(
                v1v2.y * v1v3.z - v1v2.z * v1v3.y,
                v1v2.z * v1v3.x - v1v2.x * v1v3.z,
                v1v2.x * v1v3.y - v1v2.y * v1v3.x
        );
    }

    public static Vertex unitaryVector(Vertex v) {
        double length = Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
        if (length == 0) {
            throw new ArithmeticException("Cannot normalize a zero-length vector");
        }
        double invLength = 1.0 / length;
        return new Vertex(v.x * invLength, v.y * invLength, v.z * invLength);
    }
}
