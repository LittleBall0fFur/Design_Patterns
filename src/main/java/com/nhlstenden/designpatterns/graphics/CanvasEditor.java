package com.nhlstenden.designpatterns.graphics;

import com.nhlstenden.designpatterns.graphics.HUD.GUIFactory;
import com.nhlstenden.designpatterns.graphics.shapes.Ellipse;
import com.nhlstenden.designpatterns.graphics.shapes.Rectangle;
import com.nhlstenden.designpatterns.graphics.shapes.Shape;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class CanvasEditor extends Scene {

    Shape shape_prototype;
    Canvas canvas;
    Group root;

    public CanvasEditor(Parent parent) {
        super(parent);
    }

    public Scene initEditor(){
        root = new Group();

        initGUI();
        initCanvas();

        Scene scene = new Scene(root, Color.BLACK);

        return scene;
    }

    private void initGUI() {
        GUIFactory ui = new GUIFactory(1485, 750);
        root.getChildren().add(ui);
        root.getChildren().add(GUIFactory.createButton("rectangle", event -> shape_prototype = new Rectangle(Color.RED, new Point2D(0,0), 50, 50)));
        root.getChildren().add(GUIFactory.createButton("ellipse", event -> shape_prototype = new Ellipse(Color.RED, new Point2D(0,0), 50, 50)));
    }

    private void initCanvas(){
        canvas = new Canvas(1485, 750);
        root.getChildren().add(canvas);
        canvas.present();
    }
}
