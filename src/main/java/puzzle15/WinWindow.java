package puzzle15;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class WinWindow {
    @FXML
    private Button button;
    @FXML
    private GridPane gridpane;

    private MyData numData;

    public void setWindow(int movesDone, long timePassed) throws StreamReadException, DatabindException, IOException{
        this.numData = new MyData();
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

        
        addRecord();
    }

    @FXML
    private void test(){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    private void addRecord(){
        File file = new File("src\\main\\resources\\puzzle15\\records.json");

        ObjectMapper mapper = new ObjectMapper();
        List<MyData> dataList = new ArrayList<>();

        try{
            dataList = mapper.readValue(file, new TypeReference<List<MyData>>() {});
        } catch (IOException e) {
            System.out.println("Il file json era vuoto");
        }

        dataList.add(numData);
        Collections.sort(dataList);
        
        // ELIMINAZIONE DI TUTTI I MyData TRANNE I PRIMI 10 //
        // dataList.subList(10, dataList.size()).clear();

        /*
        System.out.println("\nSTAMPA TOTALE DEL FILE records.json:");
        for (MyData datalist2 : dataList){
            System.out.println(datalist2);
        }
        */

        try {
            mapper.writeValue(file, dataList);
            System.out.println("SALVATAGGIO ESEGUITO");
        } catch (IOException e) {
            System.err.println("ERRORE NEL SALVATAGGIO: \n" + e);
        }
    }
}
