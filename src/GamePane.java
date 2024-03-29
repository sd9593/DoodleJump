
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.util.Pair;

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
    private static final double REVERSE_VELOCITY = -150;
    private double velocity = 0;

    private Timeline animation;
    private PlatformPane platformPane = new PlatformPane();

    public GamePane() {
        doodle = new Rectangle(doodleX, doodleY, DOODLE_WIDTH, DOODLE_HEIGHT);
        doodle.setFill(Color.GREEN);
        getChildren().addAll(doodle, platformPane);
        animation = new Timeline(new KeyFrame(new Duration(DURATION * GRAVITY * 10), e -> {
            fall();
            jump();
            platformPane.movePlatforms();
        }));
        // DURTION * GRAVITY * 10 allows better speed for falling
        animation.setCycleCount(Timeline.INDEFINITE);
    }

    // animation begins on enter key pressed
    public void play() {
        animation.play();
    }

    public void fall() {
        velocity = velocity + GRAVITY * DURATION;
        doodleY = doodleY + velocity * DURATION;
        if (doodleY > getHeight()) {
            animation.stop();
            setOnKeyPressed(null);
            Label gameOver = new Label("Game Over");
            gameOver.setFont(new Font("Arial", 50));
            getChildren().add(gameOver);
        } else if (doodleY < getHeight() / 2) {
            doodle.setY(getHeight() / 2);
            platformPane.scroll(doodleY - getHeight() / 2);
            doodleY = getHeight() / 2;
        } else {
            doodle.setY(doodleY);
        }

    }

    public void jump() {
        if (velocity > 0) {
            Pair<Boolean, Boolean> result = platformPane.intersects(doodle);
            if (result.getKey()) {
                // bounces doodle back upwards
                velocity = REVERSE_VELOCITY;
                if (result.getValue()) {
                    // double bounce if bouncyPlatform
                    velocity = REVERSE_VELOCITY * 1.5;
                }
            }
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
