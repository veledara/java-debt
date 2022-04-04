module hw5.mishanya.homework5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens hw5.mishanya.homework5 to javafx.fxml;
    exports hw5.mishanya.homework5;
}