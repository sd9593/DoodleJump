
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GamePane extends Pane {

    // doodle attributes
    private Rectangle doodle;
    private double doodleX = 500;
    private double doodleY = 650;
    private static final int doodleWidth = 20;
    private static final int doodleHeight = 30;

    // private Doodle doodle;

    Timeline animation;

    public GamePane() {
        doodle = new Rectangle(doodleX, doodleY, doodleWidth, doodleHeight);
        doodle.setFill(Color.GREEN);
        getChildren().add(doodle);
        animation = new Timeline(new KeyFrame(new Duration(300), e -> moveDoodle()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    public void moveDoodle() {
        doodleX += 10;
        doodle.setX(doodleX);
    }
}
