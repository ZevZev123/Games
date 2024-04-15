package minesweeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
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
                

                /*
                button.setOnMousePressed(event -> {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            leftButtonPressed(event);
                        }
                    });
            
                button.setOnContextMenuRequested(event -> {
                    putFlag(event);
                });
                */
                button.getStyleClass().setAll("button-game", "button-locked");

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
        if (matrix[position[0]][position[1]].getText() == "bomb"){
            matrix[position[0]][position[1]].setStyle("-fx-background-color:red;");
            showBombs();
            disableButtons();
            System.out.println("Game Over");
        } else {
            open(position);
        }
    }
    
    private void open(int[] position){
        Button button = matrix[position[0]][position[1]];
        
        if (!button.getStyleClass().contains(button.getText())){
            if (button.getText() == ""){
                button.getStyleClass().setAll("button-game", "bomb0");
            } else {
                button.getStyleClass().setAll("button-game", button.getText());
            }
        }
        
        if (button.getStyleClass().contains("bomb0")){
            for (int col = (position[0]-1); col < (position[0]+2); col++){
                for (int row = (position[1]-1); row < (position[1]+2); row++){
                    if ((col >= 0 && row >= 0) && (col < maxCol && row < maxRow) && (col != position[0] || row != position[1])){
                        if (matrix[col][row].getStyleClass().contains("button-locked")){
                            open(new int[] {col, row});
                        }
                    }
                }
            }
        }
    }

    private void showBombs(){
        for (Button[] list1 : matrix){
            for (Button button : list1){
                if (button.getText() == "bomb"){
                    button.getStyleClass().addAll("bomb");
                }
            }
        }
    }

    private void flag(ActionEvent event){
        int[] position = getPosition((Button) event.getSource());
        Button button = matrix[position[0]][position[1]];
        if (button.getStyleClass().contains("mine")){
            button.getStyleClass().addAll("mine");
        } else {
            button.getStyleClass().setAll("button-game","button-locked");
        }
    }

    private int[] getPosition(Button button){
        for (int col = 0; col < maxCol; col++){
            for (int row = 0; row < maxRow; row++){
                if (matrix[col][row].equals(button)){
                    return new int[] {col, row};
                }
            }
        }
        return new int[] {0,0};
    }

    private void generate(int[] position){
        int count = 0;
        int x, y;
        while (count < 130){
            x = rand.nextInt(maxCol);
            y = rand.nextInt(maxRow);

            if ((x < (position[0]-1) || x > (position[0]+1)) || (y < (position[1]-1) || y > (position[1]+1))){
                matrix[x][y].setText("bomb");
                //matrix[x][y].getStyleClass().addAll("bomb");
                count++;
            }
        }

        for (int col = 0; col < maxCol; col++){
            for (int row = 0; row < maxRow; row++){
                if (matrix[col][row].getText() != "bomb"){
                    count = 0;
                    for (int col2 = col-1; col2 < col+2; col2++){
                        for (int row2 = row-1; row2 < row+2; row2++){
                            if (col2 >= 0 && row2 >= 0 && col2 < maxCol && row2 < maxRow){
                                if (matrix[col2][row2].getText() == "bomb"){
                                    count++;
                                }        
                            }
                        }
                    }
                    switch (count) {
                        case 1:
                            matrix[col][row].setText("bomb1");
                            //matrix[col][row].getStyleClass().addAll("bomb1");
                            break;
                        case 2:
                            matrix[col][row].setText("bomb2");
                            //matrix[col][row].getStyleClass().addAll("bomb2");
                            break;
                        case 3:
                            matrix[col][row].setText("bomb3");
                            //matrix[col][row].getStyleClass().addAll("bomb3");
                            break;
                        case 4:
                            matrix[col][row].setText("bomb4");
                            //matrix[col][row].getStyleClass().addAll("bomb4");
                            break;
                        case 5:
                            matrix[col][row].setText("bomb5");
                            //matrix[col][row].getStyleClass().addAll("bomb5");
                            break;
                        case 6:
                            matrix[col][row].setText("bomb6");
                            //matrix[col][row].getStyleClass().addAll("bomb6");
                            break;
                        case 7:
                            matrix[col][row].setText("bomb7");
                            //matrix[col][row].getStyleClass().addAll("bomb7");
                            break;
                        case 8:
                            matrix[col][row].setText("bomb8");
                            //matrix[col][row].getStyleClass().addAll("bomb8");
                            break;
                    
                        default:
                            break;
                    }
                }
            }
        }
    }

    private void disableButtons(){
        for (Button[] list: matrix){
            for (Button button: list){
                button.setDisable(true);
            }
        }
    }

    @FXML
    private void restartGame(){
        for (Button[] list: matrix){
            for (Button button: list){
                button.getStyleClass().setAll("button-game", "button-locked");
                button.setStyle("-fx-background-color: #aeaeae;");
                button.setText("");
                button.setDisable(false);
                isGenerated = false;
            }
        }
    }
}
