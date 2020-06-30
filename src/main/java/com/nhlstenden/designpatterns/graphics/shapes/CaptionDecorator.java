package com.nhlstenden.designpatterns.graphics.shapes;

import com.nhlstenden.designpatterns.graphics.Drawable;
import com.nhlstenden.designpatterns.graphics.DrawingStrategy;
import com.nhlstenden.designpatterns.graphics.ShapeVisitor;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;

public class CaptionDecorator extends ShapeDecorator {

    public enum Position {
        TOP, BOTTOM, LEFT, RIGHT
    }

    private static class CaptionDrawingStrategy implements DrawingStrategy {

        private static final CaptionDrawingStrategy __instance__ = new CaptionDrawingStrategy();

        private CaptionDrawingStrategy() {
            // Empty. Made private in favor of the Singleton Pattern.
        }

        public static CaptionDrawingStrategy getInstance() {
            return __instance__;
        }

        @Override
        public void execute(GraphicsContext context, Drawable drawable) {
            try {
                tryExecute(context, drawable);
            } catch (ClassCastException exception) {
                exception.printStackTrace();
            }
        }

        private void tryExecute(GraphicsContext context, Drawable drawable) {
            CaptionDecorator decorator = (CaptionDecorator) drawable;
            Shape shape = decorator.getDecoratedShape();

            Drawable.draw(context, shape);

            context.setTextBaseline(VPos.CENTER);

            double x = 0, y = 0;
            switch (decorator.position) {
                case TOP:
                    context.setTextAlign(TextAlignment.CENTER);
                    x = shape.getPosition().getX() + shape.getWidth()/2;
                    y = shape.getPosition().getY() - 20;
                    break;

                case BOTTOM:
                    context.setTextAlign(TextAlignment.CENTER);
                    x = shape.getPosition().getX() + shape.getWidth()/2;
                    y = (shape.getPosition().getY() + shape.getHeight()) + 20;
                    break;

                case LEFT:
                    context.setTextAlign(TextAlignment.RIGHT);
                    x = shape.getPosition().getX() - 20;
                    y = shape.getPosition().getY() + shape.getHeight()/2;
                    break;

                case RIGHT:
                    context.setTextAlign(TextAlignment.LEFT);
                    x = (shape.getPosition().getX() + shape.getWidth()) + 20;
                    y = shape.getPosition().getY() + shape.getHeight()/2;
                    break;

            }

            context.fillText(decorator.caption, x, y);
        }
    }

    private String caption;
    private Position position;

    public CaptionDecorator(Shape shape, String caption, Position position) {
        super(shape, CaptionDrawingStrategy.getInstance());

        this.caption = caption;
        this.position = position;
    }

    public String getCaption() {
        return this.caption;
    }

    public Position getCaptionPosition() {
        return this.position;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public CaptionDecorator clone() {
        return new CaptionDecorator(this.getDecoratedShape().clone(), this.caption, this.position);
    }

}
