package org.example.render;

import org.example.shapes.IShape;

import java.awt.*;

public interface IRenderer {
    void render(Graphics g2, IShape shape);
}
