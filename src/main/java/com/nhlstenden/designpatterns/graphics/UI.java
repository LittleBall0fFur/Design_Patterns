package com.nhlstenden.designpatterns.graphics;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class UI extends Region {
    public UI(double width, double height) {
        InitButtons();
    }

    private void InitButtons() {
        Button rectangle_button = new Button("Rectangle");
        rectangle_button.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> OnRectangleClick());
        this.getChildren().add(rectangle_button);

        Button ellipse_button = new Button("Ellipse");
        ellipse_button.setTranslateX(100.0);
        ellipse_button.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> OnEllipseClick());
        this.getChildren().add(ellipse_button);
    }

    private void OnRectangleClick(){
        //To-do implement draw rectangle on second click
    }

    private void OnEllipseClick(){
        //To-do implement draw ellipse on second click
    }

    public ObservableList<Node> getUIChildren() {
        return this.getChildren();
    }
}
