package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.graphics.shapes.Shape;
import com.nhlstenden.designpatterns.gui.editor.CanvasEditor.EditorContext;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class EraseCommand implements EditorCommand {

    private final List<Shape> erasedShapes = new ArrayList<Shape>();

    @Override
    public void execute(EditorContext context) {
        Shape erasedShape = context.getCanvas().removeShapeAt(context.getCurrentMousePosition());
        if (erasedShape != null) this.erasedShapes.add(erasedShape);
    }

    @Override
    public void undo(EditorContext context) {
        context.getCanvas().addShapes(this.erasedShapes);
    }

    @Override
    public void redo(EditorContext context) {
        context.getCanvas().removeShapes(this.erasedShapes);
    }

    @Override
    public EraseCommand clone() {
        return new EraseCommand();
    }

}
