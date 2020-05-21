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

            // TODO: Add color customisation. Draws Rectangle in red for now.
            context.setFill(Color.RED);
            context.fillRect(position.getX(), position.getY(), rectangle.getWidth(), rectangle.getHeight());
        }

    }

    public Rectangle(Point2D position, double width, double height) {
        super(position, width, height, RectangleDrawingStrategy.getInstance());
    }

}
