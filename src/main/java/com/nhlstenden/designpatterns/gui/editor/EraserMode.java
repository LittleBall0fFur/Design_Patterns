package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.gui.editor.CanvasEditor.EditorContext;
import javafx.scene.input.MouseEvent;

public class EraserMode implements EditorMode {

    private static final EraserMode __instance__ = new EraserMode();

    private EraserMode() {
        // Empty. Made private in favor of the Singleton Pattern.
    }

    public static EraserMode getInstance() {
        return __instance__;
    }

    @Override
    public void handleMousePress(MouseEvent event, EditorContext context) {
        context.getCanvas().removeShapeAt(event.getX(), event.getY());
    }

    @Override
    public void handleMouseDrag(MouseEvent event, EditorContext context) {
        context.getCanvas().removeShapeAt(event.getX(), event.getY());
    }

    @Override
    public void handleMouseRelease(MouseEvent event, EditorContext context) {
        // Do nothing, yet.
    }

}
