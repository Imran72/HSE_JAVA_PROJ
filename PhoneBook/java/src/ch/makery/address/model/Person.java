package ch.makery.address.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import ch.makery.address.util.DateUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.DatePicker;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Класс-модель для адресата (Person).
 *
 */
public class Person {

    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty patronymic;
    private SimpleStringProperty mobilePhoneNumber;
    private SimpleStringProperty homePhoneNumber;
    private SimpleStringProperty address;
    private ObjectProperty<DatePicker> birthday;
    private SimpleStringProperty comments;

    /**
     * Конструктор с некоторыми начальными данными.
     */

    public Person() {
        this.firstName = new SimpleStringProperty("");
        this.lastName = new SimpleStringProperty("");
        this.patronymic = new SimpleStringProperty("");
        this.address = new SimpleStringProperty("");
        LocalDateTime today = LocalDateTime.now();
        this.birthday = new SimpleObjectProperty<DatePicker>(new DatePicker(today.toLocalDate()));
        this.mobilePhoneNumber = new SimpleStringProperty("");
        this.homePhoneNumber = new SimpleStringProperty("");
        this.comments = new SimpleStringProperty("");
    }

    /**
     * Конструктор с данными введенные пользователям
     */
    public Person(String firstName, String lastName, String patronymic, String phone, String homePhone,
                  String address, String birthday, String comments) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.patronymic = new SimpleStringProperty(patronymic);


        if (birthday != null && !birthday.equals("null") && !birthday.equals("")) {
            String[] data = birthday.split("\\.");
            this.birthday = new SimpleObjectProperty<DatePicker>(new DatePicker(LocalDate.of(Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]))));
        } else {
            this.birthday = null;
        }
        this.address = new SimpleStringProperty(address);

        this.mobilePhoneNumber = new SimpleStringProperty(phone);
        this.homePhoneNumber = new SimpleStringProperty(homePhone);
        this.comments = new SimpleStringProperty(comments);
    }


    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getFirstName() {
        return firstName.getValue();
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }


    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getLastName() {
        return lastName.getValue();
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }


    public StringProperty patronymicProperty() {
        return patronymic;
    }


    public String getPatronymic() {
        return patronymic.getValue();
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = new SimpleStringProperty(patronymic);
    }


    public StringProperty addressProperty() {
        return address;
    }

    public String getAddress() {
        return address.getValue();
    }

    public void setAddress(String address) {
        this.address = new SimpleStringProperty(address);
    }


    public StringProperty commentsProperty() {
        return comments;
    }

    public String getComments() {
        return comments.getValue();
    }

    public void setComments(String comments) {
        this.comments = new SimpleStringProperty(comments);
    }


    public StringProperty mobilePhoneNumberProperty() {
        return mobilePhoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber.getValue();
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = new SimpleStringProperty(mobilePhoneNumber);
    }

    public StringProperty mobileHomePhoneNumberProperty() {
        return homePhoneNumber;
    }

    public String getMobileHomePhoneNumber() {
        return homePhoneNumber.getValue();
    }

    public void setMobileHomePhoneNumber(String mobileHomePhoneNumber) {
        this.homePhoneNumber = new SimpleStringProperty(mobileHomePhoneNumber);
    }


    public ObjectProperty<DatePicker> birthdayProperty() {
        return birthday;
    }

    public DatePicker getBirthday() {
        return birthday.getValue();
    }

    public void setBirthday(String birthday) {
        String[] data = birthday.split("\\.");
        this.birthday = new SimpleObjectProperty<DatePicker>(new DatePicker(LocalDate.of(Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]))));
    }

    /**
     *  Форматирование данных в строковый формат
     * @return
     */
    @Override
    public String toString() {
        if (birthday != null) {
            int year = birthday.getValue().getValue().getYear();
            int month = birthday.getValue().getValue().getMonthValue();
            int day = birthday.getValue().getValue().getDayOfMonth();
            return firstName.getValue() + ";" + lastName.getValue() + ";" + patronymic.getValue() + ";" + mobilePhoneNumber.getValue() + ";" + homePhoneNumber.getValue() + ";" +
                    address.getValue() + ";" + day + "." + month + "." + year + ";" + comments.getValue() + ";\n";
        } else {
            return firstName.getValue() + ";" + lastName.getValue() + ";" + patronymic.getValue() + ";" + mobilePhoneNumber.getValue() + ";" + homePhoneNumber.getValue() + ";" +
                    address.getValue() + ";" + null + ";" + comments.getValue() + ";\n";
        }

    }
}