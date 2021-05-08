/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package ch.makery.address.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;


/**
 * Окно для изменения информации об адресате.
 */
public class PersonEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField patronymicField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField homePhoneField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField birthdayField;
    @FXML
    private TextArea textField;


    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Устанавливает сцену для этого окна.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Задаёт адресата, информацию о котором будем менять.
     *
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        patronymicField.setText(person.getPatronymic());
        phoneField.setText(person.getMobilePhoneNumber());
        homePhoneField.setText(person.getMobileHomePhoneNumber());
        addressField.setText(person.getAddress());
        birthdayField.setText(DateUtil.format(person.getBirthday().getValue()));
        birthdayField.setPromptText("дд.мм.гггг");
        textField.setText(person.getComments());
    }

    /**
     * Returns true, если пользователь кликнул OK, в другом случае false.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setAddress(addressField.getText());
            person.setPatronymic(patronymicField.getText());
            person.setMobileHomePhoneNumber(homePhoneField.getText());
            person.setMobilePhoneNumber(phoneField.getText());
            person.setComments(textField.getText());
            person.setBirthday(birthdayField.getText());
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Проверяет пользовательский ввод в текстовых полях.
     *
     * @return true, если пользовательский ввод корректен
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "Укажите имя!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "Укажите фамилию!\n";
        }
        if (patronymicField.getText() == null || patronymicField.getText().length() == 0) {
            errorMessage += "Укажите отчество!\n";
        }
        if (!isNumber(phoneField.getText())) {
            errorMessage += "Невалидный мобильный  номер";
        }
        if (!isNumber(homePhoneField.getText())) {
            errorMessage += "Невалидный домашний номер телефона";
        }

        if ((phoneField.getText() == null || phoneField.getText().length() == 0) &&
                (homePhoneField.getText() == null || homePhoneField.getText().length() == 0)) {
            errorMessage += "Укажите хоть один номер телефона!\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "Укажите дату рождения!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "Поле заполнено некорректно. Используйте формат дд.мм.гггг!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Поля заполнены некорректно");
            alert.setHeaderText("Пожалуйста, исправьте недопустимые поля");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
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