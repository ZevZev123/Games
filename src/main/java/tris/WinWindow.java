package tris;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class WinWindow {
    @FXML private GridPane gridpane;

    private char winner;

    @FXML
    private void initialize(){
        Label text = new Label();
        if (winner == 'x')
            text.setText("X wins");
        else
            text.setText("O wins");    
        
        gridpane.add(text, 0, 0);
    }

    public void setWinner(Character winner) {
        this.winner = winner;
    }
}
