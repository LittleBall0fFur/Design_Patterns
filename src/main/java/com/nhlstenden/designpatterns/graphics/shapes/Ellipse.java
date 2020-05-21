package com.nhlstenden.designpatterns.graphics.shapes;

import com.nhlstenden.designpatterns.graphics.Drawable;
import com.nhlstenden.designpatterns.graphics.DrawingStrategy;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Ellipse extends Shape {

    private static class EllipseDrawingStrategy implements DrawingStrategy {

        private static final EllipseDrawingStrategy __instance__ = new EllipseDrawingStrategy();

        private EllipseDrawingStrategy() {
            // Empty. Made private in favor of the Singleton Pattern.
        }

        public static EllipseDrawingStrategy getInstance() {
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
            Ellipse ellipse = (Ellipse) drawable;
            Point2D position = ellipse.getPosition();

            // TODO: Add color customisation. Draws Ellipse in blue for now.
            context.setFill(Color.BLUE);
            context.fillOval(position.getX(), position.getY(), ellipse.getWidth(), ellipse.getHeight());
        }

    }

    public Ellipse(Point2D position, double width, double height) {
        super(position, width, height, EllipseDrawingStrategy.getInstance());
    }

}
