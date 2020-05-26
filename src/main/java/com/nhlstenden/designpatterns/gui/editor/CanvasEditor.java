package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.graphics.Canvas;
import com.nhlstenden.designpatterns.graphics.shapes.Ellipse;
import com.nhlstenden.designpatterns.graphics.shapes.Rectangle;
import com.nhlstenden.designpatterns.graphics.shapes.Shape;
import com.nhlstenden.designpatterns.gui.GUIFactory;
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

public class CanvasEditor extends Scene {

    public class EditorContext {

        private EditorContext() {
            // Empty. Made private to prevent EditorContext from being made outside CanvasEditor.
        }

        public Canvas getCanvas() {
            return canvas;
        }

        public EditorMode getEditorMode() {
            return editorMode;
        }

        public Point2D getLastMousePosition() {
            return lastMousePosition;
        }

        public Shape getShapePrototype() {
            return shapePrototype;
        }

        public Shape getSelectedShape() {
            return selectedShape;
        }

        public void setSelectedShape(Shape shape) {
            selectedShape = shape;
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

    private EditorContext editorContext = new EditorContext();
    private EditorMode editorMode = DrawMode.getInstance();

    private Point2D lastMousePosition = new Point2D(0, 0);

    private Shape shapePrototype = new Rectangle();

    private Shape selectedShape = null;

    public CanvasEditor() {
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
                    case X:
                        // Do nothing, yet.
                        break;
                    case Z:
                        // Do nothing, yet.
                        break;
                }
            } else {
                switch (event.getCode()) {
                    case A:
                        this.editorMode = DrawMode.getInstance();
                        this.shapePrototype = new Rectangle();
                        break;
                    case S:
                        this.editorMode = DrawMode.getInstance();
                        this.shapePrototype = new Ellipse();
                        break;
                    case X:
                        this.editorMode = MoveMode.getInstance();
                        break;
                    case Z:
                        this.editorMode = ResizeMode.getInstance();
                        break;
                }
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
                    this.lastMousePosition = new Point2D(event.getX(), event.getY());

                    if (event.getButton() == MouseButton.PRIMARY || event.getButton() == MouseButton.SECONDARY)
                        this.editorMode.handleMousePress(event, editorContext);
                }
        );

        this.canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                event -> {
                    if (event.getButton() == MouseButton.PRIMARY || event.getButton() == MouseButton.SECONDARY)
                        this.editorMode.handleMouseDrag(event, editorContext);

                    this.lastMousePosition = new Point2D(event.getX(), event.getY());
                }
        );

        this.canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                event -> {
                    if (event.getButton() == MouseButton.PRIMARY || event.getButton() == MouseButton.SECONDARY)
                        this.editorMode.handleMouseRelease(event, editorContext);

                    this.lastMousePosition = new Point2D(event.getX(), event.getY());
                }
        );

        // Add the Canvas to the scene graph.
        this.root.getChildren().add(this.canvas);
        this.canvas.present();
    }

    private void initGUI() {
        this.root.getChildren().add(GUIFactory.createButton("rectangle", "Select Rectangle (A)", event -> {
            this.editorMode = DrawMode.getInstance();
            this.shapePrototype = new Rectangle();
        }));

        this.root.getChildren().add(GUIFactory.createButton("ellipse", "Select Ellipse (S)", event -> {
            this.editorMode = DrawMode.getInstance();
            this.shapePrototype = new Ellipse();
        }));

        this.root.getChildren().add(GUIFactory.createButton("move", "Move Mode (X)", event -> {
            this.editorMode = MoveMode.getInstance();
        }));

        this.root.getChildren().add(GUIFactory.createButton("scale", "Resize Mode (Z)", event -> {
            this.editorMode = ResizeMode.getInstance();
        }));

        this.root.getChildren().add(GUIFactory.createButton("pipette", "Pipette", event -> {
            this.editorMode = ColorSelectMode.getInstance();
        }));

        this.colorPicker = GUIFactory.createColorPicker("Color Picker", null);
        this.root.getChildren().add(this.colorPicker);

        Label positionLabel = new Label("");
        positionLabel.setTextFill(Color.WHITESMOKE);
        
        this.canvas.addEventHandler(MouseEvent.ANY, event -> {
            positionLabel.setText(String.format("(%.0f, %.0f)", event.getX(), event.getY()));
        });

        this.root.setBottomAnchor(positionLabel, 4.0);
        this.root.setRightAnchor(positionLabel, 4.0);

        this.root.getChildren().add(positionLabel);
    }

}
