package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.gui.editor.CanvasEditor.EditorContext;
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
        // TODO: Implement
    }

    @Override
    public void handleMouseDrag(MouseEvent event, EditorContext context) {
        // TODO: Implement
    }

    @Override
    public void handleMouseRelease(MouseEvent event, EditorContext context) {
        // TODO: Implement
    }

}
