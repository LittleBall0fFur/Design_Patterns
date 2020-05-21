package com.nhlstenden.designpatterns;

import com.nhlstenden.designpatterns.graphics.Canvas;
import com.nhlstenden.designpatterns.graphics.UI;
import com.nhlstenden.designpatterns.graphics.shapes.Ellipse;
import com.nhlstenden.designpatterns.graphics.shapes.Rectangle;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // TODO: Implement.
        Group root = new Group();

        Canvas canvas = new Canvas(1485, 750);
        UI ui = new UI(1485, 750);
        root.getChildren().add(canvas);
        root.getChildren().add(ui);
        root.getChildren().addAll(ui.getUIChildren());

        canvas.addShapes(new Rectangle(new Point2D(64, 64), 256, 256),
                         new Ellipse(new Point2D(128, 128), 512, 512));

        canvas.present();

        Scene scene = new Scene(root, Color.BLACK);
        stage.setScene(scene);

        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
