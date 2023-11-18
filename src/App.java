import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage myStage) {
        // borderPane will have score at top, game in center, quit button on bottom
        BorderPane borderPane = new BorderPane();

        Button quit = new Button("Quit");

        GamePane gamePane = new GamePane();

        borderPane.setCenter(gamePane);
        borderPane.setAlignment(quit, Pos.CENTER);
        borderPane.setBottom(quit);

        quit.setOnAction((ActionEvent e) -> {
            System.exit(0);
        });

        Scene scene = new Scene(borderPane, 1000, 700);
        myStage.setScene(scene);
        myStage.setTitle("Doodle jump");
        myStage.show();
        myStage.setResizable(false);
    }

    public static void main(String[] args) throws Exception {
        Application.launch();
    }
}
