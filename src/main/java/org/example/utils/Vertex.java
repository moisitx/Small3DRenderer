package org.example.utils;

public class Vertex {
    private double x, y, z;

    public Vertex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getZ() {
        return z;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public static Vertex normalVector(Vertex common, Vertex v2, Vertex v3) {
        Vertex v1v2 = new Vertex(v2.x - common.x, v2.y - common.y, v2.z - common.z);
        Vertex v1v3 = new Vertex(v3.x - common.x, v3.y - common.y, v3.z - common.z);
        return new Vertex(
                v1v2.y*v1v3.z - v1v2.z*v1v3.y,
                v1v2.z*v1v3.x - v1v2.x*v1v3.z,
                v1v2.x*v1v3.y - v1v2.y*v1v3.x);
    }

    public static Vertex unitaryVector(Vertex v) {
        return new Vertex(
                v.x/Math.sqrt(v.x*v.x + v.y*v.y + v.z*v.z),
                v.y/Math.sqrt(v.x*v.x + v.y*v.y + v.z*v.z),
                v.z/Math.sqrt(v.x*v.x + v.y*v.y + v.z*v.z));
    }
}