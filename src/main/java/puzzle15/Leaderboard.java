package puzzle15;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
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

        for (MyData datalist2 : dataList){
            System.out.println(datalist2);
            
        }
    }

    @FXML
    private void test(){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
