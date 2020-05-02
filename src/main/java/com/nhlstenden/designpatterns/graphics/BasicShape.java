package com.nhlstenden.designpatterns.graphics;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.layout.Region;
import javafx.scene.shape.*;

public abstract class BasicShape extends Region {

    private BoundingBox bounds;

    public BasicShape(Point2D center, double width, double height) {
        bounds = new BoundingBox((center.getX() -  width/2.0), (center.getY() - height/2.0), width, height);
    }

    public void select() {

    }

    public void scale() {

    }

    public void move() {

    }

}
