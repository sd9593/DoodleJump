import java.util.ArrayList;
import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class PlatformPane extends Pane {
    private ArrayList<Rectangle> stationaryPlatforms = new ArrayList<>();

    // universal platform properties
    private static final double PLATFORM_WIDTH = 50;
    private static final double PLATFORM_HEIGHT = 10;
    private double platformX = 500;
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
                platformX = platformX - 100;
            } else if (direction == 1) {
                platformX = platformX + 100;
            }
            // TODO what if off screen
            platformY = platformY - ran.nextInt(100) - 50;
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
}
