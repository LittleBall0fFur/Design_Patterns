package com.nhlstenden.designpatterns;

import com.nhlstenden.designpatterns.graphics.Canvas;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // TODO: Implement.
        Group root = new Group();

        Canvas canvas = new Canvas(1485, 750);
        root.getChildren().add(canvas);
        canvas.CreateShape('r',new Point2D(100,100), 500, 500);
        Scene scene = new Scene(root, Color.BLACK);
        stage.setScene(scene);

        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
