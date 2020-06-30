package com.nhlstenden.designpatterns.graphics.shapes;

import com.nhlstenden.designpatterns.graphics.DrawingStrategy;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public abstract class ShapeDecorator extends Shape {

    private final Shape shape;

    protected ShapeDecorator(Shape shape, DrawingStrategy drawing_strategy) {
        super(drawing_strategy);
        this.shape = shape;
    }

    public Shape getDecoratedShape() {
        return shape;
    }

    @Override
    public boolean contains(Point2D point) {
        return this.shape.contains(point);
    }

    @Override
    public void setColor(Color new_color) {
        this.shape.setColor(new_color);
    }

    @Override
    public void setPosition(double x, double y) {
        this.shape.setPosition(x, y);
    }

    @Override
    public void setPosition(Point2D position) {
        this.shape.setPosition(position);
    }

    @Override
    public void setWidth(double new_width) {
        this.shape.setWidth(new_width);
    }

    @Override
    public void setHeight(double new_height) {
        this.shape.setHeight(new_height);
    }

    @Override
    public Color getColor() {
        return this.shape.getColor();
    }

    @Override
    public Point2D getPosition() {
        return this.shape.getPosition();
    }

    @Override
    public double getWidth() {
        return this.shape.getWidth();
    }

    @Override
    public double getHeight() {
        return this.shape.getHeight();
    }

    @Override
    public Shape clone() {
        return this.shape.clone();
    }

}
