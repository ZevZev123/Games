package puzzle15;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Leaderboard {
    @FXML
    private GridPane gridpane;
    @FXML
    private Button button;

    private Label[] matrix = new Label[20];

    @FXML
    private void initialize(){
        File file = new File("src\\main\\resources\\puzzle15\\records.json");

        ObjectMapper mapper = new ObjectMapper();
        List<MyData> dataList = new ArrayList<>();

        try{
            dataList = mapper.readValue(file, new TypeReference<List<MyData>>() {});
        } catch (IOException e) {
            System.out.println("Il file json era vuoto");
        }

        Label moves = new Label("");
        Label time = new Label("");

        for (int i = 0; i < 10; i++){
            // System.out.println(dataList.get(i));
            moves = new Label();
            time = new Label();
            
            moves.styleProperty().set("-fx-alignment:center;");
            time.styleProperty().set("-fx-alignment:center;");

            moves.setText(String.valueOf(dataList.get(i).getMoves()));
            time.setText(String.valueOf(dataList.get(i).getTime()));

            matrix[i*2] = moves;
            matrix[i*2 + 1] = time;
            gridpane.add(moves, 0, i);
            gridpane.add(time, 1, i);
            /*
            */
        }
    }

    @FXML
    private void test(){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
