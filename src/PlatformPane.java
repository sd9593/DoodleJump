import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class PlatformPane extends Pane {
    private ArrayList<Platform> platforms = new ArrayList<>();

    private static final int NUM_PLATFORMS = 50;
    protected double platformX = 250;
    protected double platformY = 600;

    protected Random ran = new Random();

    // Property type allows for binding for display
    IntegerProperty scoreProperty = new SimpleIntegerProperty();

    public PlatformPane() {
        // initial platform should be stationary
        Platform initialStationaryPlatform = new StationaryPlatform(platformX, platformY);
        platformX = initialStationaryPlatform.getRectangle().getX();
        platformY = initialStationaryPlatform.getRectangle().getY();
        getChildren().add(initialStationaryPlatform.getRectangle());
        platforms.add(initialStationaryPlatform);
        for (int i = 0; i < NUM_PLATFORMS; i++) {
            generatePlatforms();
        }
    }

    // randomly decide which type of platform to create
    public void generatePlatforms() {
        Platform platform;
        int choice = ran.nextInt(4);
        switch (choice) {
            case 0:
                platform = new DisappearingPlatform(platformX, platformY);
                break;
            case 1:
                platform = new StationaryPlatform(platformX, platformY);
                break;
            case 2:
                platform = new BouncyPlatform(platformX, platformY);
                break;
            default:
                platform = new MovingPlatform(platformX, platformY);
                break;
        }
        platformX = platform.getRectangle().getX();
        platformY = platform.getRectangle().getY();

        getChildren().add(platform.getRectangle());
        platforms.add(platform);
    }

    // triggers movement of moving platforms
    public void movePlatforms() {
        for (Platform platform : platforms) {
            if (platform instanceof MovingPlatform) {
                ((MovingPlatform) platform).move();
            }
        }
    }

    // determines if the doodle intersects any platform from the top
    // pair is intersected, extraBounce
    public Pair<Boolean, Boolean> intersects(Rectangle doodle) {
        for (Platform platform : platforms) {
            if (platform.getRectangle().intersects(doodle.getBoundsInLocal())) {
                if (doodle.getX() >= platform.getRectangle().getX() - doodle.getWidth()
                        && doodle.getX() <= platform.getRectangle().getX() + platform.getPlatformWidth()
                        && doodle.getY() >= platform.getRectangle().getY() - doodle.getHeight()) {
                    if (platform instanceof DisappearingPlatform) {
                        getChildren().remove(platform.getRectangle());
                        platforms.remove(platform);
                    }
                    if (platform instanceof BouncyPlatform) {
                        return new Pair(true, true);
                    }
                    return new Pair(true, false);
                }
            }
        }
        return new Pair(false, false);
    }

    // creates illusion of upward doodle movement
    public void scroll(double scrollAmount) {
        // move each platform up by the given amount
        Iterator<Platform> iter = platforms.iterator();
        while (iter.hasNext()) {
            Platform platform = iter.next();
            platform.getRectangle().setY(platform.getRectangle().getY() - scrollAmount);
            // subtracting since scrollAmount will be negative
            if (platform.getRectangle().getY() > platform.getPaneHeight()) {
                iter.remove();
                getChildren().remove(platform);
                scoreProperty.setValue(scoreProperty.getValue() + 1);
                generatePlatforms();
                break;
            }
        }
    }

    public IntegerProperty getScoreProperty() {
        return scoreProperty;
    }

}
