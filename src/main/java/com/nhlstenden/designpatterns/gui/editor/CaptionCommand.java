package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.graphics.shapes.CaptionDecorator;
import com.nhlstenden.designpatterns.graphics.shapes.Shape;
import com.nhlstenden.designpatterns.gui.GUIFactory;
import com.nhlstenden.designpatterns.gui.editor.CanvasEditor.EditorContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class CaptionCommand implements EditorCommand {
    private Shape caption_shape;
    private CaptionDecorator decorator;

    private CanvasEditor canvas_editor = CanvasEditor.getInstance();

    private static Button top, bottom, left, right;

    @Override
    public void execute(EditorContext context) {
        caption_shape = context.getCanvas().getShapeAt(context.getCurrentMousePosition());
        if (caption_shape == null) return;


        createPlacementButtons(context, caption_shape);
    }

    @Override
    public void undo(EditorContext context) {
        context.getCanvas().removeShape(this.decorator);
        context.getCanvas().addShape(this.caption_shape);
    }

    @Override
    public void redo(EditorContext context) {
        context.getCanvas().removeShape(this.caption_shape);
        context.getCanvas().addShape(this.decorator);
    }

    @Override
    public CaptionCommand clone() {
        return new CaptionCommand();
    }

    private void createPlacementButtons(EditorContext context, Shape shape) {
        ((AnchorPane) canvas_editor.getRoot()).getChildren().remove(top);
        ((AnchorPane) canvas_editor.getRoot()).getChildren().remove(bottom);
        ((AnchorPane) canvas_editor.getRoot()).getChildren().remove(left);
        ((AnchorPane) canvas_editor.getRoot()).getChildren().remove(right);

        top = GUIFactory.createCanvasButton("add", "Place caption here...", shape.getPosition().getX() + (shape.getWidth()/2), shape.getPosition().getY() - 20, event -> {
           this.clicked(context, CaptionDecorator.Position.TOP);
        });
        ((AnchorPane) canvas_editor.getRoot()).getChildren().add(top);

        bottom = GUIFactory.createCanvasButton("add", "Place caption here...", shape.getPosition().getX() + (shape.getWidth()/2), (shape.getPosition().getY() + shape.getHeight()) + 20, event -> {
            this.clicked(context, CaptionDecorator.Position.BOTTOM);
        });
        ((AnchorPane) canvas_editor.getRoot()).getChildren().add(bottom);

        left = GUIFactory.createCanvasButton("add", "Place caption here...", shape.getPosition().getX() - 20, shape.getPosition().getY() + (shape.getHeight()/2), event -> {
            this.clicked(context, CaptionDecorator.Position.LEFT);
        });
        ((AnchorPane) canvas_editor.getRoot()).getChildren().add(left);

        right = GUIFactory.createCanvasButton("add", "Place caption here...", (shape.getPosition().getX() + shape.getWidth()) + 20, shape.getPosition().getY() + (shape.getHeight()/2),  event -> {
            this.clicked(context, CaptionDecorator.Position.RIGHT);
        });
        ((AnchorPane) canvas_editor.getRoot()).getChildren().add(right);
    }

    private void clicked(EditorContext context, CaptionDecorator.Position position){
        double x = 0, y = 0;
        switch(position){
            case TOP:
                x = top.getTranslateX() - 65;
                y = top.getTranslateY();
                break;
            case BOTTOM:
                x = bottom.getTranslateX() - 65;
                y = bottom.getTranslateY();
                break;
            case LEFT:
                x = left.getTranslateX() - 130;
                y = left.getTranslateY();
                break;
            case RIGHT:
                x = right.getTranslateX();
                y = right.getTranslateY();
                break;
        }
        ((AnchorPane) canvas_editor.getRoot()).getChildren().remove(top);
        ((AnchorPane) canvas_editor.getRoot()).getChildren().remove(bottom);
        ((AnchorPane) canvas_editor.getRoot()).getChildren().remove(left);
        ((AnchorPane) canvas_editor.getRoot()).getChildren().remove(right);

        TextField textField = GUIFactory.createTextField("Enter text here...", x, y);
        textField.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.ENTER) {
                this.decorator = new CaptionDecorator(this.caption_shape, textField.getText(), position);
                context.getCanvas().removeShape(this.caption_shape);
                context.getCanvas().addShape(this.decorator);
                ((AnchorPane) canvas_editor.getRoot()).getChildren().remove(textField);
            }
        });
        ((AnchorPane) canvas_editor.getRoot()).getChildren().add(textField);
    }

}
