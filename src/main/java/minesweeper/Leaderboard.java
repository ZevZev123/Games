package minesweeper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Leaderboard {
    @FXML
    private GridPane gridpane;
    @FXML
    private Button button;

    @FXML
    private void initialize(){
        gridpane.setStyle("-fx-justify-content: space-around;");

        File file = new File("src\\main\\resources\\minesweeper\\records.json");
        ObjectMapper objectMapper = new ObjectMapper();
        List<MyDataMine> myData = new ArrayList<>();
        try{
            myData = objectMapper.readValue(file, new TypeReference<List<MyDataMine>>() {});
        } catch (IOException e) {
            System.err.println("Error:\n" + e);
        }
        
        Label grid = new Label("");
        Label bombs = new Label("");
        Label time = new Label("");

        for (int i = 0; i < 10; i++){
            grid = new Label();
            bombs = new Label();
            time = new Label();
        
            
            grid.setText(String.valueOf(myData.get(i).getCol()) + "x" + String.valueOf(myData.get(i).getRow()));
            bombs.setText(String.valueOf(myData.get(i).getNumBomb()));
            time.setText(String.valueOf(myData.get(i).getTime()));
            
            gridpane.add(grid, 0, i);
            gridpane.add(bombs, 1, i);
            gridpane.add(time, 2, i);
        }
    }
    
    @FXML
    private void exit(){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
