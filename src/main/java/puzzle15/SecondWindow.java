package puzzle15;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import java.io.File;
import java.io.IOException;

public class SecondWindow {
    @FXML
    private Button button;
    @FXML
    private GridPane gridpane;

    private Data numData;

    public void setWindow(int movesDone, long timePassed){
        this.numData = new Data();
        numData.setMoves(movesDone);
        numData.setTime(timePassed);

        Label lab;
        if (numData != null){
            lab = new Label(String.valueOf(numData.getMoves()));
            gridpane.add(lab, 1, 0);
        } else {
            System.out.println("moves è nullo");
        }
        
        lab = new Label(String.valueOf(numData.getTime()) + " sec");
        gridpane.add(lab, 1, 1);

        saveRecord();
        System.out.println("Elementi salvati in json!");
    }

    @FXML
    private void test(){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveRecord(){
        File file = new File("src\\main\\resources\\puzzle15\\records.json");
        Data data;

        Map<String, Object> dataWrite = new HashMap<>();
        dataWrite.put("moves", numData.getMoves());
        dataWrite.put("time", numData.getTime());
        dataWrite.put("moves", numData.getMoves());
        dataWrite.put("time", numData.getTime());

        System.out.println(dataWrite);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, dataWrite);
            data = mapper.readValue(file, Data.class);
            System.out.println(data.getMoves() + "\t" + data.getTime());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
