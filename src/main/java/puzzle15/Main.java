package puzzle15;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/puzzle15/structure.fxml"));
        primaryStage.setTitle("15 Puzzle");
        primaryStage.getIcons().add(new Image("file:\\logo.png"));
        Scene scene = new Scene(root, 500, 500);
        scene.getStylesheets().add(getClass().getResource("/puzzle15/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
