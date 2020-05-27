package com.nhlstenden.designpatterns;

import com.nhlstenden.designpatterns.gui.editor.CanvasEditor;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    private static final String APP_NAME = "SsshApe";
    private static final String APP_VERSION = "v1.0.0";

    private static final Image APP_ICON = new Image(App.class.getResourceAsStream("/img/logo.png"));

    @Override
    public void start(Stage stage) throws Exception {
        CanvasEditor editor = new CanvasEditor();
        stage.setScene(editor);

        stage.setMaximized(true);
        stage.setTitle(String.format("%s - %s", APP_NAME, APP_VERSION));
        stage.getIcons().add(APP_ICON);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
