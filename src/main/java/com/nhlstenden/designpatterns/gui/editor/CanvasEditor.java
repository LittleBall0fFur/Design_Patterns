package com.nhlstenden.designpatterns.gui.editor;

import com.nhlstenden.designpatterns.graphics.Canvas;
import com.nhlstenden.designpatterns.graphics.shapes.Ellipse;
import com.nhlstenden.designpatterns.graphics.shapes.Rectangle;
import com.nhlstenden.designpatterns.graphics.shapes.Shape;
import com.nhlstenden.designpatterns.gui.GUIFactory;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
            return selectedColor;
        }

    }

    private final AnchorPane root = (AnchorPane) this.getRoot();

    private Canvas canvas;

    private EditorContext editorContext = new EditorContext();
    private EditorMode editorMode = DrawMode.getInstance();

    private Point2D lastMousePosition = new Point2D(0, 0);

    private Shape shapePrototype = new Rectangle();

    private Shape selectedShape = null;
    // TODO: Replace with ColorPicker
    private Color selectedColor = Color.BLUE;

    public CanvasEditor(double width, double height) {
        super(new AnchorPane(), width, height);

        initCanvas();
        initGUI();
    }

    public CanvasEditor() {
        super(new AnchorPane());

        initCanvas();
        initGUI();
    }

    private void initCanvas() {
        // Create a new Canvas and bind its width and height to the editor.
        this.canvas = new Canvas(this.getWidth(), this.getHeight());
        this.canvas.prefWidthProperty().bind(this.widthProperty());
        this.canvas.prefHeightProperty().bind(this.heightProperty());

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
        this.root.getChildren().add(GUIFactory.createButton("rectangle", event -> {
            this.editorMode = DrawMode.getInstance();
            this.shapePrototype = new Rectangle();
        }));

        this.root.getChildren().add(GUIFactory.createButton("ellipse", event -> {
            this.editorMode = DrawMode.getInstance();
            this.shapePrototype = new Ellipse();
        }));

        this.root.getChildren().add(GUIFactory.createButton("move", event -> {
            this.editorMode = MoveMode.getInstance();
        }));

        this.root.getChildren().add(GUIFactory.createButton("scale", event -> {
            this.editorMode = ResizeMode.getInstance();
        }));

        Label positionLabel = new Label("");
        this.canvas.addEventHandler(MouseEvent.ANY, event -> {
            positionLabel.setText(String.format("(%.0f, %.0f)", event.getX(), event.getY()));
        });

        this.root.setBottomAnchor(positionLabel, 0.0);
        this.root.setRightAnchor(positionLabel, 0.0);

        this.root.getChildren().add(positionLabel);
    }

}
