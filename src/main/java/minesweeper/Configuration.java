package minesweeper;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Configuration {
    @FXML private TextField row;
    @FXML private TextField col;
    @FXML private TextField bomb;

    private MineController parentController;

    public void setWindow(MyInfo info){
        row.setText(String.valueOf(info.getRow()));
        col.setText(String.valueOf(info.getCol()));
        bomb.setText(String.valueOf(info.getBomb()));
    }

    public void setController(MineController controller){
        this.parentController = controller;
    }

    @FXML
    private void exit(){
        int numRow = Integer.parseInt(row.getText());
        int numCol = Integer.parseInt(col.getText());
        int numBomb = Integer.parseInt(bomb.getText());

        if (numRow <= 20 && numCol <= 25 && numBomb < ((numRow*numCol)-9)){
            MyInfo newInfo = new MyInfo(numRow, numCol, numBomb);
            parentController.setInfo(newInfo);
        } else {
            System.out.println("New info not usable");
        }

        Stage stage = (Stage) row.getScene().getWindow();
        stage.close();
    }
}
