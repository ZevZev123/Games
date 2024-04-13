package minesweeper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MineController {
    @FXML
    private GridPane mines;

    @FXML
    private void initialize(){
        Button button;
        for (int row = 0; row < 20; row++){
            for (int col = 0; col < 20; col++){
                button = new Button();
                button.getStyleClass().setAll("button-game");

               mines.add(button, row, col);
            }
        }
    }

    @FXML
    private void restartGame(){
        System.out.println("CIAO");
    }
}
