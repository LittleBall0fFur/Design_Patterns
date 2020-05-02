package com.nhlstenden.designpatterns.graphics;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Rectangle extends BasicShape {

    public Rectangle(Point2D center, double width, double height) {
        super(center, width, height);
    }

    public javafx.scene.shape.Rectangle CreateRectangle(Point2D center, double width, double height, Color fill_color, Color border_color) {
        javafx.scene.shape.Rectangle r = new javafx.scene.shape.Rectangle();
        r.setX(center.getX());
        r.setY(center.getY());
        r.setWidth(width);
        r.setHeight(height);
        r.setFill(fill_color);

        return r;
    }
}
