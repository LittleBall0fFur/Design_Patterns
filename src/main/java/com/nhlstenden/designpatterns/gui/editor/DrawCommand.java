package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.graphics.shapes.Shape;
import com.nhlstenden.designpatterns.gui.editor.CanvasEditor.EditorContext;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class DrawCommand implements EditorCommand {

    private final Shape prototype;

    private Shape drawnShape;

    public DrawCommand(Shape prototype) {
        this.prototype = prototype;
    }

    @Override
    public void execute(EditorContext context) {
        if (this.drawnShape == null) {
            this.drawnShape = this.prototype.clone();
            this.drawnShape.setColor(context.getSelectedColor());
            this.drawnShape.setPosition(context.getCurrentMousePosition());

            context.getCanvas().addShape(this.drawnShape);
        } else {
            Point2D distance = context.getCurrentMousePosition().subtract(this.drawnShape.getPosition());

            if (context.getCurrentMouseEvent().isShiftDown()) {
                this.drawnShape.setWidth(Math.min(distance.getX(), distance.getY()));
                this.drawnShape.setHeight(Math.min(distance.getX(), distance.getY()));
            } else {
                this.drawnShape.setWidth(distance.getX());
                this.drawnShape.setHeight(distance.getY());
            }

        }

        context.getCanvas().present();
    }

    @Override
    public void undo(EditorContext context) {
        context.getCanvas().removeShape(this.drawnShape);
    }

    @Override
    public void redo(EditorContext context) {
        context.getCanvas().addShape(this.drawnShape);
    }

    @Override
    public DrawCommand clone() {
        return new DrawCommand(this.prototype);
    }

}
