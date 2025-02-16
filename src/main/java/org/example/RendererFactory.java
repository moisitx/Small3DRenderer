package org.example;

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
