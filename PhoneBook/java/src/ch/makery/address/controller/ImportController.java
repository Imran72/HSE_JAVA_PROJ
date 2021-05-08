/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package ch.makery.address.controller;

import ch.makery.address.MainClass;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ImportController {
    @FXML
    private TextField fileField;


    // Ссылка на главное приложение.
    private MainClass mainApp;
    private Stage dialogStage;
    private boolean okClicked = false;

    /**
     * Устанавливает сцену для этого окна.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainApp
     */
    public void setMainApp(MainClass mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Returns true, если пользователь кликнул OK, в другом случае false.
     *
     * @return
     */
    public String isOkClicked() {
        if (okClicked)
            return fileField.getText();
        return "";
    }

    /**
     * Метод валидирует введенную строку для импорта файлов, а также
     * данные находящиеся внутри файла
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            okClicked = true;
            dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ошибка импортации");
            alert.setHeaderText("Ошибка импортации");
            alert.setContentText("Некорректный путь или некорректные данные");

            alert.showAndWait();
        }
    }

    /**
     * Метод выбора файла инструмментами FileChooser
     */
    @FXML
    private void handleFindFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            fileField.setText(selectedFile.getAbsolutePath());
        }
    }


    /**
     * Вызывается, когда пользователь кликнул по кнопке Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        File file = new File(fileField.getText());
        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String data = inputStream.nextLine();
                String[] values = new String[8];
                String[] tmp = data.split(";");
                if (tmp.length > 8)
                    return false;
                System.arraycopy(tmp, 0, values, 0, tmp.length);
                if (values[0] == null || values[1] == null)
                    return false;
                if (values[3] == null && values[4] == null)
                    return false;
                if (isNumber(values[3]) || isNumber(values[3]))
                    return false;
                String[] date = values[6].split("\\.");
                if (!isValid(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]))) {
                    return false;
                }
            }
            inputStream.close();
        } catch (Exception e) {
            e.fillInStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Метод валидации даты
     * @param a
     * @param b
     * @param c
     * @return
     */
    public boolean isValid(int a, int b, int c) {
        try {
            LocalDate.of(a, b, c);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Метод валидации телефонного номера
     * @param a
     * @return
     */
    public boolean isNumber(String a) {
        for (char el : a.toCharArray()) {
            if (el < 48 || el > 57)
                return false;
        }
        return true;
    }
}
