import javafx.scene.Scene;

import java.util.List;

public class Canvas {
    private int width= 0, height = 0;
    private Scene s;
    private List<Object> children;

    Canvas(int width, int height){

    }

    void AddChild(BasicShape entity){
        children.add(entity);
    }

    void removeChild(BasicShape entity){
        children.remove(entity);
    }
}
