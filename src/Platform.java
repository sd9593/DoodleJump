import java.util.Random;

import javafx.scene.shape.Rectangle;

public abstract class Platform extends Rectangle {
    // universal platform properties
    protected static final double PLATFORM_WIDTH = 50;
    protected static final double PLATFORM_HEIGHT = 10;
    protected double platformX = 250;
    protected double platformY = 600;

    private static final int PANE_WIDTH = 500;
    private static final int PANE_HEIGHT = 700;

    // determines coordinates value of most recently generated platform
    protected double previousX = platformX;

    // to be used to determine new platform position
    protected Random ran = new Random();

    public Platform() {
        platformX = generatePlatformX();
        platformY = platformY - ran.nextInt(50) - 25; // moves up 25-75
    }

    public double generatePlatformX() {
        int direction = ran.nextInt(2);
        if (direction == 0) {
            platformX = previousX - ran.nextInt(50) - 50; // moves left 50-100
        } else if (direction == 1) {
            platformX = previousX + ran.nextInt(50) + 50; // moves right 50-100
        }
        if (platformX + PLATFORM_WIDTH >= PANE_WIDTH) {
            platformX = PANE_WIDTH - PLATFORM_WIDTH;
        } else if (platformX < 0) {
            platformX = 0;
        }
        previousX = platformX;
        return platformX;
    }

    public abstract Rectangle getRectangle();

    public double getPlatformWidth() {
        return PLATFORM_WIDTH;
    }

    public double getPlatformHeight() {
        return PLATFORM_HEIGHT;
    }

    public double getPaneWidth() {
        return PANE_WIDTH;
    }

    public double getPaneHeight() {
        return PANE_HEIGHT;
    }
}
