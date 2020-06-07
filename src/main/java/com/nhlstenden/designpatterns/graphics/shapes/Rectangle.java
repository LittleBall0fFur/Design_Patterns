package com.nhlstenden.designpatterns.graphics.shapes;

import com.nhlstenden.designpatterns.graphics.Drawable;
import com.nhlstenden.designpatterns.graphics.DrawingStrategy;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {

    private static class RectangleDrawingStrategy implements DrawingStrategy {

        private static final RectangleDrawingStrategy __instance__ = new RectangleDrawingStrategy();

        private RectangleDrawingStrategy() {
            // Empty. Made private in favor of the Singleton Pattern.
        }

        public static RectangleDrawingStrategy getInstance() {
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
            Rectangle rectangle = (Rectangle) drawable;
            Point2D position = rectangle.getPosition();

            context.setFill(rectangle.getColor());
            context.fillRect(position.getX(), position.getY(), rectangle.getWidth(), rectangle.getHeight());
        }

    }

    public Rectangle() {
        super(RectangleDrawingStrategy.getInstance());
    }

    public Rectangle(Color color, Point2D position, double width, double height) {
        super(color, position, width, height, RectangleDrawingStrategy.getInstance());
    }

    public void drawCaption() {
        super.drawCaption(this);
    }

    @Override
    public boolean contains(Point2D point) {
        return point.getX() >= this.getPosition().getX() && point.getX() < (this.getPosition().getX()+this.getWidth())
               &&
               point.getY() >= this.getPosition().getY() && point.getY() < (this.getPosition().getY()+this.getHeight());
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(this.getColor(), this.getPosition(), this.getWidth(), this.getHeight());
    }

}
