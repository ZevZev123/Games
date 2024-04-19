package puzzle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MinePuzzle extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/puzzle/structure.fxml"));
        primaryStage.setTitle("Minesweeper");
        primaryStage.getIcons().add(new Image("file:src\\main\\resources\\images\\minesweeper.png"));
        Scene scene = new Scene(root, 700, 600);
        scene.getStylesheets().add(getClass().getResource("/puzzle/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
