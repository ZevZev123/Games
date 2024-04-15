module games {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires javafx.graphics;
    requires javafx.base;
    
    opens puzzle15 to javafx.fxml;
    exports puzzle15;
    
    opens minesweeper to javafx.fxml;
    exports minesweeper;
}
