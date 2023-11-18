import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GamePane extends Pane {

    private Doodle doodle;

    public GamePane() {
        getChildren().add(doodle);
    }
}
