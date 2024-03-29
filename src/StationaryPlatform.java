import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class StationaryPlatform extends Platform {
    Rectangle stationaryPlatform;

    public StationaryPlatform(double platformX, double platformY) {
        platformX = generatePlatformX();
        platformY = platformY - ran.nextInt(50) - 25; // moves up 25-75
        stationaryPlatform = new Rectangle(platformX, platformY, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        stationaryPlatform.setFill(Color.BLACK);
    }

    public Rectangle getRectangle() {
        return stationaryPlatform;
    }
}
