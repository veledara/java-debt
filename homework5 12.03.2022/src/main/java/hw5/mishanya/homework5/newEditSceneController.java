package hw5.mishanya.homework5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class newEditSceneController {

    Group group;
    List<Person> people;
    public TextField firstNameTextBox;
    public TextField lastNameTextBox;
    public TextField streetTextBox;
    public TextField cityTextBox;
    public TextField postalCodeTextBox;
    public TextField birthdayTextBox;
    public Button okButton;
    public Button cancelButton;

    public void initialize() {
        group = Group.getInstance();
        people = group.getPeople();
    }

    @FXML
    public void cancelButtonClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void okButtonClick() throws IOException {
        if (firstNameTextBox.getText() == null || firstNameTextBox.getText().trim().isEmpty() ||
                lastNameTextBox.getText() == null || lastNameTextBox.getText().trim().isEmpty() ||
                streetTextBox.getText() == null || streetTextBox.getText().trim().isEmpty() ||
                cityTextBox.getText() == null || cityTextBox.getText().trim().isEmpty() ||
                postalCodeTextBox.getText() == null || postalCodeTextBox.getText().trim().isEmpty() ||
                birthdayTextBox.getText() == null || birthdayTextBox.getText().trim().isEmpty()
        ) {
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("Ошибка!");
            fail.setContentText("Все поля должны быть заполнены. Попробуйте еще раз.");
            fail.showAndWait();
        } else {
            Person person = new Person(firstNameTextBox.getText(),
                    lastNameTextBox.getText(),
                    streetTextBox.getText(),
                    cityTextBox.getText(),
                    postalCodeTextBox.getText(),
                    birthdayTextBox.getText());
            people.add(person);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-scene.fxml"));

            addressAppController controller = loader.getController();
            controller.peopleData.addAll(people);
            controller.table.setItems(controller.peopleData);

            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        }
    }
}