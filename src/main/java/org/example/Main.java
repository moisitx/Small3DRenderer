package org.example;

import org.example.factories.RendererFactory;
import org.example.factories.ShapeFactory;
import org.example.shapes.IShape;
import org.example.shapes.ShapeRepository;
import org.example.utils.Matrix;
import org.example.utils.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Main {
    private static ArrayList<IShape> shapes = ShapeRepository.createCylinder(100);
    private static double lastX, lastY, rotAngleX, rotAngleY = 0;
    public static void main(String[] args) {

        JFrame frame = new JFrame("3D Shape renderer");
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // slider to control horizontal rotationd
        JSlider headingSlider = new JSlider(0, 360, 180);
        pane.add(headingSlider, BorderLayout.SOUTH);

        // slider to control vertical rotation
        JSlider pitchSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, 0);
        pane.add(pitchSlider, BorderLayout.EAST);

        // panel to display render results
        JPanel renderPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, getWidth(), getHeight());

                //Rotation matrix plane XZ
                double heading = Math.toRadians(headingSlider.getValue());
                Matrix rotationXZ = new Matrix(new double[] {
                        Math.cos(heading+rotAngleX), 0, -Math.sin(heading+rotAngleX),
                        0, 1, 0,
                        Math.sin(heading+rotAngleX), 0, Math.cos(heading+rotAngleX)
                });

                //Rotation matrix plane YZ
                double pitch = Math.toRadians(pitchSlider.getValue());
                Matrix rotationYZ = new Matrix(new double[]{
                        1, 0, 0,
                        0, Math.cos(pitch+rotAngleY), Math.sin(pitch+rotAngleY),
                        0, -Math.sin(pitch+rotAngleY), Math.cos(pitch+rotAngleY)
                });

                //Apply the transformation to the shapes, this happens everytime the slider is moved
                ArrayList<IShape> transformedShapes = new ArrayList<>();
                for (IShape shape : shapes) {
                    ArrayList<Vertex> transformedVertices = new ArrayList<>();
                    for (Vertex vertex : shape.getVertices()) {
                        transformedVertices.add(rotationYZ.transform(rotationXZ.transform(vertex)));
                    }
                    transformedShapes.add(ShapeFactory.createShape(shape, transformedVertices));
                }

                //Sort the transformed shapes by depth (Z) to ensure a correct rendering, further away objects should render first
                ArrayList<IShape> sortedTetrahedron = sortByZ(transformedShapes);

                //A translation is applied to displace the shapes towards the origin so it can be centered
                g2.translate(getWidth() / 2, getHeight() / 2);

                //Each shape is drawed
                for (IShape shape : sortedTetrahedron) {
                    RendererFactory.getRenderer(shape).render(g2, shape);
                }
            }
        };
        pane.add(renderPanel, BorderLayout.CENTER);

        headingSlider.addChangeListener(e -> renderPanel.repaint());
        pitchSlider.addChangeListener(e -> renderPanel.repaint());

        renderPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                lastY = e.getY();
                lastX = e.getX();
            }

        });

        renderPanel.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

                rotAngleX += Math.atan(e.getX() - lastX)*0.04;
                rotAngleY += Math.atan(e.getY() - lastY)*0.04;

                lastX = e.getX();
                lastY = e.getY();

                renderPanel.repaint();
            }

        });

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 6));

        // Buttons to select shapes
        JButton tetrahedronButton = new JButton("Tetrahedron");
        tetrahedronButton.addActionListener(e -> updateShape(ShapeRepository.createTetrahedron(), renderPanel));

        JButton cubeButton = new JButton("Cube");
        cubeButton.addActionListener(e -> updateShape(ShapeRepository.createCube(), renderPanel));

        JButton pyramidButton = new JButton("Pyramid");
        pyramidButton.addActionListener(e -> updateShape(ShapeRepository.createPyramid(), renderPanel));

        JButton octahedronButton = new JButton("Octahedron");
        octahedronButton.addActionListener(e -> updateShape(ShapeRepository.createOctahedron(), renderPanel));

        JButton prismButton = new JButton("Triangular Prism");
        prismButton.addActionListener(e -> updateShape(ShapeRepository.createTriangularPrism(), renderPanel));

        JButton cylinderButton = new JButton("Cylinder");
        cylinderButton.addActionListener(e -> updateShape(ShapeRepository.createCylinder(100), renderPanel));

        // Add buttons to panel
        buttonPanel.add(tetrahedronButton);
        buttonPanel.add(cubeButton);
        buttonPanel.add(pyramidButton);
        buttonPanel.add(octahedronButton);
        buttonPanel.add(prismButton);
        buttonPanel.add(cylinderButton);

        pane.add(buttonPanel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    private static ArrayList<IShape> sortByZ(ArrayList<IShape> shapes) {
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

    private static void updateShape(ArrayList<IShape> newShapes, JPanel renderPanel) {
        shapes = newShapes;
        renderPanel.repaint();
    }

}