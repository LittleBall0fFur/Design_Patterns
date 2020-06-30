package com.nhlstenden.designpatterns.graphics.shapes;

import com.nhlstenden.designpatterns.graphics.Drawable;
import com.nhlstenden.designpatterns.graphics.DrawingStrategy;
import com.nhlstenden.designpatterns.graphics.ShapeVisitor;
import com.nhlstenden.designpatterns.gui.editor.CanvasEditor;
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
        //super.drawCaption(context, new Point2D(x, y), color, text);
    }

    @Override
    public boolean contains(Point2D point) {
        return point.getX() >= this.getPosition().getX() && point.getX() < (this.getPosition().getX()+this.getWidth())
               &&
               point.getY() >= this.getPosition().getY() && point.getY() < (this.getPosition().getY()+this.getHeight());
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(this.getColor(), this.getPosition(), this.getWidth(), this.getHeight());
    }

}
