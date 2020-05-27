package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.graphics.shapes.Shape;
import com.nhlstenden.designpatterns.gui.editor.CanvasEditor.EditorContext;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class DrawMode implements EditorMode {

    private static final DrawMode __instance__ = new DrawMode();

    private DrawMode() {
        // Empty. Made private in favor of the Singleton Pattern.
    }

    public static DrawMode getInstance() {
        return __instance__;
    }

    @Override
    public void handleMousePress(MouseEvent event, EditorContext context) {
        if (context.getShapePrototype() == null)
            return;

        Shape shape = context.getShapePrototype().clone();
        shape.setColor(context.getSelectedColor());
        shape.setPosition(event.getX(), event.getY());

        context.getCanvas().addShape(shape);
        context.setSelectedShape(shape);
    }

    @Override
    public void handleMouseDrag(MouseEvent event, EditorContext context) {
        if (context.getSelectedShape() == null)
            return;

        Shape current_shape = context.getSelectedShape();
        Point2D distance = new Point2D(event.getX(), event.getY()).subtract(current_shape.getPosition());

        if (event.isShiftDown()) {
            current_shape.setWidth(Math.min(distance.getX(), distance.getY()));
            current_shape.setHeight(Math.min(distance.getX(), distance.getY()));
        } else {
            current_shape.setWidth(distance.getX());
            current_shape.setHeight(distance.getY());
        }

        context.getCanvas().present();
    }

    @Override
    public void handleMouseRelease(MouseEvent event, EditorContext context) {
        context.setSelectedShape(null);
    }

}
