import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlatformPane extends Pane {
    private ArrayList<Rectangle> stationaryPlatforms = new ArrayList<>();
    private ArrayList<Rectangle> disappearingPlatforms = new ArrayList<>();
    private ArrayList<Rectangle> movingPlatforms = new ArrayList<>();

    private static final int PANE_WIDTH = 500;
    private static final int PANE_HEIGHT = 700;

    // universal platform properties
    private static final double PLATFORM_WIDTH = 50;
    private static final double PLATFORM_HEIGHT = 10;
    private double platformX = 250;
    private double platformY = 600;

    // Property type allows for binding for display
    IntegerProperty scoreProperty = new SimpleIntegerProperty();

    // determines x value of most recently generated platform
    private double previousX = platformX;

    // determines how fast to move moving platforms
    private double speed = 20;

    // to be used to determine new platform position
    private Random ran = new Random();

    // stationary platform attributes
    private static final int NUM_PLATFORMS = 10;

    public PlatformPane() {
        Rectangle initialStationaryPlatform = new Rectangle(platformX, platformY, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        getChildren().add(initialStationaryPlatform);
        stationaryPlatforms.add(initialStationaryPlatform);
        for (int i = 0; i < NUM_PLATFORMS; i++) {
            generatePlatforms();
        }
    }

    public void generatePlatforms() {
        switch (ran.nextInt(1)) {
            case 0:
                previousX = makeStationaryPlatform(previousX);
            case 1:
                previousX = makeDisappearingPlatform(previousX);
            default:
                previousX = makeMovingPlatform(previousX);
        }
    }

    public double makeStationaryPlatform(double previousX) {
        platformX = generatePlatformX(previousX);
        platformY = platformY - ran.nextInt(50) - 25;
        Rectangle stationaryPlatform = new Rectangle(platformX, platformY, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        getChildren().add(stationaryPlatform);
        stationaryPlatforms.add(stationaryPlatform);
        return platformX;
    }

    public double makeDisappearingPlatform(double previousX) {
        platformX = generatePlatformX(previousX);
        platformY = platformY - ran.nextInt(50) - 25;
        Rectangle disappearingPlatform = new Rectangle(platformX, platformY, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        getChildren().add(disappearingPlatform);
        disappearingPlatforms.add(disappearingPlatform);
        disappearingPlatform.setFill(Color.RED);
        return platformX;
    }

    public double makeMovingPlatform(double previousX) {
        platformX = previousX;
        platformY = platformY - ran.nextInt(50) - 25;
        Rectangle movingPlatform = new Rectangle(platformX, platformY, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        getChildren().add(movingPlatform);
        movingPlatforms.add(movingPlatform);
        movingPlatform.setFill(Color.GREEN);
        return platformX;
    }

    public void movePlatforms() {

        // for (Rectangle movingPlatform : movingPlatforms) {
        // // movingPlatform.setX(movingPlatform.getX() + speed);

        // // if (ran.nextInt(2) == 0) {
        // // movingPlatform.setX(movingPlatform.getX() + speed);
        // // } else {
        // // movingPlatform.setX(movingPlatform.getX() - speed);
        // // }

        // // if (movingPlatform.getX() <= 0) {
        // // movingPlatform.setX(movingPlatform.getX() + speed);
        // // }
        // // if (movingPlatform.getX() + PLATFORM_WIDTH > PANE_WIDTH) {
        // // movingPlatform.setX(movingPlatform.getX() - speed);
        // // }
        // }
    }

    public double generatePlatformX(double previousX) {
        int direction = ran.nextInt(2);
        if (direction == 0) {
            platformX = previousX - ran.nextInt(50) - 100; // moves left 50-100
        } else if (direction == 1) {
            platformX = previousX + ran.nextInt(50) + 50; // moves right 50-100
        }
        if (platformX + PLATFORM_WIDTH >= PANE_WIDTH) {
            platformX = PANE_WIDTH - PLATFORM_WIDTH;
        } else if (platformX < 0) {
            platformX = 0;
        }
        return platformX;
    }

    public boolean intersects(Rectangle doodle) {
        for (Rectangle disappearingPlatform : disappearingPlatforms) {
            if (disappearingPlatform.intersects(doodle.getBoundsInLocal())) {
                if (doodle.getX() >= disappearingPlatform.getX() - doodle.getWidth()
                        && doodle.getX() <= disappearingPlatform.getX() +
                                PLATFORM_WIDTH
                        && doodle.getY() >= disappearingPlatform.getY() - 4 * PLATFORM_HEIGHT) {
                    // 4 * PLATFORM_HEIGHT prevents dipping below platform before jumping
                    getChildren().remove(disappearingPlatform);
                    disappearingPlatforms.remove(disappearingPlatform);
                    return true;
                }
            }
        }
        for (Rectangle stationaryPlatform : stationaryPlatforms) {
            if (stationaryPlatform.intersects(doodle.getBoundsInLocal())) {
                if (doodle.getX() >= stationaryPlatform.getX() - doodle.getWidth()
                        && doodle.getX() <= stationaryPlatform.getX() +
                                PLATFORM_WIDTH
                        && doodle.getY() >= stationaryPlatform.getY() - 4 * PLATFORM_HEIGHT) {
                    // 4 * PLATFORM_HEIGHT prevents dipping below platform before jumping
                    return true;
                }
            }
        }
        for (Rectangle movingPlatform : movingPlatforms) {
            if (movingPlatform.intersects(doodle.getBoundsInLocal())) {
                if (doodle.getX() >= movingPlatform.getX() - doodle.getWidth()
                        && doodle.getX() <= movingPlatform.getX() +
                                PLATFORM_WIDTH
                        && doodle.getY() >= movingPlatform.getY() - 4 * PLATFORM_HEIGHT) {
                    // 4 * PLATFORM_HEIGHT prevents dipping below platform before jumping
                    return true;
                }
            }
        }
        // for loop for other types of platforms
        return false;
    }

    public void scroll(double scrollAmount) {
        // move each platform up by the given amount
        for (Rectangle stationaryPlatform : stationaryPlatforms) {
            stationaryPlatform.setY(stationaryPlatform.getY() - scrollAmount);
            // subtracting since scrollAmount will be negative
            if (stationaryPlatform.getY() > PANE_HEIGHT) {
                getChildren().remove(stationaryPlatform);
                stationaryPlatforms.remove(stationaryPlatform);
                scoreProperty.setValue(scoreProperty.getValue() + 2);
                generatePlatforms();
            }
        }
        for (Rectangle disappearingPlatform : disappearingPlatforms) {
            disappearingPlatform.setY(disappearingPlatform.getY() - scrollAmount);
            // subtracting since scrollAmount will be negative
            if (disappearingPlatform.getY() > PANE_HEIGHT) {
                getChildren().remove(disappearingPlatform);
                disappearingPlatforms.remove(disappearingPlatform);
                scoreProperty.setValue(scoreProperty.getValue() + 2);
                generatePlatforms();
            }
        }
        for (Rectangle movingPlatform : movingPlatforms) {
            movingPlatform.setY(movingPlatform.getY() - scrollAmount);
            // subtracting since scrollAmount will be negative
            if (movingPlatform.getY() > PANE_HEIGHT) {
                getChildren().remove(movingPlatform);
                movingPlatforms.remove(movingPlatform);
                scoreProperty.setValue(scoreProperty.getValue() + 1);
                generatePlatforms();
            }
        }
    }

    public IntegerProperty getScoreProperty() {
        return scoreProperty;
    }
}
