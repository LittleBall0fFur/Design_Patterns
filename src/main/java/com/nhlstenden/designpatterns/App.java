package com.nhlstenden.designpatterns;

import com.nhlstenden.designpatterns.graphics.CanvasEditor;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // TODO: Implement.
        CanvasEditor editor = new CanvasEditor(new Group());
        stage.setScene(editor.initEditor());

        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
