
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GamePane extends Pane {

    // doodle attributes
    private Rectangle doodle;
    private double doodleX = 500;
    private double doodleY = 600;
    private static final int doodleWidth = 20;
    private static final int doodleHeight = 30;
    private double speed = 20;

    Timeline doodleAnimation;

    public GamePane() {
        doodle = new Rectangle(doodleX, doodleY, doodleWidth, doodleHeight);
        doodle.setFill(Color.GREEN);
        getChildren().add(doodle);
        doodleAnimation = new Timeline(new KeyFrame(new Duration(300), e -> jump()));
        doodleAnimation.setCycleCount(Timeline.INDEFINITE);
    }

    // animation begins on enter key pressed
    public void play() {
        doodleAnimation.play();
    }

    public void jump() {
        doodleY += 10;
        doodle.setY(doodleY);
    }

    public void moveDoodle(KeyEvent e) {
        if (e.getCode() == KeyCode.LEFT) {
            if (doodleX <= 0) {
                doodleX = getWidth();
            }
            doodleX -= speed;
        } else if (e.getCode() == KeyCode.RIGHT) {
            if (doodleX + doodleWidth >= getWidth()) {
                doodleX = 0 - doodleWidth;
            }
            doodleX += speed;
        }
        doodle.setX(doodleX);
    }
}
