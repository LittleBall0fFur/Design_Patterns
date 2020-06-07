package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.graphics.caption.Caption;
import com.nhlstenden.designpatterns.graphics.caption.ShapeCaptionDecorator;
import com.nhlstenden.designpatterns.graphics.shapes.Shape;
import com.nhlstenden.designpatterns.gui.editor.CanvasEditor.EditorContext;
import javafx.geometry.Point2D;

public class CaptionCommand implements EditorCommand {
    private Shape caption_shape;
    @Override
    public void execute(EditorContext context) {
        // bepaal of shape is aangeklikt
        // nee -> return
        // ja -> new ShapeCaptionDecorator(
        Shape shape = context.getCanvas().getShapeAt(context.getCurrentMousePosition());
        Caption caption = new ShapeCaptionDecorator(shape);
        caption.drawCaption();
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
