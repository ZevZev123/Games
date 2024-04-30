package minesweeper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Configuration {
    @FXML private TextField row;
    @FXML private TextField col;
    @FXML private TextField bomb;

    public void setWindow(MyInfo info){
        System.err.println(info);
        row.setText(String.valueOf(info.getRow()));
        col.setText(String.valueOf(info.getCol()));
        bomb.setText(String.valueOf(info.getBomb()));
    }

    @FXML
    private void exit(){
        Stage stage = (Stage) row.getScene().getWindow();
        stage.close();
    }
}
