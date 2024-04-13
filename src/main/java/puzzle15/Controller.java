package puzzle15;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;

import java.util.Random;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    
    @FXML
    private GridPane matrice;

    private Button[][] matrix = new Button[4][4];
    private Random rand = new Random();
    private MyData moves = new MyData();
    private long start = System.currentTimeMillis();
    private long end = 0;

    @FXML
    private void initialize(){
        Button button;
        for (int row = 0; row < 4; row++){
            for (int col = 0; col < 4; col++){
                button = new Button("lab"+ (row * 4 + col + 1));
                button.getStyleClass().add("button-game");
                if (row*4+col+1 != 16)
                    button.setText(""+(row*4+col+1));
                else
                    button.setText("");
                
                button.setOnAction(event -> move(event));
                
                button.setDisable(true);
                matrix[row][col] = button;
                matrice.add(button, col, row);
            }
        }
        colorUpdate();
    }

    private void blockButtons(){
        for (Button[] list1 : matrix){
            for (Button button : list1){
                button.setDisable(true);
            }
        }
    }

    private void unblockButtons(){
        for (Button[] list1 : matrix){
            for (Button button : list1){
                button.setDisable(false);
            }
        }
    }

    private void move(ActionEvent event){
        /*
        // STAMPA TUTTA LA MATRICE 1 //
        for (Button[] lista1: matrix){
            for (Button lista2: lista1)
                System.out.print(lista2+"\t");
            System.out.println();    
        }
        */
        
        /*
        // STAMPA TUTTA LA MATRICE 2 //
        for (int i = 0; i < 4; i++){
            for (int k = 0; k < 4; k++){
                System.out.print((Button)matrix[i][k] + "\t");
            }
            System.out.println();
        }
        */

        int[] position = getPosition((Button) event.getSource());
        // System.out.println("POSIZIONE: " + position[0] + "\t" + position[1]);

        if (position[0] != 0){                                                  // NON CONTROLLA SOPRA
            if (((Button) matrix[position[0]-1][position[1]]).getText().equals("")){
                matrix[position[0]-1][position[1]].setText(matrix[position[0]][position[1]].getText());
                matrix[position[0]][position[1]].setText("");
                moves.incrementMoves();
            }
        }
        if (position[0] != 3){                                                  // NON CONTROLLA SOTTO
            if (((Button) matrix[position[0]+1][position[1]]).getText().equals("")){
                matrix[position[0]+1][position[1]].setText(matrix[position[0]][position[1]].getText());
                matrix[position[0]][position[1]].setText("");
                moves.incrementMoves();
            }
        }
        if (position[1] != 0){                                                  // NON CONTROLLA SINISTRA
            if (((Button) matrix[position[0]][position[1]-1]).getText().equals("")){
                matrix[position[0]][position[1]-1].setText(matrix[position[0]][position[1]].getText());
                matrix[position[0]][position[1]].setText("");
                moves.incrementMoves();
            }
        }
        if (position[1] != 3){                                                  // NON CONTROLLA DESTRA
            if (((Button) matrix[position[0]][position[1]+1]).getText().equals("")){
                matrix[position[0]][position[1]+1].setText(matrix[position[0]][position[1]].getText());
                matrix[position[0]][position[1]].setText("");
                moves.incrementMoves();
            }
        }

        colorUpdate();
        checkGame();
    }


    private int[] getPosition(Button button){
        for (int row = 0; row < 4; row++){
            for (int col = 0; col < 4; col++){
                if (matrix[row][col].equals(button)){
                    int[] position = {row, col};
                    return position;
                }
            }
        }
        int[] position = {0,0};
        return position;
    }


    private void colorUpdate(){
        for (int row = 0; row < 4; row++){
            for (int col = 0; col < 4; col++){
                if (((Button) matrix[row][col]).getText() != ""){
                    if (Integer.parseInt(matrix[row][col].getText()) == row * 4 + col + 1){
                        matrix[row][col].setStyle("-fx-border-color: green;");
                    }
                    else {
                        matrix[row][col].setStyle("-fx-border-color: red;");
                    }
                } 
                else {
                    if (row == 3 && col == 3){
                        matrix[row][col].setStyle("-fx-border-color: green;");
                    }
                    else {
                        matrix[row][col].setStyle("-fx-border-color: red;");
                    }
                }
            }
        }
    }

    private boolean checkGame(){
        for (int row = 0; row < 4; row++){
            for (int col = 0; col < 4; col++){
                if(((Button) matrix[row][col]).getStyle() == "-fx-border-color: red;"){
                    return false;
                }       
            }
        }
        blockButtons();
        end = System.currentTimeMillis();
        openSecondWindow();
        moves.setMoves(0);
        start = System.currentTimeMillis();
        return true;
    }

    @FXML
    private void shuffle(){
        unblockButtons();
        start = System.currentTimeMillis();
        int num;

        for (int i = 0; i < 500; i++){
            num = rand.nextInt(4);

            switch (num) {
                case 0:
                    moveUp();
                    break;
                case 1:
                    moveDown();
                    break;
                case 2:
                    moveLeft();
                    break;
                case 3:
                    moveRight();
                    break;
            }
        }
        colorUpdate();
        moves.setMoves(0);
    }



    private boolean moveUp(){
        int[] position = findEmpty();
        if (position[0] != 3){
            matrix[position[0]][position[1]].setText(matrix[position[0]+1][position[1]].getText());
            matrix[position[0]+1][position[1]].setText("");
            moves.incrementMoves();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean moveDown(){
        int[] position = findEmpty();
        if (position[0] != 0){
            matrix[position[0]][position[1]].setText(matrix[position[0]-1][position[1]].getText());
            matrix[position[0]-1][position[1]].setText("");
            moves.incrementMoves();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean moveLeft(){
        int[] position = findEmpty();
        if (position[1] != 3){
            matrix[position[0]][position[1]].setText(matrix[position[0]][position[1]+1].getText());
            matrix[position[0]][position[1]+1].setText("");
            moves.incrementMoves();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean moveRight(){
        int[] position = findEmpty();
        if (position[1] != 0){
            matrix[position[0]][position[1]].setText(matrix[position[0]][position[1]-1].getText());
            matrix[position[0]][position[1]-1].setText("");
            moves.incrementMoves();
            return true;
        }
        else {
            return false;
        }
    }



    private int[] findEmpty(){
        for (int row = 0; row < 4; row++){
            for (int col = 0; col < 4; col++){
                if (((Button) matrix[row][col]).getText() == ""){
                    int[] position = {row, col};
                    return position;
                }
            }
        }
        int[] position = {0, 0};
        return position;
    }

    @FXML
    public void arrowPressed(KeyEvent key){
        if (!matrix[0][0].isDisable()){
            switch (key.getCode()) {
                case UP:
                    moveUp();
                    break;
                case DOWN:
                    moveDown();
                    break;
                case LEFT:
                    moveLeft();
                    break;
                case RIGHT:
                    moveRight();
                    break;
                case ENTER:
                    shuffle();
            
                default:
                    break;
            }
            colorUpdate();
            checkGame();
        }
    }

    @FXML
    private void showLeaderboard(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("structure3.fxml"));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Leaderboard");
            stage.setWidth(350);
            stage.setHeight(500);

            stage.getIcons().add(new Image("file:src\\main\\resources\\puzzle15\\crown.png"));
            Scene scene = root.getScene();
            scene.getStylesheets().add(getClass().getResource("style2.css").toExternalForm());
            stage.show();
        } catch (IOException e) {
            System.out.println("ERRORE:\n" + e);
        }
    }

    private void openSecondWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("structure2.fxml"));
            Parent root = loader.load();
            
            WinWindow controller = loader.getController();
            controller.setWindow(moves.getMoves(), (end - start)/1000);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("You Won");
            stage.setWidth(350);
            stage.setHeight(200);
            
            stage.getIcons().add(new Image("file:src\\main\\resources\\puzzle15\\logo.png"));
            Scene scene = root.getScene();
            scene.getStylesheets().add(getClass().getResource("style2.css").toExternalForm());
            stage.show();
        } catch (IOException e) {
            System.out.println("ERRORE:\n" + e);
        }
    }

}