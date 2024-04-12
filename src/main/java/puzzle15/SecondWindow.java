package puzzle15;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class SecondWindow {
    @FXML
    private Button button;
    @FXML
    private GridPane gridpane;

    private Data numData;

    public void setWindow(int movesDone, long timePassed) throws StreamReadException, DatabindException, IOException{
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
    private void saveRecord() throws StreamReadException, DatabindException, IOException{
        File file = new File("src\\main\\resources\\puzzle15\\records.json");

        ObjectMapper mapper = new ObjectMapper();

        List<Data> dataList2 = mapper.readValue(file, new TypeReference<List<Data>>() {});
        dataList2.add(numData);

        try {
            mapper.writeValue(file, dataList2);
            System.out.println("SALVATAGGIO ESEGUITO");
        } catch (IOException e) {
            System.err.println("ERRORE NEL SALVATAGGIO: " + e);
        }
    }
}
