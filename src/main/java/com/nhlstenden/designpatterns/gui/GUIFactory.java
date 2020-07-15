package com.nhlstenden.designpatterns.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class GUIFactory {

    private static double offset;

    public static Button createButton(String name, String tooltip, EventHandler<MouseEvent> clickEventHandler){
        Button button = new Button();
        offset += 24;
        button.setTranslateY(offset);
        button.addEventHandler(MouseEvent.MOUSE_RELEASED, clickEventHandler);

        button.setTooltip(new Tooltip(tooltip));

        BackgroundImage backgroundImage = new BackgroundImage(new Image(GUIFactory.class.getResource("/img/icons/" + name + ".png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        button.setBackground(background);
        button.setMinSize(24,24);

        return button;
    }

    public static Button createCanvasButton(String name, String tooltip, double x, double y, EventHandler<MouseEvent> clickEventHandler){
        Button button = new Button();
        button.setTranslateX(x + 12);
        button.setTranslateY(y + 12);
        button.addEventHandler(MouseEvent.MOUSE_RELEASED, clickEventHandler);

        button.setTooltip(new Tooltip(tooltip));

        BackgroundImage backgroundImage = new BackgroundImage(new Image(GUIFactory.class.getResource("/img/icons/" + name + ".png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        button.setBackground(background);
        button.setMinSize(24,24);

        return button;
    }

    public static TextField createTextField(String promptText, double x, double y) {
        TextField textField = new TextField("");
        textField.setPromptText(promptText);
        textField.setTranslateX(x);
        textField.setTranslateY(y);
        return textField;
    }

    public static ColorPicker createColorPicker(String tooltip, EventHandler<ActionEvent> actionEventHandler) {
        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        offset += 24;
        colorPicker.setTranslateY(offset);
        colorPicker.setOnAction(actionEventHandler);

        colorPicker.setTooltip(new Tooltip(tooltip));

        // Ugly way to force the ColorPicker to display properly, might fix later.
        colorPicker.getStyleClass().add("button");
        colorPicker.getStylesheets().add(GUIFactory.class.getResource("/css/colorpicker.css").toExternalForm());

        colorPicker.setBackground(new Background(
                new BackgroundFill(Color.WHITE, new CornerRadii(16), new Insets(2)),
                new BackgroundFill(colorPicker.getValue(), new CornerRadii(16), new Insets(3)))
        );

        colorPicker.valueProperty().addListener((observable, old_color, new_color) -> {
            colorPicker.setBackground(new Background(
                    new BackgroundFill(Color.WHITE, new CornerRadii(16), new Insets(2)),
                    new BackgroundFill(new_color, new CornerRadii(16), new Insets(3))
                    )
            );
        });

        colorPicker.setMinSize(24,24);
        colorPicker.setMaxSize(24,24);

        return colorPicker;
    }

    /*public static Text createText(Point2D position, Color color, String text){
        Text caption = new Text();
        caption.setFont(Font.font("Verdana", 20));
        caption.setFill(color);
        caption.setText(text);
        caption.setX(position.getX());
        caption.setY(position.getY());

        return caption;
    }*/

}
