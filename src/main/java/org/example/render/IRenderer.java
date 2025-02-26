package org.example.render;

import org.example.shapes.IShape;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface IRenderer {
    void render(Graphics g2, IShape shape, BufferedImage image);
}
