package com.nhlstenden.designpatterns.graphics.shapes;

import com.nhlstenden.designpatterns.graphics.DrawingStrategy;
import javafx.geometry.Point2D;

public class ShapeDecorator {

    private Shape shape;

    public ShapeDecorator(Shape shape) {
        this.shape = shape;
    }
}
