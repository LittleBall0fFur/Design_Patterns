package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.graphics.shapes.Shape;
import com.nhlstenden.designpatterns.gui.editor.CanvasEditor.EditorContext;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class ResizeCommand implements EditorCommand {

    private Shape resizedShape;

    private double original_width, original_height;
    private double modified_width, modified_height;

    @Override
    public void execute(EditorContext context) {
        if (resizedShape == null) {
            this.resizedShape = context.getCanvas().getShapeAt(context.getCurrentMousePosition());

            this.original_width = this.resizedShape.getWidth();
            this.original_height = this.resizedShape.getHeight();

            this.modified_width = this.resizedShape.getWidth();
            this.modified_height = this.resizedShape.getHeight();
        } else {
            Point2D distance = context.getCurrentMousePosition().subtract(resizedShape.getPosition());

            if (context.getCurrentMouseEvent().isShiftDown()) {
                this.resizedShape.setWidth(Math.min(distance.getX(), distance.getY()));
                this.resizedShape.setHeight(Math.min(distance.getX(), distance.getY()));
            } else {
                this.resizedShape.setWidth(distance.getX());
                this.resizedShape.setHeight(distance.getY());
            }

            this.modified_width = this.resizedShape.getWidth();
            this.modified_height = this.resizedShape.getHeight();

            context.getCanvas().present();
        }
    }

    @Override
    public void undo(EditorContext context) {
        this.resizedShape.setWidth(this.original_width);
        this.resizedShape.setHeight(this.original_height);

        context.getCanvas().present();
    }

    @Override
    public void redo(EditorContext context) {
        this.resizedShape.setWidth(this.modified_width);
        this.resizedShape.setHeight(this.modified_height);

        context.getCanvas().present();
    }

    @Override
    public ResizeCommand clone() {
        return new ResizeCommand();
    }

}
