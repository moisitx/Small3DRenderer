package org.example.shapes;

import org.example.utils.Vertex;

import java.awt.Color;
import java.util.ArrayList;

public class ShapeRepository {

    // Returns a tetrahedron made of triangles
    public static ArrayList<IShape> createTetrahedron() {
        ArrayList<IShape> tetrahedron = new ArrayList<>();
        tetrahedron.add(new Triangle(new Vertex(100, 100, 100),
                new Vertex(-100, -100, 100),
                new Vertex(-100, 100, -100),
                Color.WHITE));
        tetrahedron.add(new Triangle(new Vertex(100, 100, 100),
                new Vertex(-100, -100, 100),
                new Vertex(100, -100, -100),
                Color.RED));
        tetrahedron.add(new Triangle(new Vertex(-100, 100, -100),
                new Vertex(100, -100, -100),
                new Vertex(100, 100, 100),
                Color.GREEN));
        tetrahedron.add(new Triangle(new Vertex(-100, 100, -100),
                new Vertex(100, -100, -100),
                new Vertex(-100, -100, 100),
                Color.BLUE));
        return tetrahedron;
    }

    // Returns a rectangular cube made of rectangles
    public static ArrayList<IShape> createCube() {
        ArrayList<IShape> cube = new ArrayList<>();

        // Front face
        cube.add(new Rectangle(
                new Vertex(-100, -100, 100),
                new Vertex(100, -100, 100),
                new Vertex(100, 100, 100),
                new Vertex(-100, 100, 100),
                Color.RED
        ));

        // Back face
        cube.add(new Rectangle(
                new Vertex(-100, -100, -100),
                new Vertex(100, -100, -100),
                new Vertex(100, 100, -100),
                new Vertex(-100, 100, -100),
                Color.BLUE
        ));

        // Left face
        cube.add(new Rectangle(
                new Vertex(-100, -100, 100),
                new Vertex(-100, -100, -100),
                new Vertex(-100, 100, -100),
                new Vertex(-100, 100, 100),
                Color.GREEN
        ));

        // Right face
        cube.add(new Rectangle(
                new Vertex(100, -100, 100),
                new Vertex(100, -100, -100),
                new Vertex(100, 100, -100),
                new Vertex(100, 100, 100),
                Color.YELLOW
        ));

        // Top face
        cube.add(new Rectangle(
                new Vertex(-100, 100, 100),
                new Vertex(100, 100, 100),
                new Vertex(100, 100, -100),
                new Vertex(-100, 100, -100),
                Color.CYAN
        ));

        // Bottom face
        cube.add(new Rectangle(
                new Vertex(-100, -100, 100),
                new Vertex(100, -100, 100),
                new Vertex(100, -100, -100),
                new Vertex(-100, -100, -100),
                Color.MAGENTA
        ));

        return cube;
    }

    // Returns a pyramid made of triangles and a square base
    public static ArrayList<IShape> createPyramid() {
        ArrayList<IShape> pyramid = new ArrayList<>();

        // Base (square)
        pyramid.add(new Rectangle(
                new Vertex(-100, -100, 0),
                new Vertex(100, -100, 0),
                new Vertex(100, 100, 0),
                new Vertex(-100, 100, 0),
                Color.GRAY
        ));

        // Sides (triangles)
        pyramid.add(new Triangle(new Vertex(0, 0, 150), new Vertex(-100, -100, 0), new Vertex(100, -100, 0), Color.RED));
        pyramid.add(new Triangle(new Vertex(0, 0, 150), new Vertex(100, -100, 0), new Vertex(100, 100, 0), Color.GREEN));
        pyramid.add(new Triangle(new Vertex(0, 0, 150), new Vertex(100, 100, 0), new Vertex(-100, 100, 0), Color.BLUE));
        pyramid.add(new Triangle(new Vertex(0, 0, 150), new Vertex(-100, 100, 0), new Vertex(-100, -100, 0), Color.YELLOW));

        return pyramid;
    }

    public static ArrayList<IShape> createOctahedron() {
        ArrayList<IShape> octahedron = new ArrayList<>();

        // Top half
        octahedron.add(new Triangle(new Vertex(0, 0, 100), new Vertex(-100, -100, 0), new Vertex(100, -100, 0), Color.RED));
        octahedron.add(new Triangle(new Vertex(0, 0, 100), new Vertex(100, -100, 0), new Vertex(100, 100, 0), Color.GREEN));
        octahedron.add(new Triangle(new Vertex(0, 0, 100), new Vertex(100, 100, 0), new Vertex(-100, 100, 0), Color.BLUE));
        octahedron.add(new Triangle(new Vertex(0, 0, 100), new Vertex(-100, 100, 0), new Vertex(-100, -100, 0), Color.YELLOW));

        // Bottom half
        octahedron.add(new Triangle(new Vertex(0, 0, -100), new Vertex(-100, -100, 0), new Vertex(100, -100, 0), Color.CYAN));
        octahedron.add(new Triangle(new Vertex(0, 0, -100), new Vertex(100, -100, 0), new Vertex(100, 100, 0), Color.MAGENTA));
        octahedron.add(new Triangle(new Vertex(0, 0, -100), new Vertex(100, 100, 0), new Vertex(-100, 100, 0), Color.ORANGE));
        octahedron.add(new Triangle(new Vertex(0, 0, -100), new Vertex(-100, 100, 0), new Vertex(-100, -100, 0), Color.PINK));

        return octahedron;
    }

    public static ArrayList<IShape> createTriangularPrism() {
        ArrayList<IShape> prism = new ArrayList<>();

        // Base 1 (triangle)
        prism.add(new Triangle(new Vertex(-100, -100, 100), new Vertex(100, -100, 100), new Vertex(0, 100, 100), Color.RED));

        // Base 2 (triangle)
        prism.add(new Triangle(new Vertex(-100, -100, -100), new Vertex(100, -100, -100), new Vertex(0, 100, -100), Color.BLUE));

        // Side 1 (rectangle)
        prism.add(new Rectangle(
                new Vertex(-100, -100, 100),
                new Vertex(100, -100, 100),
                new Vertex(100, -100, -100),
                new Vertex(-100, -100, -100),
                Color.GREEN
        ));

        // Side 2 (rectangle)
        prism.add(new Rectangle(
                new Vertex(100, -100, 100),
                new Vertex(0, 100, 100),
                new Vertex(0, 100, -100),
                new Vertex(100, -100, -100),
                Color.YELLOW
        ));

        // Side 3 (rectangle)
        prism.add(new Rectangle(
                new Vertex(0, 100, 100),
                new Vertex(-100, -100, 100),
                new Vertex(-100, -100, -100),
                new Vertex(0, 100, -100),
                Color.CYAN
        ));

        return prism;
    }

    public static ArrayList<IShape> createCylinder(int sides) {
        ArrayList<IShape> cylinder = new ArrayList<>();
        double radius = 100;
        double height = 200;
        double angleIncrement = 2 * Math.PI / sides;

        // Side faces (rectangles)
        for (int i = 0; i < sides; i++) {
            double x1 = radius * Math.cos(i * angleIncrement);
            double y1 = radius * Math.sin(i * angleIncrement);
            double x2 = radius * Math.cos((i + 1) * angleIncrement);
            double y2 = radius * Math.sin((i + 1) * angleIncrement);

            cylinder.add(new Rectangle(
                    new Vertex(x1, y1, height / 2),
                    new Vertex(x2, y2, height / 2),
                    new Vertex(x2, y2, -height / 2),
                    new Vertex(x1, y1, -height / 2),
                    Color.ORANGE
            ));

            // Top face
            cylinder.add(new Triangle(
                    new Vertex(0, 0, height / 2),
                    new Vertex(x1, y1, height / 2),
                    new Vertex(x2, y2, height / 2),
                    Color.BLUE
            ));

            // Bottom face
            cylinder.add(new Triangle(
                    new Vertex(0, 0, -height / 2),
                    new Vertex(x1, y1, -height / 2),
                    new Vertex(x2, y2, -height / 2),
                    Color.RED
            ));
        }

        return cylinder;
    }
}