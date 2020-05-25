package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.graphics.shapes.Shape;
import com.nhlstenden.designpatterns.gui.editor.CanvasEditor.EditorContext;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class ResizeMode implements EditorMode {

    private static final ResizeMode __instance__ = new ResizeMode();

    private ResizeMode() {
        // Empty. Made private in favor of the Singleton Pattern.
    }

    public static ResizeMode getInstance() {
        return __instance__;
    }

    @Override
    public void handleMousePress(MouseEvent event, EditorContext context) {
        context.setSelectedShape(context.getCanvas().getShapeAt(event.getX(), event.getY()));
    }

    @Override
    public void handleMouseDrag(MouseEvent event, EditorContext context) {
        if (context.getSelectedShape() == null)
            return;

        Point2D distance = new Point2D(event.getX(), event.getY()).subtract(context.getSelectedShape().getPosition());

        if (event.isShiftDown()) {
            context.getSelectedShape().setWidth(Math.min(distance.getX(), distance.getY()));
            context.getSelectedShape().setHeight(Math.min(distance.getX(), distance.getY()));
        } else {
            context.getSelectedShape().setWidth(distance.getX());
            context.getSelectedShape().setHeight(distance.getY());
        }

        context.getCanvas().present();
    }

    @Override
    public void handleMouseRelease(MouseEvent event, EditorContext context) {
        context.setSelectedShape(null);
    }

}
