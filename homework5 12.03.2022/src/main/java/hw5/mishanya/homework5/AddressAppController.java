package hw5.mishanya.homework5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddressAppController {
    public ObservableList<Person> peopleData = FXCollections.observableArrayList();

    Stage stage = new Stage();

    Group group;

    public Label firstNameLabel;
    public Label lastNameLabel;
    public Label streetLabel;
    public Label cityLabel;
    public Label postalCodeLabel;
    public Label birthdayLabel;

    public Button newButton;
    public Button editButton;

    public TableView<Person> table;

    public TableColumn<Person, String> firstNameColumn;

    public TableColumn<Person, String> lastNameColumn;

    public void initialize() {
        group = Group.getInstance();
        group.setPeople(new ArrayList<>());
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableView.TableViewSelectionModel<Person> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((val, oldVal, newVal) -> {
            if (newVal != null) {
                firstNameLabel.setText(newVal.getFirstName());
                lastNameLabel.setText(newVal.getLastName());
                streetLabel.setText(newVal.getStreet());
                cityLabel.setText(newVal.getCity());
                postalCodeLabel.setText(newVal.getCode());
                birthdayLabel.setText(newVal.getBirthday());
            } else {
                firstNameLabel.setText("");
                lastNameLabel.setText("");
                streetLabel.setText("");
                cityLabel.setText("");
                postalCodeLabel.setText("");
                birthdayLabel.setText("");
            }
        });
    }

    @FXML
    private void editButtonClick() throws IOException {
        if (!table.getItems().isEmpty()) {
                int selectedIndex = table.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                FXMLLoader fxmlLoader = new FXMLLoader(AddressApp.class.getResource("new-edit-scene.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 500, 300);
                stage.setTitle("Edit person " + selectedIndex);
                newEditSceneCreator(scene);
            } else {
                AddressApp.alertShow("???? ?????????????? ???????????? ?????? ????????????????????????????.");
            }

        } else {
            AddressApp.alertShow("?????????????? ??????????.");
        }

    }

    @FXML
    private void newButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AddressApp.class.getResource("new-edit-scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        stage.setTitle("New person");
        newEditSceneCreator(scene);
    }

    @FXML
    private void deleteButtonClick() {
        if (!table.getItems().isEmpty()) {
            int selectedIndex = table.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                List<Person> people = group.getPeople();
                people.remove(selectedIndex);
                group.setPeople(people);
                table.getItems().remove(selectedIndex);
            } else {
                AddressApp.alertShow("???? ?????????????? ???????????? ?????? ????????????????.");
            }
        } else {
            AddressApp.alertShow("?????????????? ??????????.");
        }
    }

    private void newEditSceneCreator(Scene scene) {
        if (stage.getModality() == Modality.NONE){
            stage.initModality(Modality.APPLICATION_MODAL);
        }
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
        table.getItems().clear();
        initData();
        table.setItems(peopleData);
    }

    private void initData() {
        peopleData.removeAll();
        peopleData.addAll(group.getPeople());
    }
}