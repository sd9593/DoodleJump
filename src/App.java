import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage myStage) {
        // borderPane will have score at top, game in center, quit button on bottom
        BorderPane borderPane = new BorderPane();

        Button quit = new Button("Quit");

        GamePane gamePane = new GamePane();

        Label scoreBoard = new Label("Your score: ");
        Text text = new Text();
        HBox hbox = new HBox(scoreBoard, text);

        gamePane.getScoreProperty().addListener(ov -> {
            text.setText(Integer.toString(gamePane.getScoreProperty().getValue()));
        });

        borderPane.setTop(hbox);
        borderPane.setCenter(gamePane);
        borderPane.setAlignment(quit, Pos.CENTER);
        borderPane.setBottom(quit);

        quit.setOnAction((ActionEvent e) -> System.exit(0));

        gamePane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                gamePane.play();
            } else if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT) {
                gamePane.moveDoodle(e);
            }
        });

        Scene scene = new Scene(borderPane, 500, 700);
        myStage.setScene(scene);
        myStage.setTitle("Doodle jump");
        myStage.show();
        myStage.setResizable(false);

        gamePane.requestFocus(); // allows KeyPressed event to fire
    }

    public static void main(String[] args) throws Exception {
        Application.launch();
    }
}
