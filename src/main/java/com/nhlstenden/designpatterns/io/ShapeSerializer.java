package com.nhlstenden.designpatterns.io;

import com.nhlstenden.designpatterns.graphics.ShapeVisitor;
import com.nhlstenden.designpatterns.graphics.shapes.*;

import java.util.Formatter;
import java.util.Locale;

public class ShapeSerializer implements ShapeVisitor {

    private final Formatter output;
    private int depth;

    public ShapeSerializer(StringBuilder builder) {
        this.output = new Formatter(builder, Locale.US);
        this.depth = 0;
    }

    @Override
    public void visit(Rectangle rectangle) {
        output.format("%srectangle (%f %f) (%f %f) (%f %f %f)\n",
                this.getCurrentIndentation(),
                rectangle.getWidth(), rectangle.getHeight(),
                rectangle.getPosition().getX(), rectangle.getPosition().getY(),
                rectangle.getColor().getRed(), rectangle.getColor().getGreen(), rectangle.getColor().getBlue()
        );
    }

    @Override
    public void visit(Ellipse ellipse) {
        output.format("%sellipse (%f %f) (%f %f) (%f %f %f)\n",
                this.getCurrentIndentation(),
                ellipse.getWidth(), ellipse.getHeight(),
                ellipse.getPosition().getX(), ellipse.getPosition().getY(),
                ellipse.getColor().getRed(), ellipse.getColor().getGreen(), ellipse.getColor().getBlue()
        );
    }

    @Override
    public void visit(Composition composition) {
        output.format(this.getCurrentIndentation() + "composition\n");

        depth++;
        for (Shape shape : composition.getShapes())
            shape.accept(this);

        depth--;
    }

    @Override
    public void visit(CaptionDecorator decorator) {
        output.format("%scaption %s \"%s\"\n",
                this.getCurrentIndentation(),
                decorator.getCaptionPosition(),
                decorator.getCaption()
        );

        depth++;
        decorator.getDecoratedShape().accept(this);
        depth--;
    }

    private String getCurrentIndentation() {
        return "\t".repeat(this.depth);
    }

}
