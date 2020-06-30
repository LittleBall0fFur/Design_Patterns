package com.nhlstenden.designpatterns.graphics.shapes;

import com.nhlstenden.designpatterns.graphics.Drawable;
import com.nhlstenden.designpatterns.graphics.DrawingStrategy;
import com.nhlstenden.designpatterns.graphics.ShapeVisitor;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public abstract class Shape extends Drawable {
    private Color color;

    private Point2D position;
    private double width, height;

    protected Shape(DrawingStrategy drawingStrategy) {
        this(Color.BLACK, new Point2D(0, 0), 0.0, 0.0, drawingStrategy);
    }

    protected Shape(Color color, Point2D position, double width, double height, DrawingStrategy drawingStrategy) {
        super(drawingStrategy);

        this.color = color;

        this.position = position;
        this.width = width;
        this.height = height;
    }

    /**
     * Determines whether a given point lies within this {@code Shape}'s bounds.
     * @param point The point to check.
     * @return {@code true} if the point lies within bounds, {@code false} otherwise.
     */
    public abstract boolean contains(Point2D point);

    public void setColor(Color new_color) {
        this.color = new_color;
    }

    public void setPosition(double x, double y) {
        this.position = new Point2D(x, y);
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public void setWidth(double new_width) {
        this.width = new_width;
    }

    public void setHeight(double new_height) {
        this.height = new_height;
    }

    public Color getColor() {
        return color;
    }

    public Point2D getPosition() {
        return position;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() { return height; }

    public abstract void accept(ShapeVisitor visitor);

    @Override
    public abstract Shape clone();

}
