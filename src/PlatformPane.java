import java.util.ArrayList;
import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class PlatformPane extends Pane {
    private ArrayList<Rectangle> stationaryPlatforms = new ArrayList<>();

    private static final int PANE_WIDTH = 500;

    // universal platform properties
    private static final double PLATFORM_WIDTH = 50;
    private static final double PLATFORM_HEIGHT = 10;
    private double platformX = 250;
    private double platformY = 600;

    // to be used to determine new platform position
    Random ran = new Random();

    // stationary platform attributes
    private static final int NUM_STATIONARY_PLATFORMS = 10;

    public PlatformPane() {
        Rectangle initialStationaryPlatform = new Rectangle(platformX, platformY, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        getChildren().add(initialStationaryPlatform);
        stationaryPlatforms.add(initialStationaryPlatform);
        for (int i = 0; i <= NUM_STATIONARY_PLATFORMS; i++) {
            int direction = ran.nextInt(2);
            if (direction == 0) {
                platformX = platformX - ran.nextInt(50) - 100; // moves left 50-100
            } else if (direction == 1) {
                platformX = platformX + ran.nextInt(50) + 50; // moves right 50-100
            }
            if (platformX + PLATFORM_WIDTH >= PANE_WIDTH) {
                platformX = PANE_WIDTH - PLATFORM_WIDTH;
            } else if (platformX < 0) {
                platformX = 0;
            }
            platformY = platformY - ran.nextInt(50) - 25;
            Rectangle stationaryPlatform = new Rectangle(platformX, platformY, PLATFORM_WIDTH, PLATFORM_HEIGHT);
            getChildren().add(stationaryPlatform);
            stationaryPlatforms.add(stationaryPlatform);
        }

    }

    public boolean intersects(Rectangle doodle) {
        boolean result = false;
        for (Rectangle stationaryPlatform : stationaryPlatforms) {
            if (stationaryPlatform.intersects(doodle.getBoundsInLocal())) {
                if (doodle.getX() >= stationaryPlatform.getX() && doodle.getX() <= stationaryPlatform.getX() +
                        PLATFORM_WIDTH && doodle.getY() >= stationaryPlatform.getY() - 2 * PLATFORM_HEIGHT) {
                    // 2 * PLATFORM_HEIGHT prevents dipping below platform before jumping
                    result = true;
                }
            }
        }
        // for loop for other types of platforms
        return result;
    }

    public void scroll(double scrollAmount) {
        // move each platform up by the given amount
        for (Rectangle stationaryPlatform : stationaryPlatforms) {
            stationaryPlatform.setY(stationaryPlatform.getY() - scrollAmount);
            // subtracting since scrollAmount will be negative
        }
    }
}
