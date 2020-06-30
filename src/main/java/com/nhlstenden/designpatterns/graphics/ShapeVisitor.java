package com.nhlstenden.designpatterns.graphics;

import com.nhlstenden.designpatterns.graphics.shapes.CaptionDecorator;
import com.nhlstenden.designpatterns.graphics.shapes.Composition;
import com.nhlstenden.designpatterns.graphics.shapes.Ellipse;
import com.nhlstenden.designpatterns.graphics.shapes.Rectangle;

public interface ShapeVisitor {

    public void visit(Rectangle rectangle);
    public void visit(Ellipse ellipse);
    public void visit(Composition composition);
    public void visit(CaptionDecorator decorator);

}
