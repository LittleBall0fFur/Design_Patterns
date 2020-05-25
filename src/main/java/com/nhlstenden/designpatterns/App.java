package com.nhlstenden.designpatterns;

import com.nhlstenden.designpatterns.gui.editor.CanvasEditor;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    private static final String APP_NAME = "Shapist";
    private static final String APP_VERSION = "v1.0.0";

    @Override
    public void start(Stage stage) throws Exception {
        CanvasEditor editor = new CanvasEditor();
        stage.setScene(editor);

        stage.setMaximized(true);
        stage.setTitle(String.format("%s - %s", APP_NAME, APP_VERSION));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
