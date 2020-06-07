package com.nhlstenden.designpatterns.graphics.caption;

import com.nhlstenden.designpatterns.graphics.Drawable;
import com.nhlstenden.designpatterns.graphics.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;

public class ShapeCaptionDecorator extends Drawable implements Caption {

    private Caption caption;

    public ShapeCaptionDecorator(Shape shape) {
        super(shape.getDrawingStrategy());
        this.caption = new CaptionImpl(shape);
    }

    @Override
    public void drawCaption() {
        caption.drawCaption();
    }
}
