package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.graphics.shapes.Shape;
import com.nhlstenden.designpatterns.gui.editor.CanvasEditor.EditorContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class PipetteMode implements EditorMode {

    private static final PipetteMode __instance__ = new PipetteMode();

    private PipetteMode() {
        // Empty. Made private in favor of the Singleton Pattern.
    }

    public static PipetteMode getInstance() {
        return __instance__;
    }

    @Override
    public void handleMousePress(MouseEvent event, EditorContext context) {
        Shape shape = context.getCanvas().getShapeAt(event.getX(), event.getY());
        if (shape == null) return;

        if (event.getButton() == MouseButton.PRIMARY) {
            context.setSelectedColor(shape.getColor());
        } else if (event.getButton() == MouseButton.SECONDARY) {
            shape.setColor(context.getSelectedColor());
        }

        context.getCanvas().present();
    }

    @Override
    public void handleMouseDrag(MouseEvent event, EditorContext context) {
        Shape shape = context.getCanvas().getShapeAt(event.getX(), event.getY());
        if (shape == null) return;

        if (event.getButton() == MouseButton.PRIMARY) {
            context.setSelectedColor(shape.getColor());
        } else if (event.getButton() == MouseButton.SECONDARY) {
            shape.setColor(context.getSelectedColor());
        }

        context.getCanvas().present();
    }

    @Override
    public void handleMouseRelease(MouseEvent event, EditorContext context) {
        // Do nothing, yet.
    }

}
