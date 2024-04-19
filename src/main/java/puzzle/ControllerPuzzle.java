package puzzle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ControllerPuzzle {
    @FXML private GridPane matrice;

    private Button[][] matrix = new Button[10][10];

    @FXML
    private void initialize(){
        Image originalImage = new Image("file:src\\main\\resources\\images\\weirdphoto.jpg");

        double originalWidth = originalImage.getWidth();
        double originalHeight = originalImage.getHeight();

        System.out.println("tot width: " + originalWidth);
        System.out.println("tot height: " + originalHeight);
        
        // col - verticale  - heigth
        // row - orizontale - width
        int num1 = 1, num2 = 1;
        double pieceWidth = 0, pieceHeight = 0;
        if (originalHeight > originalWidth){
            System.out.println("test: " + (originalHeight/originalWidth) + " ? " + (((int)(originalHeight/originalWidth)) + 0.5));
            if ((originalHeight/originalWidth) >= (((int)(originalHeight/originalWidth)) + 0.5))
                num1 = (int) (originalHeight / originalWidth) + 1;
            else 
                num1 = (int) (originalHeight / originalWidth);
        }else {
            System.out.println("test: " + (originalWidth/originalHeight) + " ? " + (((int)(originalWidth/originalHeight)) + 0.5));
            if ((originalWidth/originalHeight) >= (((int)(originalWidth/originalHeight)) + 0.5))
                num2 = (int) (originalWidth / originalHeight) + 1;
            else 
                num2 = (int) (originalWidth / originalHeight);
        }

        if (num1 > 5) num1 = 5;
        if (num2 > 5) num2 = 5;
        
        pieceWidth = originalWidth / (num1 * 2);
        pieceHeight = originalHeight / (num2 * 2);
        
        System.out.println("orizonatali: " + pieceWidth);
        System.out.println("verticali  : " + pieceHeight);

        Button button;
        ImageView subImages = new ImageView(originalImage);
        for (int row = 0; row < num1*2; row++){
            for (int col = 0; col < num2*2; col++){
                subImages = new ImageView(originalImage);
                System.out.println(col*pieceWidth + "\t" + row*pieceHeight + "\t" + pieceWidth + "\t" + pieceHeight);
                subImages.setViewport(new javafx.geometry.Rectangle2D(col*pieceWidth, row*pieceHeight, pieceWidth, pieceHeight));
                subImages.setFitHeight(50);
                subImages.setFitWidth(50);
                button = new Button();
                // button.setStyle("-fx-max-heigth: 50px; -fx-max-width: 50px; -fx-background-size: 50px; -fx-background-repeat: no-repeat;");
                button.setGraphic(subImages);
                matrix[col][row] = button;
                matrice.add(button, col, row);
            }
        }
    }

    @FXML
    private void shuffle(){

        System.out.println("SHUFFLE");
    }
}
