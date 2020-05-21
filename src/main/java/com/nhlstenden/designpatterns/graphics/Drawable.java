package com.nhlstenden.designpatterns.graphics;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class Drawable {

    private final DrawingStrategy drawingStrategy;

    /**
     * Constructs a Drawable instance to be used as a base class for anything which can be drawn.
     * The provided DrawingStrategy will be executed whenever the Drawable is requested to be drawn, using the static
     * 'draw' method.
     * @param drawingStrategy
     */
    protected Drawable(DrawingStrategy drawingStrategy) {
        this.drawingStrategy = drawingStrategy;
    }

    /**
     * Draw the provided Drawable instance, using the given GraphicsContext.
     * @param context The GraphicsContext in which to draw the Drawable.
     * @param drawable The Drawable to be drawn.
     */
    public static void draw(GraphicsContext context, Drawable drawable) {
        drawable.drawingStrategy.execute(context, drawable);
    }

}
