package com.nhlstenden.designpatterns.graphics.shapes;

import com.nhlstenden.designpatterns.graphics.Drawable;
import com.nhlstenden.designpatterns.graphics.DrawingStrategy;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.layout.Region;
import javafx.scene.shape.*;

public abstract class Shape extends Drawable {

    private Point2D position;
    private double width, height;

    protected Shape(Point2D position, double width, double height, DrawingStrategy drawingStrategy) {
        super(drawingStrategy);

        this.position = position;
        this.width = width;
        this.height = height;
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

    public Point2D getPosition() {
        return position;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void select() {

    }

    public void scale() {

    }

    public void move() {

    }

}
