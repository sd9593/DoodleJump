
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GamePane extends Pane {

    // doodle attributes
    private Rectangle doodle;
    private double doodleX = 250;
    private double doodleY = 400;
    private static final int DOODLE_WIDTH = 20;
    private static final int DOODLE_HEIGHT = 30;
    private double speed = 20;

    // attributes for falling animation
    private static final int GRAVITY = 100;
    private static final double DURATION = 0.015;
    private static final double REVERSE_VELOCITY = -200;
    private double velocity = 0;

    Timeline doodleAnimation;
    PlatformPane platformPane = new PlatformPane();

    public GamePane() {
        doodle = new Rectangle(doodleX, doodleY, DOODLE_WIDTH, DOODLE_HEIGHT);
        doodle.setFill(Color.GREEN);
        getChildren().addAll(doodle, platformPane);
        doodleAnimation = new Timeline(new KeyFrame(new Duration(DURATION * GRAVITY * 10), e -> {
            fall();
            jump();
            platformPane.movePlatforms();
        }));
        // DURTION * GRAVITY * 10 allows better speed for falling
        doodleAnimation.setCycleCount(Timeline.INDEFINITE);
    }

    // animation begins on enter key pressed
    public void play() {
        doodleAnimation.play();
    }

    public void fall() {
        velocity = velocity + GRAVITY * DURATION;
        doodleY = doodleY + velocity * DURATION;
        if (doodleY < getHeight() / 2) {
            doodle.setY(getHeight() / 2);
            platformPane.scroll(doodleY - getHeight() / 2);
            doodleY = getHeight() / 2;
        } else {
            doodle.setY(doodleY);
        }
    }

    public void jump() {
        if (velocity > 0 && platformPane.intersects(doodle)) {
            // bounces doodle back upwards
            velocity = REVERSE_VELOCITY;
        }
    }

    public void moveDoodle(KeyEvent e) {
        // wrap around to right if doodle goes off screen
        if (e.getCode() == KeyCode.LEFT) {
            if (doodleX <= 0) {
                doodleX = getWidth();
            }
            doodleX -= speed;
        } else if (e.getCode() == KeyCode.RIGHT) {
            // wrap around to left if doodle goes off screen
            if (doodleX + DOODLE_WIDTH >= getWidth()) {
                doodleX = 0.0 - DOODLE_WIDTH;
            }
            doodleX += speed;
        }
        doodle.setX(doodleX);
    }

    public IntegerProperty getScoreProperty() {
        return platformPane.getScoreProperty();
    }
}
