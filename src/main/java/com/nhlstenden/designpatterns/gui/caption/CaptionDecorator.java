package com.nhlstenden.designpatterns.gui.caption;

import com.nhlstenden.designpatterns.graphics.Drawable;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

public abstract class CaptionDecorator implements Caption {

    @Override
    public void drawCaption(Drawable shape) {
        Text caption = new Text();
        caption.setFont(Font.font("Verdana", 20));
        //caption.setFill();
        caption.setText("Hello World");
        // logic to draw caption around shape
        // do not delete previous caption
    }
}
