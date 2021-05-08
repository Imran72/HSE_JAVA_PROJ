/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package ch.makery.address.controller;

import ch.makery.address.MainClass;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class ExportController {
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
        }
    }

    /**
     * Метод выбора файла инструмментами FileChooser
     */
    @FXML
    private void handleFindFile() {
        DirectoryChooser chooser = new DirectoryChooser();
        File selectedFile = chooser.showDialog(null);

        fileField.setText(selectedFile.getAbsolutePath());
    }


    /**
     * Вызывается, когда пользователь кликнул по кнопке Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        if (!fileField.getText().isEmpty()) {
            return true;
        }
        return false;
    }
}
