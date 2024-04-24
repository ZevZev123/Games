package puzzle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ControllerPuzzle {
    @FXML private GridPane matrice;
    @FXML private Label text;

    private Button[][] matrix = new Button[15][10];
    private int minNum = 4;
    private Random random = new Random();
    private int width = 0;
    private int height = 0;
    private Button buttonBackup = new Button();

    private List<String> images = new ArrayList<String>();

    @FXML
    private void initialize(){
        buttonBackup.setId("-1");
        text.setText("");

        File directory = new File("src\\main\\resources\\images\\puzzle");
        
        if (directory.exists() && directory.isDirectory()){
            File[] files = directory.listFiles();
            
            for (File file : files){
                if (file.isFile() && isImage(file.getName())){
                    images.add(file.getName());
                }
            }
        } else {
            System.out.println("DIRECTORY ERROR");
        }

        Image originalImage = new Image(String.format("file:src\\main\\resources\\images\\puzzle\\%s", images.get(random.nextInt(images.size()))));
        
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
                subImages.setFitHeight(99);
                subImages.setFitWidth(99);
                button = new Button();
                button.setId(""+(row*height+col));
                button.getStyleClass().addAll("button-game");
                button.setOnAction(event -> move(event));
                button.setGraphic(subImages);
                matrix[col][row] = button;
                matrice.add(button, col, row);
            }
        }

        Platform.runLater(() -> {
            Stage stage = (Stage) matrice.getScene().getWindow();
            stage.setWidth(110*height);
            stage.setHeight(150*width);

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double centerX = screenBounds.getMinX() + (screenBounds.getWidth() - stage.getWidth()) / 2;
            double centerY = screenBounds.getMinY() + (screenBounds.getHeight() - stage.getHeight()) / 2;

            stage.setX(centerX);
            stage.setY(centerY);
        });

        shuffle();
    }
    
    private boolean isImage(String fileName){
        String[] extentions = {".png", ".jpg", ".jpeg"};
        
        for (String extetion : extentions){
            if (fileName.toLowerCase().endsWith(extetion)){
                return true;
            }
        }
        return false;
    }

    @FXML private void imageChange(){
        System.out.println("Image changed");
        for (int row = 0; row < width; row++){
            for (int col = 0; col < height; col++){
                matrice.getChildren().remove(matrix[col][row]);
                matrix[col][row] = null;
            }
        }
        initialize();
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
        text.setText("YOU WON");
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
        text.setText("");

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
