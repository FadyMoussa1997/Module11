module com.example.demodemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    opens com.example.demodemo to javafx.fxml;
    exports com.example.demodemo;
}