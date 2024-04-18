package tris;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TrisController {
    @FXML private GridPane matrice;
    @FXML private Label label;

    private Button[][] matrix = new Button[3][3];
    private boolean xTurn = true;

    @FXML
    private void initialize(){
        Button button;
        label.setTextFill(Color.BLUE);
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
            button.getStyleClass().addAll("x-button");
            xTurn = false;
            label.setText("Turn: O");
            label.setTextFill(Color.RED);
        } else {
            button.getStyleClass().addAll("o-button");
            xTurn = true;
            label.setText("Turn: X");
            label.setTextFill(Color.BLUE);
        }
        button.setDisable(true);
        checkWin();
    }

    private void checkWin(){
        // rows check
        for (int rows = 0; rows < 3; rows++){
            if (matrix[0][rows].getStyleClass().contains("x-button") && matrix[1][rows].getStyleClass().contains("x-button") && matrix[2][rows].getStyleClass().contains("x-button")){
                WinWindow('x');
            }
            else if (matrix[0][rows].getStyleClass().contains("o-button") && matrix[1][rows].getStyleClass().contains("o-button") && matrix[2][rows].getStyleClass().contains("o-button")){
                WinWindow('o');
            }
        }
        // columns check
        for (int cols = 0; cols < 3; cols++){
            if (matrix[cols][0].getStyleClass().contains("x-button") && matrix[cols][1].getStyleClass().contains("x-button") && matrix[cols][2].getStyleClass().contains("x-button")){
                WinWindow('x');
            }
            else if (matrix[cols][0].getStyleClass().contains("o-button") && matrix[cols][1].getStyleClass().contains("o-button") && matrix[cols][2].getStyleClass().contains("o-button")){
                WinWindow('o');
            }
        }
        // 1 diagonal check
        if (matrix[0][0].getStyleClass().contains("x-button") && matrix[1][1].getStyleClass().contains("x-button") && matrix[2][2].getStyleClass().contains("x-button")){
            WinWindow('x');
        }
        if (matrix[0][0].getStyleClass().contains("o-button") && matrix[1][1].getStyleClass().contains("o-button") && matrix[2][2].getStyleClass().contains("o-button")){
            WinWindow('o');
        }
        // 2 diagonal check
        if (matrix[2][0].getStyleClass().contains("x-button") && matrix[1][1].getStyleClass().contains("x-button") && matrix[0][2].getStyleClass().contains("x-button")){
            WinWindow('x');
        }
        if (matrix[2][0].getStyleClass().contains("o-button") && matrix[1][1].getStyleClass().contains("o-button") && matrix[0][2].getStyleClass().contains("o-button")){
            WinWindow('o');
        }
    }
    
    private void WinWindow(Character winner){
        for (Button[] buttonList : matrix) {
            for (Button button : buttonList){
                button.setDisable(true);
            }
        }
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("structure2.fxml"));
            Parent root = loader.load();
            
            WinWindow controller = loader.getController();
            controller.setWindow(winner);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Winner");
            stage.setWidth(350);
            stage.setHeight(200);
            
            if (winner == 'x')
                stage.getIcons().add(new Image("file:src\\main\\resources\\images\\trisX.png"));
            else
                stage.getIcons().add(new Image("file:src\\main\\resources\\images\\trisO.png"));

            Scene scene = root.getScene();
            scene.getStylesheets().add(getClass().getResource("style2.css").toExternalForm());
            stage.show();
        } catch (IOException e) {
            System.out.println("ERRORE:\n" + e);
        }
    }

    @FXML
    private void restart(){
        for (Button[] buttonList : matrix){
            for (Button button: buttonList) {
                button.getStyleClass().setAll("button", "button-game");
                button.setDisable(false);
            }
        }
    }
}
