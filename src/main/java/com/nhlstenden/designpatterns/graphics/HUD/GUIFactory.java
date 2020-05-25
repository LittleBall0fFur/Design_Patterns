package com.nhlstenden.designpatterns.graphics.HUD;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class GUIFactory extends Region {
    private static double offset;

    public GUIFactory(double width, double height) {}

    public static Button createButton(String name, EventHandler<MouseEvent> click_event){
        Button button = new Button();
        offset += 24;
        button.setTranslateY(offset);
        button.addEventHandler(MouseEvent.MOUSE_RELEASED, click_event);

        BackgroundImage backgroundImage = new BackgroundImage(new Image(GUIFactory.class.getResource("/" + name + ".png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        button.setBackground(background);
        button.setMinSize(24,24);

        return button;
    }
}
