package com.nhlstenden.designpatterns;

import com.nhlstenden.designpatterns.gui.editor.CanvasEditor;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        CanvasEditor editor = new CanvasEditor();
        stage.setScene(editor);

        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
