package com.nhlstenden.designpatterns.graphics.shapes;

import com.nhlstenden.designpatterns.graphics.Drawable;
import com.nhlstenden.designpatterns.graphics.DrawingStrategy;
import com.nhlstenden.designpatterns.graphics.ShapeVisitor;
import com.nhlstenden.designpatterns.gui.editor.CanvasEditor;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static java.lang.Math.pow;

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

            context.setFill(ellipse.getColor());
            context.fillOval(position.getX(), position.getY(), ellipse.getWidth(), ellipse.getHeight());
        }

    }

    public Ellipse() {
        super(EllipseDrawingStrategy.getInstance());
    }

    public Ellipse(Color color, Point2D position, double width, double height) {
        super(color, position, width, height, EllipseDrawingStrategy.getInstance());
    }

    public void drawCaption(CanvasEditor.EditorContext context, Color color, String text) {
        double x = this.getPosition().getX();
        double y = this.getPosition().getY();
        double padding = 5.0;
        // check if caption should be placed: ernaast, erboven, eronder
        // calculate new x where caption should be placed
        x = x + (this.getWidth()/2) + padding;
        // calculate new y where caption should be placed
        y = y + (this.getHeight()/2) + padding;
        // call super.drawCaption with these parameters
//        super.drawCaption(context, new Point2D(x, y), color, text);
    }

    @Override
    public boolean contains(Point2D point) {
        Point2D center = this.getPosition().add(this.getWidth()/2, this.getHeight()/2);
        Point2D distance = point.subtract(center);

        // Equation determining whether a point lies within an ellipse.
        // Should the result be greater than 1, then it can be said the given point does not lie within the ellipse.
        double discriminant = pow(distance.getX(), 2) / pow(this.getWidth()/2, 2)
                            + pow(distance.getY(), 2) / pow(this.getHeight()/2, 2);

        return discriminant <= 1;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Ellipse clone() {
        return new Ellipse(this.getColor(), this.getPosition(), this.getWidth(), this.getHeight());
    }

}
