package hw5.mishanya.homework5;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class NewEditSceneController {
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
    private void cancelButtonClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void okButtonClick() {
        if (Objects.equals(((Stage) okButton.getScene().getWindow()).getTitle(), "New person")) {
            if (isNotValidToClickOK()) {
                AddressApp.alertShow("Все поля должны быть заполнены. Попробуйте еще раз.");
            } else {
                people.add(new Person(firstNameTextBox.getText(),
                        lastNameTextBox.getText(),
                        streetTextBox.getText(),
                        cityTextBox.getText(),
                        postalCodeTextBox.getText(),
                        birthdayTextBox.getText()));
                group.setPeople(people);
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
            }
        } else {
            if (isNotValidToClickOK()) {
                AddressApp.alertShow("Все поля должны быть заполнены. Попробуйте еще раз.");
            } else {
                String lastWord = ((Stage) okButton.getScene().getWindow()).getTitle()
                        .substring(((Stage) okButton.getScene().getWindow()).getTitle().lastIndexOf(" ") + 1);
                int index = Integer.parseInt(lastWord);
                people.get(index).setFirstName(firstNameTextBox.getText());
                people.get(index).setLastName(lastNameTextBox.getText());
                people.get(index).setStreet(streetTextBox.getText());
                people.get(index).setCity(cityTextBox.getText());
                people.get(index).setCode(postalCodeTextBox.getText());
                people.get(index).setBirthday(birthdayTextBox.getText());
                group.setPeople(people);
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
            }
        }
    }

    private boolean isNotValidToClickOK() {
        return firstNameTextBox.getText() == null || firstNameTextBox.getText().trim().isEmpty() ||
                lastNameTextBox.getText() == null || lastNameTextBox.getText().trim().isEmpty() ||
                streetTextBox.getText() == null || streetTextBox.getText().trim().isEmpty() ||
                cityTextBox.getText() == null || cityTextBox.getText().trim().isEmpty() ||
                postalCodeTextBox.getText() == null || postalCodeTextBox.getText().trim().isEmpty() ||
                birthdayTextBox.getText() == null || birthdayTextBox.getText().trim().isEmpty();
    }
}