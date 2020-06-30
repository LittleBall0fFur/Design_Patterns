package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.graphics.Canvas;
import com.nhlstenden.designpatterns.graphics.Drawable;
import com.nhlstenden.designpatterns.graphics.shapes.Ellipse;
import com.nhlstenden.designpatterns.graphics.shapes.Rectangle;
import com.nhlstenden.designpatterns.graphics.shapes.Shape;
import com.nhlstenden.designpatterns.gui.GUIFactory;
import com.nhlstenden.designpatterns.io.ShapeSerializer;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.DirectoryIteratorException;
import java.util.Deque;
import java.util.LinkedList;

public class CanvasEditor extends Scene {

    public class EditorContext {

        private EditorContext() {
            // Empty. Made private to prevent EditorContext from being made outside CanvasEditor.
        }

        public Canvas getCanvas() {
            return canvas;
        }

        public MouseEvent getCurrentMouseEvent() {
            return currentMouseEvent;
        }

        public Point2D getPreviousMousePosition() {
            return previousMousePosition;
        }

        public Point2D getCurrentMousePosition() {
            return currentMousePosition;
        }

        public Color getSelectedColor() {
            return colorPicker.getValue();
        }

        public void setSelectedColor(Color new_color) {
            colorPicker.setValue(new_color);
        }

    }

    private final AnchorPane root = (AnchorPane) this.getRoot();

    private Canvas canvas;
    private ColorPicker colorPicker;

    private final EditorContext editorContext = new EditorContext();
    private final EditorHistory history = new EditorHistory(editorContext);

    private EditorCommand commandPrototype = new DrawCommand(new Rectangle());
    private EditorCommand currentCommand = null;

    private MouseEvent currentMouseEvent = null;

    private Point2D previousMousePosition = new Point2D(0, 0);
    private Point2D currentMousePosition = new Point2D(0, 0);

    private static final CanvasEditor __instance__ = new CanvasEditor();

    public static CanvasEditor getInstance(){
        return __instance__;
    }

    private CanvasEditor() {
        super(new AnchorPane());
        this.root.setBackground(new Background(
                new BackgroundFill(Color.rgb(47, 47, 47), null, null))
        );

        registerKeybindings();

        initCanvas();
        initGUI();
    }

    private void registerKeybindings() {
        this.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            System.out.println(event.getCharacter());

            if (event.isControlDown()) {
                switch (event.getCode()) {
                    case A:
                        // Do nothing, yet.
                        break;
                    case S:
                        // Do nothing, save canvas once implemented.
                        break;
                    case Z:
                        this.history.undo();
                        break;
                    case Y:
                        this.history.redo();
                        break;
                }
            } else {
//                switch (event.getCode()) {
//                    case A:
//                        this.editorMode = DrawMode.getInstance();
//                        this.shapePrototype = new Rectangle();
//                        break;
//                    case S:
//                        this.editorMode = DrawMode.getInstance();
//                        this.shapePrototype = new Ellipse();
//                        break;
//                    case X:
//                        this.editorMode = MoveMode.getInstance();
//                        break;
//                    case Z:
//                        this.editorMode = ResizeMode.getInstance();
//                        break;
//                }
            }
        });
    }

    private void initCanvas() {
        // Create a new Canvas and bind its width and height to the editor.
        this.canvas = new Canvas(this.getWidth() - 48, this.getHeight() - 48);

        this.root.setLeftAnchor(this.canvas, 24.0);
        this.root.setTopAnchor(this.canvas, 24.0);

        // Bind the Canvas' size to the size of the CanvasEditor.
        this.root.widthProperty().addListener((object, old_width, new_width) -> {
            this.canvas.resize(new_width.doubleValue() - 48.0, this.canvas.getHeight());
            this.canvas.present();
        });

        this.root.heightProperty().addListener((object, old_height, new_height) -> {
            this.canvas.resize(this.canvas.getWidth(), new_height.doubleValue() - 48.0);
            this.canvas.present();
        });

        // Hook EditorMode to the Canvas by registering MouseEvent Handlers.
        this.canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> {
                    if (event.getButton() != MouseButton.PRIMARY && event.getButton() != MouseButton.SECONDARY)
                        return;

                    this.currentMouseEvent = event;

                    this.previousMousePosition = this.currentMousePosition;
                    this.currentMousePosition = new Point2D(event.getX(), event.getY());

                    this.currentCommand = this.commandPrototype.clone();
                    this.currentCommand.execute(this.editorContext);
                }
        );

        this.canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                event -> {
                    if (event.getButton() != MouseButton.PRIMARY && event.getButton() != MouseButton.SECONDARY)
                        return;

                    this.currentMouseEvent = event;

                    this.previousMousePosition = this.currentMousePosition;
                    this.currentMousePosition = new Point2D(event.getX(), event.getY());

                    this.currentCommand.execute(this.editorContext);
                }
        );

        this.canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                event -> {
                    if (event.getButton() != MouseButton.PRIMARY && event.getButton() != MouseButton.SECONDARY)
                        return;

                    this.currentMouseEvent = event;

                    this.previousMousePosition = this.currentMousePosition;
                    this.currentMousePosition = new Point2D(event.getX(), event.getY());

                    this.currentCommand.execute(this.editorContext);

                    // Register command in history.
                    this.history.register(currentCommand);
                    this.currentCommand = null;
                }
        );

        // Add the Canvas to the scene graph.
        this.root.getChildren().add(this.canvas);
        this.canvas.present();
    }

    private void initGUI() {
        this.root.getChildren().add(GUIFactory.createButton("rectangle", "Select Rectangle (A)", event -> {
            this.commandPrototype = new DrawCommand(new Rectangle());
        }));

        this.root.getChildren().add(GUIFactory.createButton("ellipse", "Select Ellipse (S)", event -> {
            this.commandPrototype = new DrawCommand(new Ellipse());
        }));

        this.root.getChildren().add(GUIFactory.createButton("eraser", "Eraser Mode", event -> {
            this.commandPrototype = new EraseCommand();
        }));

        this.root.getChildren().add(GUIFactory.createButton("move", "Move Mode (X)", event -> {
            this.commandPrototype = new MoveCommand();
        }));

        this.root.getChildren().add(GUIFactory.createButton("scale", "Resize Mode (Z)", event -> {
            this.commandPrototype = new ResizeCommand();
        }));

        this.root.getChildren().add(GUIFactory.createButton("compose", "Compose Mode (?)", event -> {
            this.commandPrototype = new ComposeCommand();
        }));

        this.root.getChildren().add(GUIFactory.createButton("caption", "Add a caption to a shape", event -> {
            this.commandPrototype = new CaptionCommand();
        }));

        this.root.getChildren().add(GUIFactory.createButton("pipette", "Pipette", event -> {
            this.commandPrototype = new PipetteCommand();
        }));

        this.colorPicker = GUIFactory.createColorPicker("Color Picker", null);
        this.root.getChildren().add(this.colorPicker);

        this.root.getChildren().add(GUIFactory.createButton("save", "Save (CTRL+S)", event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Ssshape Files", "*.shp")
            );

            File output = fileChooser.showSaveDialog(this.getWindow());
            if (output != null) {
                saveCanvas(output.getPath());
            }
        }));

        this.root.getChildren().add(GUIFactory.createButton("undo", "Undo (CTRL+Z)", event -> {
            this.history.undo();
        }));

        this.root.getChildren().add(GUIFactory.createButton("redo", "Redo (CTRL+Y)", event -> {
            this.history.redo();
        }));

        Label positionLabel = new Label("");
        positionLabel.setTextFill(Color.WHITESMOKE);

        this.canvas.addEventHandler(MouseEvent.ANY, event -> {
            positionLabel.setText(String.format("(%.0f, %.0f)", event.getX(), event.getY()));
        });

        this.root.setBottomAnchor(positionLabel, 4.0);
        this.root.setRightAnchor(positionLabel, 4.0);

        this.root.getChildren().add(positionLabel);
    }

    public void saveCanvas(String output_path) {
        try {
            trySaveCanvas(output_path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void trySaveCanvas(String output_path) throws IOException {
        StringBuilder result = new StringBuilder();
        ShapeSerializer serializer = new ShapeSerializer(result);
        for (Shape shape : this.canvas.getShapes())
            shape.accept(serializer);

        // DEBUG
        System.out.print(result.toString());

        FileWriter output = new FileWriter(output_path);
        output.write(result.toString());
        output.close();
    }

}
