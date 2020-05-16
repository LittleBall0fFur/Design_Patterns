package com.nhlstenden.designpatterns.graphics;

import javafx.scene.canvas.GraphicsContext;

public interface DrawingStrategy {

    /**
     * Execute the DrawingStrategy, drawing the provided drawable in the given GraphicsContext.
     * @param context The GraphicsContext in which to draw the drawable.
     * @param drawable The Drawable to be drawn.
     */
    public void execute(GraphicsContext context, Drawable drawable);

}
