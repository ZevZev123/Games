package tris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TrisController {
    @FXML private GridPane matrice;
    @FXML private Label label;

    private Button[][] matrix = new Button[3][3];
    private boolean xTurn = true;

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
        Button button = (Button) event.getSource();
        if (xTurn){
            if (button.getStyleClass().size() != 2){
                button.getStyleClass().addAll("x-button");
                xTurn = false;
                label.setText("Turn: O");
            }
        } else {
            if (button.getStyleClass().size() != 2){
                button.getStyleClass().addAll("o-button");
                xTurn = true;
                label.setText("Turn: X");
            }
        }
        checkWin();
    }

    private void checkWin(){
        for (int rows = 0; rows < 3; rows++){
            if (matrix[0][rows].getStyleClass().contains("x-button") && matrix[1][rows].getStyleClass().contains("x-button") && matrix[2][rows].getStyleClass().contains("x-button")){
                System.out.println("X wins");
            }
        }
    }
}
