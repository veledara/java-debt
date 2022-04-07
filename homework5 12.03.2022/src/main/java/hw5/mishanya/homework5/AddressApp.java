package hw5.mishanya.homework5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class AddressApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AddressApp.class.getResource("main-scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setTitle("AddressApp");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void alertShow(String alertText) {
        Alert fail = new Alert(Alert.AlertType.INFORMATION);
        fail.setTitle("Ошибка");
        fail.setHeaderText("Ошибка!");
        fail.setContentText(alertText);
        fail.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}