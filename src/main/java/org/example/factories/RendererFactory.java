package org.example.factories;

import org.example.render.IRenderer;
import org.example.render.RectangleRenderer;
import org.example.render.TriangleRenderer;
import org.example.shapes.IShape;
import org.example.shapes.Rectangle;
import org.example.shapes.Triangle;

public class RendererFactory {
    public static IRenderer getRenderer(IShape shape) {
        if(shape instanceof Rectangle) {
            return new RectangleRenderer();
        } else if (shape instanceof Triangle){
            return new TriangleRenderer();
        }else{
            throw new IllegalArgumentException("No renderer available for shape: " + shape.getClass().getSimpleName());
        }
    }
}
