/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package ch.makery.address.controller;

import ch.makery.address.MainClass;
import ch.makery.address.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

import java.io.*;

public class RootLayout {
    private TableView<Person> personTable;


    // Ссылка на главное приложение.
    private MainClass mainApp;


    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainApp
     * @param personTable
     */
    public void setMainApp(MainClass mainApp, TableView<Person> personTable) {
        this.mainApp = mainApp;
        this.personTable = personTable;

    }


    /**
     * Вызывается, когда пользователь кликает по кнопке удаления.
     */
    @FXML
    private void handleDeletePerson() throws IOException {
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
     * Вызывается, когда пользователь кликает по кнопке New...
     * Открывает диалоговое окно с дополнительной информацией нового адресата.
     */
    @FXML
    private void handleNewPerson() throws IOException {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }

        mainApp.WriteData();
    }

    /**
     * Вызывается, когда пользователь кликает по кнопка Edit...
     * Открывает диалоговое окно для изменения выбранного адресата.
     */
    @FXML
    private void handleEditPerson() throws IOException {
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
     * Вызывается при "Настрокйки" -> "Выход"
     * Пользователь тем самым закрывает приложение
     *
     * @throws Exception
     */
    @FXML
    private void handleExit() throws Exception {
        mainApp.stop();
    }


    /**
     * Вызывается при "Настрокйки" -> "Справка"
     * На экран выводится модальное окно с информацией о приложении
     * и некоторыми положениями полезными для пользователей
     *
     * @throws IOException
     */
    @FXML
    private void handleInformationDesk() throws IOException {
        mainApp.showInformationDesk();
    }


    /**
     * Вызывается при "Настрокйки" -> "Экпортировать"
     * На экран выводится модальное окно с возможностью экспортировать данные о контактах в
     * любую директорию
     *
     * @throws IOException
     */
    @FXML
    private void handleExportContacts() throws IOException {
        String filename = mainApp.showExportDesk("Экспорт контактов");
        if (filename != "") {
            File file = new File(filename + "/contacts.csv");
            file.createNewFile();
            try {
                FileWriter fileWriter = new FileWriter(filename + "/contacts.csv");
                PrintWriter printWriter = new PrintWriter(fileWriter);

                for (Person person : mainApp.getPersonData()) {
                    printWriter.print(person.toString());
                }
                printWriter.close();

            } catch (FileNotFoundException e) {
                e.fillInStackTrace();
            }
        }
    }

    /**
     * Вызывается при "Настрокйки" -> "Импортировать"
     * На экран выводится модальное окно с возможностью импортировать данные откуда-то
     * извне
     *
     * @throws IOException
     */
    @FXML
    private void handleImportContacts() throws IOException {
        String file = mainApp.showImportDesk("Импорт контактов");
        if (file != "") {
            mainApp.AddData(mainApp.getPersonData(), file);
            mainApp.WriteData();
        }
    }
}
