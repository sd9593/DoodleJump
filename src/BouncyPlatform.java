import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BouncyPlatform extends Platform {
    Rectangle bouncyPlatform;

    public BouncyPlatform(double platformX, double platformY) {
        platformX = generatePlatformX();
        platformY = platformY - ran.nextInt(50) - 25; // moves up 25-75
        bouncyPlatform = new Rectangle(platformX, platformY, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        bouncyPlatform.setFill(Color.GREEN);
    }

    public Rectangle getRectangle() {
        return bouncyPlatform;
    }
}
