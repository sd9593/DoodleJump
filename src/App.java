import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage myStage) {
        GamePane gamePane = new GamePane();

        Scene scene = new Scene(gamePane, 1000, 700);
        myStage.setScene(scene);
        myStage.setTitle("Doodle jump");
        myStage.show();
        myStage.setResizable(false);
    }

    public static void main(String[] args) throws Exception {
        Application.launch();
    }
}
