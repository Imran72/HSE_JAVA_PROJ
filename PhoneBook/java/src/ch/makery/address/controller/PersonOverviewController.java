/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package ch.makery.address.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ch.makery.address.MainClass;
import ch.makery.address.model.Person;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PersonOverviewController {
    @FXML
    public TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person, String> patronymicColumn;
    @FXML
    private TableColumn<Person, String> phoneColumn;
    @FXML
    private TableColumn<Person, String> homePhoneColumn;
    @FXML
    private TableColumn<Person, String> AddressColumn;
    @FXML
    private TableColumn<Person, DatePicker> birthdayColumn;
    @FXML
    private TableColumn<Person, String> textColumn;

    @FXML
    private TextField textField;


    // Ссылка на главное приложение.
    private MainClass mainApp;

    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public PersonOverviewController() {
        personTable = new TableView<Person>();
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с 7 столбцами.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        patronymicColumn.setCellValueFactory(cellData -> cellData.getValue().patronymicProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().mobilePhoneNumberProperty());
        homePhoneColumn.setCellValueFactory(cellData -> cellData.getValue().mobileHomePhoneNumberProperty());
        AddressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        birthdayColumn.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());
        textColumn.setCellValueFactory(cellData -> cellData.getValue().commentsProperty());
    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainApp
     */
    public void setMainApp(MainClass mainApp) {
        this.mainApp = mainApp;

        // Добавление в таблицу данных из наблюдаемого списка
        personTable.setItems(mainApp.getPersonData());
    }


    /**
     * Вызывается, когда пользователь кликает по кнопке удаления.
     */
    @FXML
    public void handleDeletePerson() throws IOException {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
            mainApp.WriteData();
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ошибка удаления");
            alert.setHeaderText("Контакт не был выбран");
            alert.setContentText("Пожалуйста, выберите контакт в таблице.");

            alert.showAndWait();
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопке Добавить...
     * Открывает диалоговое окно с дополнительной информацией нового адресата.
     */
    @FXML
    public void handleNewPerson() throws IOException {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }

        mainApp.WriteData();
    }

    /**
     * Вызывается, когда пользователь кликает по кнопка Редактировать...
     * Открывает диалоговое окно для изменения выбранного адресата.
     */
    @FXML
    public void handleEditPerson() throws IOException {
        int index = personTable.getSelectionModel().getFocusedIndex();
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                mainApp.getPersonData().set(index, selectedPerson);
            }

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }

        mainApp.WriteData();
    }

    /**
     * Вызывается, когда пользователь нажимает на кнопку "Поиск"
     * В отношении введенных данных происходит фильтрации контактов с
     * последующим выводом на основной экран
     */
    @FXML
    public void handleSearch() {
        String text = textField.getText();
        if (!text.isEmpty()) {
            String[] words = text.split(" ");
            mainApp.ReadData(FXCollections.observableArrayList());
            personTable.getItems().clear();
            personTable.setItems(mainApp.getPersonData());
            List<Person> a = null;
            if (words.length == 1) {
                a = (personTable.getItems().stream().filter(item -> item.getLastName().equals(words[0])).collect(Collectors.toList()));
            } else if (words.length == 2) {
                a = (personTable.getItems().stream().filter(item -> item.getLastName().equals(words[0])
                        && item.getFirstName().equals(words[1])).collect(Collectors.toList()));
            } else if (words.length == 3) {
                a = (personTable.getItems().stream().filter(item -> item.getLastName().equals(words[0])
                        && item.getFirstName().equals(words[1]) && item.getPatronymic().equals(words[2])).collect(Collectors.toList()));
            } else {
                return;
            }
            ObservableList<Person> b = FXCollections.observableArrayList();
            ;
            for (Person person : a) {
                b.add(person);
            }
            mainApp.setPersonData(b);
        } else {
            mainApp.ReadData(FXCollections.observableArrayList());
        }

        personTable.getItems().clear();
        personTable.setItems(mainApp.getPersonData());
    }
}