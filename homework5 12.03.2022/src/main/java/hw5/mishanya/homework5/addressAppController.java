package hw5.mishanya.homework5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class addressAppController {
    public ObservableList<Person> peopleData = FXCollections.observableArrayList();

    Stage stage = new Stage();

    Group group;

    public Label firstNameLabel;
    public Label lastNameLabel;
    public Label streetLabel;
    public Label cityLabel;
    public Label postalCodeLabel;
    public Label birthdayLabel;

    public TableView<Person> table;

    public TableColumn<Person, String> firstNameColumn;

    public TableColumn<Person, String> lastNameColumn;

    public void initialize() {
        group = Group.getInstance();
        group.setPeople(new ArrayList<>());
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        TableView.TableViewSelectionModel<Person> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((val, oldVal, newVal) -> {
            if (newVal != null) {
                firstNameLabel.setText(newVal.getFirstName());
                lastNameLabel.setText(newVal.getLastName());
                streetLabel.setText(newVal.getStreet());
                cityLabel.setText(newVal.getCity());
                postalCodeLabel.setText(newVal.getCode());
                birthdayLabel.setText(newVal.getBirthday());
            }
        });
    }

    @FXML
    public void editButtonClick() {
    }

    @FXML
    public void newButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AddressApp.class.getResource("new-edit-scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        stage.setTitle("New person");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void deleteButtonClick() {
    }

    private void initData() {
        peopleData.addAll(group.getPeople());
    }
}