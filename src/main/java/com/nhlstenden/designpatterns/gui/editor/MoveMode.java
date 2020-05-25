package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.gui.editor.CanvasEditor.EditorContext;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class MoveMode implements EditorMode {

    private static final MoveMode __instance__ = new MoveMode();

    private MoveMode() {
        // Empty. Made private in favor of the Singleton Pattern.
    }

    public static MoveMode getInstance() {
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

        Point2D distance = new Point2D(event.getX(), event.getY()).subtract(context.getLastMousePosition());
        context.getSelectedShape().setPosition(context.getSelectedShape().getPosition().add(distance));
        context.getCanvas().present();
    }

    @Override
    public void handleMouseRelease(MouseEvent event, EditorContext context) {
        context.setSelectedShape(null);
    }

}
