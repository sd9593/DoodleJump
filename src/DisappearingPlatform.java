import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DisappearingPlatform extends Platform {
    private Rectangle disappearingPlatform;

    public DisappearingPlatform(double platformX, double platformY) {
        platformX = generatePlatformX();
        platformY = platformY - ran.nextInt(50) - 25; // moves up 25-75
        disappearingPlatform = new Rectangle(platformX, platformY, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        disappearingPlatform.setFill(Color.RED);
    }

    public Rectangle getRectangle() {
        return disappearingPlatform;
    }
}
