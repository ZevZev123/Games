package tris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class TrisController {
    @FXML
    private GridPane matrice;

    private Button[][] matrix = new Button[3][3];

    @FXML
    private void initialize(){
        Button button;
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                button = new Button();
                button.getStyleClass().addAll("button-game");
                button.setOnAction(event -> move(event));

                matrix[row][col] = button;
                matrice.add(button, col, row);
            }
        }
    }

    private void move(ActionEvent event){
        

        checkWin();
    }

    private void checkWin(){

    }
}
