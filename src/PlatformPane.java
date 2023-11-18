import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class PlatformPane extends Pane {
    private ArrayList<Rectangle> stationaryPlatforms = new ArrayList<>();

    // universal platform properties
    private static final double PLATFORM_WIDTH = 50;
    private static final double PLATFORM_HEIGHT = 10;

    Rectangle stationaryPlatform = new Rectangle(500, 600, PLATFORM_WIDTH, PLATFORM_HEIGHT);

    public PlatformPane() {
        getChildren().add(stationaryPlatform);
        stationaryPlatforms.add(stationaryPlatform);
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
