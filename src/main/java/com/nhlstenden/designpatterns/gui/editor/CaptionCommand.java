package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.gui.editor.CanvasEditor.EditorContext;

public class CaptionCommand implements EditorCommand {

    @Override
    public void execute(EditorContext context) {

    }

    @Override
    public void undo(EditorContext context) {

    }

    @Override
    public void redo(EditorContext context) {

    }

    @Override
    public CaptionCommand clone() {
        return new CaptionCommand();
    }

}
