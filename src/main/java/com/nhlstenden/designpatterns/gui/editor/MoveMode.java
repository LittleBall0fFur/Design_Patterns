package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.gui.editor.CanvasEditor.EditorContext;
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
