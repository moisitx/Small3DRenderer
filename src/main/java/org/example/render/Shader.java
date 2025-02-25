package org.example.render;

import org.example.utils.Vertex;

import java.awt.Color;

public class Shader {
    private static final double GAMMA = 2.4;
    private static final double INV_GAMMA = 1.0 / GAMMA;

    public static Color getShade(Vertex v, Color color) {
        Vertex normal = Vertex.unitaryVector(v);
        double angleCos = Math.abs(normal.getZ());

        int red = applyShading(color.getRed(), angleCos);
        int green = applyShading(color.getGreen(), angleCos);
        int blue = applyShading(color.getBlue(), angleCos);

        return new Color(red, green, blue);
    }

    private static int applyShading(int colorValue, double factor) {
        double linearValue = Math.pow(colorValue, GAMMA) * factor;
        int correctedValue = (int) Math.pow(linearValue, INV_GAMMA);
        return Math.min(255, Math.max(0, correctedValue));
    }
}

