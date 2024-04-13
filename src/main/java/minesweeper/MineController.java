package minesweeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Random;

/*
 * expert   -> 32 x 18 -> 150 mines
 * huge     ->
 * hard     ->
 * medium   ->
 * easy     ->
 * begginer ->
 */

public class MineController {
    @FXML
    private GridPane mines;

    private int maxRow = 20;
    private int maxCol = 25;
    private Button[][] matrix = new Button[maxCol][maxRow];
    private boolean isGenerated = false;

    private Random rand = new Random();

    @FXML
    private void initialize(){
        Button button;
        for (int row = 0; row < maxCol; row++){
            for (int col = 0; col < maxRow; col++){
                button = new Button();
                button.setOnAction(event -> leftButtonPressed(event));
                button.getStyleClass().setAll("button-game");

                matrix[row][col] = button;
                mines.add(button, row, col);
            }
        }
    }
    
    @FXML
    private void leftButtonPressed(ActionEvent event){
        int[] position = getPosition((Button) event.getSource());
        System.out.println("pos: " + position[0] + ", " + position[1]);
        
        if (!isGenerated){
            generate(position);
            isGenerated = true;
        }
        
    }
    
    private int[] getPosition(Button button){
        for (int col = 0; col < maxCol; col++){
            for (int row = 0; row < maxRow; row++){
                if (matrix[col][row].equals(button)){
                    int[] position = {col, row};
                    return position;
                }
            }
        }
        int[] position = {0,0};
        return position;
    }

    private void generate(int[] position){
        int count = 0;
        int x, y;
        while (count < 130){
            x = rand.nextInt(maxCol);
            y = rand.nextInt(maxRow);

            if ((x < (position[0]-1) || x > (position[0]+1)) || (y < (position[1]-1) || y > (position[1]+1))){
                matrix[x][y].getStyleClass().addAll("bomb");
                count++;
            }
        }

        for (int col = 0; col < maxCol; col++){
            for (int row = 0; row < maxRow; row++){
                
            }
        }
    }

    @FXML
    private void restartGame(){
        for (Button[] list: matrix){
            for (Button button: list){
                button.getStyleClass().setAll("button-game");
                isGenerated = false;
            }
        }
    }
}
