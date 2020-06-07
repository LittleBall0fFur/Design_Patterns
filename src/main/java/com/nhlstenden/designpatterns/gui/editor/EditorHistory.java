package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.gui.editor.CanvasEditor.EditorContext;

import java.util.ArrayDeque;
import java.util.Deque;

public final class EditorHistory {

    private final EditorContext editorContext;

    private final Deque<EditorCommand> done = new ArrayDeque<EditorCommand>();
    private final Deque<EditorCommand> undone = new ArrayDeque<EditorCommand>();

    public EditorHistory(EditorContext editorContext) {
        this.editorContext = editorContext;
    }

    public boolean hasCommandsToUndo() {
        return !this.done.isEmpty();
    }

    public boolean hasCommandsToRedo() {
        return !this.undone.isEmpty();
    }

    public void register(EditorCommand command) {
        this.done.push(command);
        this.undone.clear();
    }

    public void undo() {
        if (this.hasCommandsToUndo() == false)
            return;

        EditorCommand command = this.done.pop();
        command.undo(this.editorContext);
        this.undone.push(command);
    }

    public void redo() {
        if (this.hasCommandsToRedo() == false)
            return;

        EditorCommand command = this.undone.pop();
        command.redo(this.editorContext);
        this.done.push(command);
    }

}
