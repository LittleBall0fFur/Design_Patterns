package com.nhlstenden.designpatterns.gui.editor;

import javafx.scene.input.MouseEvent;

public interface EditorMode {

    public void handleMousePress(MouseEvent event, CanvasEditor.EditorContext context);
    public void handleMouseDrag(MouseEvent event, CanvasEditor.EditorContext context);
    public void handleMouseRelease(MouseEvent event, CanvasEditor.EditorContext context);

}
