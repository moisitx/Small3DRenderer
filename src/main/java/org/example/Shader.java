package org.example;

import java.awt.*;

public class Shader {

    public static Color getShade(Vertex v, Color color) {
        Vertex unitary = Vertex.unitaryVector(v);
        double angleCos = Math.abs(unitary.z);

        double redLinear = Math.pow(color.getRed(), 2.4) * angleCos;
        double greenLinear = Math.pow(color.getGreen(), 2.4) * angleCos;
        double blueLinear = Math.pow(color.getBlue(), 2.4) * angleCos;

        int red = (int) Math.pow(redLinear, 1/2.4);
        int green = (int) Math.pow(greenLinear, 1/2.4);
        int blue = (int) Math.pow(blueLinear, 1/2.4);

        return new Color(red, green, blue);
    }
}
