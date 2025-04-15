module com.example.game_fighting {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.game_fighting to javafx.fxml;
    exports com.example.game_fighting;
}