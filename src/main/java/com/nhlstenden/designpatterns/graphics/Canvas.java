package com.nhlstenden.designpatterns.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Canvas extends Region {

    private javafx.scene.canvas.Canvas canvas;
    private GraphicsContext context;

    private List<Drawable> drawables;

    public Canvas(double width, double height) {
        this.setWidth(width);
        this.setHeight(height);

        this.canvas = new javafx.scene.canvas.Canvas();
        this.context = this.canvas.getGraphicsContext2D();
        this.drawables = new ArrayList<Drawable>();

        // Bind the Wrapper Canvas Instance to it's internal JavaFX Canvas.
        this.canvas.widthProperty().bind(this.widthProperty());
        this.canvas.heightProperty().bind(this.heightProperty());

        this.clear();

        this.getChildren().add(canvas);
    }

    public void addShapes(Drawable... drawables) {
        for (Drawable drawable : drawables)
            addShape(drawable);
    }

    public void addShape(Drawable drawable) {
        this.drawables.add(drawable);
    }

    // TODO: Works for now, but replace with a better way of drawing; Observer Pattern?
    public void present() {
        this.clear();

        for (Drawable drawable : this.drawables)
            Drawable.draw(this.context, drawable);
    }

    private void clear() {
        this.context.clearRect(0, 0, this.getWidth(), this.getHeight());
    }

}
