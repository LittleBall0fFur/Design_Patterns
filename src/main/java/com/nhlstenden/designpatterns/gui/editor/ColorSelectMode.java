package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.graphics.shapes.Shape;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ColorSelectMode implements EditorMode {

    private static final ColorSelectMode __instance__ = new ColorSelectMode();

    private ColorSelectMode() {
        // Empty. Made private in favor of the Singleton Pattern.
    }

    public static ColorSelectMode getInstance() {
        return __instance__;
    }


    @Override
    public void handleMousePress(MouseEvent event, CanvasEditor.EditorContext context) {
        Shape shape = context.getCanvas().getShapeAt(event.getX(), event.getY());
        if (shape != null) context.setSelectedColor(shape.getColor());
    }

    @Override
    public void handleMouseDrag(MouseEvent event, CanvasEditor.EditorContext context) {
        Shape shape = context.getCanvas().getShapeAt(event.getX(), event.getY());
        if (shape != null) context.setSelectedColor(shape.getColor());
    }

    @Override
    public void handleMouseRelease(MouseEvent event, CanvasEditor.EditorContext context) {
        // Empty, do nothing.
    }

}
