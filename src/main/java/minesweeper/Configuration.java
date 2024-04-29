package minesweeper;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Configuration {
    @FXML private TextField row;
    @FXML private TextField col;
    @FXML private TextField bomb;
    @FXML private TextField button;

    @FXML
    private void initialize(){
        
    }

    @FXML
    private void exit(){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
