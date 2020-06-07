package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.gui.editor.CanvasEditor.EditorContext;

public interface EditorCommand {

    public void execute(EditorContext context);

    public void undo(EditorContext context);
    public void redo(EditorContext context);

    public EditorCommand clone();

}
