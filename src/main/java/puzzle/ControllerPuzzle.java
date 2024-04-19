package puzzle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class ControllerPuzzle {
    @FXML private GridPane matrice;

    private Button[][] matrix = new Button[8][4];

    @FXML
    private void initialize(){
        Image originalImage = new Image("file:src\\main\\resources\\images\\orangecar.jpeg");

        // Dimensioni dell'immagine originale
        double originalWidth = originalImage.getWidth();
        double originalHeight = originalImage.getHeight();

        // Definisci le dimensioni dei pezzi dell'immagine
        double pieceWidth = originalWidth / 8;
        double pieceHeight = originalHeight / 4;

        System.out.println("tot width: " + originalWidth);
        System.out.println("tot height: " + originalHeight);
        System.out.println("width: " + pieceWidth);
        System.out.println("height: " + pieceHeight);

        /*
        ImageView subImages = new ImageView(originalImage);
        for (int row = 0; row < pieceHeight*3; row = row + (int) pieceHeight){
            for (int col = 0; col < pieceWidth*7; col = col + (int) pieceWidth){
                subImages.setViewport(new javafx.geometry.Rectangle2D(col, row, pieceWidth, pieceHeight));
            }
        }
        */
        
    }

    @FXML
    private void shuffle(){
        System.out.println("SHUFFLE");
    }
}
