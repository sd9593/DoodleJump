import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MovingPlatform extends Platform {
    Rectangle movingPlatform;
    private int dx;

    public MovingPlatform(double platformX, double platformY) {
        platformX = generatePlatformX();
        platformY = platformY - ran.nextInt(50) - 25; // moves up 25-75
        movingPlatform = new Rectangle(platformX, platformY, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        movingPlatform.setFill(Color.BLUE);
        dx = 1;
    }

    public Rectangle getRectangle() {
        return movingPlatform;
    }

    public void move() {
        if (movingPlatform.getX() <= 0) {
            dx *= -1;
        } else if (movingPlatform.getX() + PLATFORM_WIDTH > getPaneWidth()) {
            dx *= -1;
        }
        platformX += dx;
        movingPlatform.setX(platformX);
    }
}
