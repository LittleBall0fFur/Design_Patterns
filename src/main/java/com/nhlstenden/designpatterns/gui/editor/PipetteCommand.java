package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.graphics.shapes.Shape;
import com.nhlstenden.designpatterns.gui.editor.CanvasEditor.EditorContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class PipetteCommand implements EditorCommand {

    private Color original_color;
    private Color modified_color;

    private List<Shape> modifiedShapes = new ArrayList<Shape>();
    private List<Color> originalColors = new ArrayList<Color>();
    private List<Color> modifiedColors = new ArrayList<Color>();

    @Override
    public void execute(EditorContext context) {
        if (this.original_color == null) {
            this.original_color = context.getSelectedColor();
            this.modified_color = context.getSelectedColor();
        }

        Shape shape = context.getCanvas().getShapeAt(context.getCurrentMousePosition());
        if (shape == null) return;

        if (context.getCurrentMouseEvent().getButton() == MouseButton.PRIMARY) {
            context.setSelectedColor(shape.getColor());
            this.modified_color = shape.getColor();
        } else if (context.getCurrentMouseEvent().getButton() == MouseButton.SECONDARY) {
            if (!this.modifiedShapes.contains(shape)) {
                this.modifiedShapes.add(shape);
                this.originalColors.add(shape.getColor());
                this.modifiedColors.add(context.getSelectedColor());

                shape.setColor(context.getSelectedColor());
                context.getCanvas().present();
            }
        }
    }

    @Override
    public void undo(EditorContext context) {
        context.setSelectedColor(original_color);

        for (int i = 0; i < this.modifiedShapes.size(); i++)
            this.modifiedShapes.get(i).setColor(this.originalColors.get(i));

        context.getCanvas().present();
    }

    @Override
    public void redo(EditorContext context) {
        context.setSelectedColor(modified_color);

        for (int i = 0; i < this.modifiedShapes.size(); i++)
            this.modifiedShapes.get(i).setColor(this.modifiedColors.get(i));

        context.getCanvas().present();
    }

    @Override
    public PipetteCommand clone() {
        return new PipetteCommand();
    }

}
