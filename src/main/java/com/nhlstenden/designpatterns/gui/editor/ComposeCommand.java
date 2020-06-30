package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.graphics.shapes.Composition;
import com.nhlstenden.designpatterns.graphics.shapes.Shape;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class ComposeCommand implements EditorCommand {

    private Point2D lower_bound = null;
    private Point2D upper_bound = null;

    private List<Shape> ungrouped_shapes = null;
    private Composition grouped_shapes = null;

    @Override
    public void execute(CanvasEditor.EditorContext context) {
        var event_type = context.getCurrentMouseEvent().getEventType();
        if (event_type == MouseEvent.MOUSE_PRESSED) {
            lower_bound = context.getCurrentMousePosition();
            upper_bound = context.getCurrentMousePosition();
        } else if (event_type == MouseEvent.MOUSE_DRAGGED) {
            upper_bound = context.getCurrentMousePosition();
        } else if (event_type == MouseEvent.MOUSE_RELEASED) {
            ungrouped_shapes = context.getCanvas().getShapesBetween(lower_bound, upper_bound);
            if (ungrouped_shapes.size() != 0) {
                grouped_shapes = new Composition(ungrouped_shapes);
                context.getCanvas().removeShapes(ungrouped_shapes);
                context.getCanvas().addShape(grouped_shapes);
            }
        }
    }

    @Override
    public void undo(CanvasEditor.EditorContext context) {
        context.getCanvas().removeShape(grouped_shapes);
        context.getCanvas().addShapes(ungrouped_shapes);
    }

    @Override
    public void redo(CanvasEditor.EditorContext context) {
        context.getCanvas().removeShapes(ungrouped_shapes);
        context.getCanvas().addShape(grouped_shapes);
    }

    @Override
    public EditorCommand clone() {
        return new ComposeCommand();
    }

}
