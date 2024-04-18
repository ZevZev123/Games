package tris;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WinWindow {
    @FXML private Label label;
    @FXML private Button exitButton;

    private char winner;

    public void setWindow(Character winner){
        this.winner = winner;

        if (this.winner == 'x')
            label.setText("X wins");
        else
            label.setText("O wins");    
    }

    @FXML
    private void exit(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
