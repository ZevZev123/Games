module games {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires javafx.graphics;

    opens puzzle15 to javafx.fxml;
    exports puzzle15;

    opens minesweeper to java.fxml;
    exports minesweeper;
}
