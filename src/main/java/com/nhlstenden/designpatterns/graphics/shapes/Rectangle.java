package com.nhlstenden.designpatterns.graphics.shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends BasicShape {

    public Rectangle(GraphicsContext gc, Point2D center, double width, double height, Color fill_color, Color stroke_color) {
        super(center, width, height);
        gc.setFill(fill_color);
        gc.fillRect(center.getX(), center.getY(), width, height);
    }
}
