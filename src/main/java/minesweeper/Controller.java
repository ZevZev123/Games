package minesweeper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Controller {
    @FXML
    public GridPane mines;

    @FXML
    public void initialize(){
        Button button;
        // 30 x 30
        for (int row = 0; row < 10; row++){
            for (int col = 0; col < 10; col++){
                button = new Button();
                button.getStyleClass().setAll("button-game");

               mines.add(button, row, col);
            }
        }
    }
}
