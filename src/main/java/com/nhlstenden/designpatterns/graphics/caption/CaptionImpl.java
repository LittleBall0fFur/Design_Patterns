package com.nhlstenden.designpatterns.graphics.caption;

import com.nhlstenden.designpatterns.graphics.shapes.Shape;

public class CaptionImpl implements Caption{

    private Shape shape;

    public CaptionImpl(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void drawCaption() {
        // draw caption
    }
}
