package puzzle;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ControllerPuzzle {
    @FXML private GridPane matrice;

    private Button[][] matrix = new Button[8][4];
    private int minNum = 4;
    private Random random = new Random();
    private int width = 0;
    private int height = 0;
    private Button buttonBackup = new Button();

    @FXML
    private void initialize(){
        buttonBackup.setId("-1");
        Image originalImage = new Image("file:src\\main\\resources\\images\\racoon.png");

        double originalWidth = originalImage.getWidth();
        double originalHeight = originalImage.getHeight();
        
        // col - verticale  - heigth
        // row - orizontale - width
        int num1 = 1, num2 = 1;
        double pieceWidth = 0, pieceHeight = 0;
        if (originalHeight > originalWidth){
            if ((originalHeight/originalWidth) >= (((int)(originalHeight/originalWidth)) + 0.5))
                num1 = (int) (originalHeight / originalWidth) + 1;
            else 
                num1 = (int) (originalHeight / originalWidth);
        }else {
            if ((originalWidth/originalHeight) >= (((int)(originalWidth/originalHeight)) + 0.5))
                num2 = (int) (originalWidth / originalHeight) + 1;
            else 
                num2 = (int) (originalWidth / originalHeight);
        }

        if (num1 > 5) num1 = 5;
        if (num2 > 5) num2 = 5;
        
        pieceWidth = originalWidth / (num2 * minNum);
        pieceHeight = originalHeight / (num1 * minNum);
        
        width = num1*minNum;
        height = num2*minNum;
        Button button;
        ImageView subImages = new ImageView(originalImage);
        for (int row = 0; row < width; row++){
            for (int col = 0; col < height; col++){
                subImages = new ImageView(originalImage);
                // System.out.println(col*pieceWidth + "\t" + row*pieceHeight + "\t" + pieceWidth + "\t" + pieceHeight + "\t" + col + "\t" + row);
                subImages.setViewport(new javafx.geometry.Rectangle2D(col*pieceWidth, row*pieceHeight, pieceWidth, pieceHeight));
                subImages.setFitHeight(98);
                subImages.setFitWidth(98);
                button = new Button();
                button.setId(""+(row*height+col));
                button.getStyleClass().addAll("button-game");
                button.setOnAction(event -> move(event));
                button.setGraphic(subImages);
                matrix[col][row] = button;
                matrice.add(button, col, row);
            }
        }
        shuffle();
    }
    
    private void move(ActionEvent event){
        Button button = (Button) event.getSource();
        if (buttonBackup.getId() == "-1"){
            buttonBackup.setGraphic(button.getGraphic());
            buttonBackup.setId(button.getId());
        } else {
            for (int row = 0; row < width; row++){
                for (int col = 0; col < height; col++){
                    if (matrix[col][row].getId() == buttonBackup.getId()){
                        matrix[col][row].setGraphic(button.getGraphic());
                        matrix[col][row].setId(button.getId());
                        button.setGraphic(buttonBackup.getGraphic());
                        button.setId(buttonBackup.getId());
                        buttonBackup.setId("-1");

                        if (checkWin()){
                            winWindow();
                        }
                        return;
                    }
                }
            }            
        }
    }
    
    private void winWindow(){
        System.out.println("YOU WON!");
    }
    
    @FXML
    private void shuffle(){
        int firstNewWidth;
        int firstNewHeight;
        int secondNewWidth;
        int secondNewHeight;
        int count = 0;
        buttonBackup.setId("-1");

        while (count < width*height){
            firstNewWidth = random.nextInt(width);
            firstNewHeight = random.nextInt(height);

            secondNewWidth = random.nextInt(width);
            secondNewHeight = random.nextInt(height);
            
            buttonBackup.setGraphic(matrix[firstNewHeight][firstNewWidth].getGraphic());
            buttonBackup.setId(matrix[firstNewHeight][firstNewWidth].getId());

            matrix[firstNewHeight][firstNewWidth].setGraphic(matrix[secondNewHeight][secondNewWidth].getGraphic());
            matrix[firstNewHeight][firstNewWidth].setId(matrix[secondNewHeight][secondNewWidth].getId());
            
            matrix[secondNewHeight][secondNewWidth].setGraphic(buttonBackup.getGraphic());
            matrix[secondNewHeight][secondNewWidth].setId(buttonBackup.getId());

            count++;
        }

        buttonBackup.setId("-1");
    }

    private boolean checkWin(){
        for (int row = 0; row < width; row++){
            for (int col = 0; col < height; col++){
                if (!matrix[col][row].getId().equals(""+((row*height)+col))){
                    return false;
                }
            }
        }
        return true;
    }
}
