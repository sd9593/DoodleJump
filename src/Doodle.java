import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Doodle extends Node {
    private Rectangle doodle;
    private double doodleX;
    private double doodleY;
    private static final int doodleWidth = 20;
    private static final int doodleHeight = 30;

    public Doodle() {
        doodleX = 500;
        doodleY = 650;
        doodle = new Rectangle(doodleX, doodleY, doodleWidth, doodleHeight);
        doodle.setFill(Color.GREEN);
    }
}
