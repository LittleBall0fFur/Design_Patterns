package com.nhlstenden.designpatterns.graphics;

import javafx.scene.Scene;

import java.util.List;

public class Canvas {

    private int width= 0, height = 0;
    private Scene scene;
    private List<Object> children;

    public Canvas(int width, int height){

    }

    public void addChild(BasicShape entity) {
        children.add(entity);
    }

    public void removeChild(BasicShape entity) {
        children.remove(entity);
    }

}
