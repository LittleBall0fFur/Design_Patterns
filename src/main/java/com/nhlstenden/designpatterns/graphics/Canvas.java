package com.nhlstenden.designpatterns.graphics;

import com.nhlstenden.designpatterns.graphics.shapes.Shape;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.*;

public class Canvas extends Region {

    private javafx.scene.canvas.Canvas canvas;
    private GraphicsContext context;

    private List<Drawable> drawables;

    public Canvas(double width, double height) {
        this();

        this.setWidth(width);
        this.setHeight(height);
    }

    public Canvas() {
        this.canvas = new javafx.scene.canvas.Canvas();
        this.context = this.canvas.getGraphicsContext2D();
        this.drawables = new ArrayList<Drawable>();

        // Bind the Wrapper Canvas Instance to it's internal JavaFX Canvas.
        this.canvas.widthProperty().bind(this.widthProperty());
        this.canvas.heightProperty().bind(this.heightProperty());

        this.clear();

        this.getChildren().add(canvas);
    }

    public Shape getShapeAt(double x, double y) {
        return this.getShapeAt(new Point2D(x, y));
    }

    /**
     * Returns the first {@code Shape} found at the given position.
     * @param position The position at which to look for shapes.
     * @return The {@code Shape} at the given position if found, {@code null} otherwise.
     */
    public Shape getShapeAt(Point2D position) {
        Shape result = null;
        for (int i = drawables.size()-1; i >= 0; i--) {
            if (!(drawables.get(i) instanceof Shape))
                continue;

            Shape current_shape = (Shape) drawables.get(i);
            if (current_shape.contains(position)) {
                result = current_shape;
                break;
            }
        }

        return result;
    }

    public Shape removeShapeAt(double x, double y) {
        return this.removeShapeAt(new Point2D(x, y));
    }

    public Shape removeShapeAt(Point2D position) {
        Shape result = null;
        for (int i = drawables.size()-1; i >= 0; i--) {
            if (!(drawables.get(i) instanceof Shape))
                continue;

            Shape current_shape = (Shape) drawables.get(i);
            if (current_shape.contains(position)) {
                result = current_shape;
                drawables.remove(i);
                break;
            }
        }

        this.present();

        return result;
    }

    public void addShapes(Shape... shapes) {
        this.addShapes(Arrays.asList(shapes));
    }

    public void addShapes(Collection<? extends Shape> shapes) {
        for (Shape shape : shapes)
            this.drawables.add(shape);

        // Update the Canvas.
        this.present();
    }

    public void addShape(Shape shape) {
        this.drawables.add(shape);

        // Update the Canvas.
        this.present();
    }

    public void removeShapes(Shape... shapes) {
        this.addShapes(Arrays.asList(shapes));
    }

    public void removeShapes(Collection<? extends Shape> shapes) {
        for (Shape shape : shapes)
            this.drawables.remove(shape);

        // Update the Canvas.
        this.present();
    }

    public void removeShape(Shape shape) {
        this.drawables.remove(shape);

        // Update the Canvas.
        this.present();
    }

    // TODO: Works for now, but replace with a better way of drawing; Observer Pattern?
    public void present() {
        this.clear();

        for (Drawable drawable : this.drawables)
            Drawable.draw(this.context, drawable);
    }

    private void clear() {
        this.context.setFill(Color.WHITE);
        this.context.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public List<Drawable> getDrawables(){
        return drawables;
    }

}
