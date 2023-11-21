import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class StationaryPlatform extends Platform {
    Rectangle stationaryPlatform;

    public StationaryPlatform(double platformX, double platformY) {
        super.platformX = platformX;
        super.platformY = platformY;
        platformX = generatePlatformX();
        platformY = platformY - ran.nextInt(50) - 25; // moves up 25-75
        stationaryPlatform = new Rectangle(platformX, platformY, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        stationaryPlatform.setFill(Color.BLUE);
    }

    public Rectangle getRectangle() {
        return stationaryPlatform;
    }
}
