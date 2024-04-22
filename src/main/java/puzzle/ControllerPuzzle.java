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

    @FXML
    private void initialize(){
        Image originalImage = new Image("file:src\\main\\resources\\images\\capy.jpg");

        double originalWidth = originalImage.getWidth();
        double originalHeight = originalImage.getHeight();

        System.out.println("tot width: " + originalWidth);
        System.out.println("tot height: " + originalHeight);
        
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
        
        System.out.println("orizonatali: " + num1*minNum);
        System.out.println("verticali  : " + num2*minNum);

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
                button.setId(""+((row*minNum)+col));
                button.getStyleClass().addAll("button-game");
                button.setOnAction(event -> move(event));
                button.setGraphic(subImages);
                matrix[col][row] = button;
                matrice.add(button, col, row);
            }
        }
    }

    private void move(ActionEvent event){
        Button button = (Button) event.getSource();
        System.out.println(button.getId());
    }

    @FXML
    private void shuffle(){
        int newWidth;
        int newHeight;
        Button buttonBackup;

        for (int row = 0; row < width; row++){
            for (int col = 0; col < height; col++){
                newWidth = random.nextInt(width);
                newHeight = random.nextInt(height);
            }
        }
        System.out.println("SHUFFLE");
    }

    private boolean checkWin(){
        return false;
    }
}
