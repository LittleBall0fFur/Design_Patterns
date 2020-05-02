package com.nhlstenden.designpatterns.graphics;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class Canvas {

    private int width= 0, height = 0;
    private Scene scene;
    private javafx.scene.canvas.Canvas canvas;
    private GraphicsContext gc;

    public Canvas(int width, int height, Group root){
        canvas = new javafx.scene.canvas.Canvas(width, height);

        gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.WHITE);
        gc.fillRect(50, 50, width, height);

        root.getChildren().add(canvas);
    }
}
