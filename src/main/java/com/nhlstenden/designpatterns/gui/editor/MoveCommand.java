package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.graphics.shapes.Shape;
import com.nhlstenden.designpatterns.gui.editor.CanvasEditor.EditorContext;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class MoveCommand implements EditorCommand {

    private Shape movedShape;

    private Point2D original_position;
    private Point2D modified_position;

    @Override
    public void execute(EditorContext context) {
        if (this.movedShape == null) {
            this.movedShape = context.getCanvas().getShapeAt(context.getCurrentMousePosition());
            this.original_position = this.movedShape != null ? this.movedShape.getPosition() : null;
        } else {
            final Point2D distance = context.getCurrentMousePosition().subtract(context.getPreviousMousePosition());
            this.movedShape.setPosition(this.movedShape.getPosition().add(distance));
            this.modified_position = this.movedShape.getPosition();
            context.getCanvas().present();
        }
    }

    @Override
    public void undo(EditorContext context) {
        this.movedShape.setPosition(original_position);
        context.getCanvas().present();
    }

    @Override
    public void redo(EditorContext context) {
        this.movedShape.setPosition(modified_position);
        context.getCanvas().present();
    }

    @Override
    public MoveCommand clone() {
        return new MoveCommand();
    }

}
