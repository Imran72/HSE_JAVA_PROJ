/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package ch.makery.address;

import java.io.*;
import java.util.Scanner;

import ch.makery.address.controller.*;
import ch.makery.address.model.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainClass extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;


    /**
     * Данные, в виде наблюдаемого списка адресатов.
     */
    private ObservableList<Person> personData = FXCollections.observableArrayList();


    /**
     * Возвращает данные в виде наблюдаемого списка адресатов.
     *
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }

    public void setPersonData(ObservableList<Person> personData) {
        this.personData = personData;
    }

    public MainClass() {
        // В качестве образца добавляем некоторые данные
        ReadData(personData);
    }

    /**
     * Запус сцены
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Телефонная книга");

        RootLayout controller = initRootLayout();

        showPersonOverview(controller);


    }

    @Override
    public void stop() throws Exception {
        primaryStage.close();
    }

    /**
     * Инициализирует корневой макет.
     */
    public RootLayout initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClass.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            RootLayout controller = loader.getController();

            return controller;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Показывает в корневом макете сведения об адресатах.
     */
    public void showPersonOverview(RootLayout rootLayoutController) {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClass.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Помещаем сведения об адресатах в центр корневого макета.
            rootLayout.setCenter(personOverview);

            // Даём контроллеру доступ к главному приложению.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

            rootLayoutController.setMainApp(this, controller.personTable);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Открывает диалоговое окно для изменения деталей указанного адресата.
     * Если пользователь кликнул OK, то изменения сохраняются в предоставленном
     * объекте адресата и возвращается значение true.
     *
     * @param person - объект адресата, который надо изменить
     * @return true, если пользователь кликнул OK, в противном случае false.
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClass.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            if (person.getFirstName() != "")
                dialogStage.setTitle("Редактирование контакта");
            else
                dialogStage.setTitle("Добавление контакта");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Подгрузка view  c информацией о приложении
     */
    public void showInformationDesk() {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClass.class.getResource("view/InformationView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Модуль импорта данных извне
     * @param title
     * @return
     */
    public String showImportDesk(String title) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClass.class.getResource("view/ImportContacts.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            ImportController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Модуль экспорта данных извне
     * @param title
     * @return
     */
    public String showExportDesk(String title) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClass.class.getResource("view/ExportContacts.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            ExportController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Возвращает главную сцену.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Читаем данные из основного файла
     * @param contacts
     */
    public void ReadData(ObservableList<Person> contacts) {
        String filename = System.getProperty("user.dir") + "/src/ch/makery/address/data/contact.csv";
        File file = new File(filename);
        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String data = inputStream.nextLine();
                String[] values = new String[8];
                for (int i = 0; i < values.length; i++) {
                    values[i] = "";
                }
                String[] tmp = data.split(";");
                for (int i = 0; i < tmp.length; i++) {
                    values[i] = tmp[i];
                }
                contacts.add(new Person(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7]));
            }
            inputStream.close();
            personData = contacts;
        } catch (FileNotFoundException e) {
            e.fillInStackTrace();
        }
    }

    /**
     * Добавить данные в таблицу
     * @param contacts
     * @param filename
     */
    public void AddData(ObservableList<Person> contacts, String filename) {
        File file = new File(filename);
        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String data = inputStream.nextLine();
                String[] values = new String[8];
                for (int i = 0; i < values.length; i++) {
                    values[i] = "";
                }
                String[] tmp = data.split(";");
                for (int i = 0; i < tmp.length; i++) {
                    values[i] = tmp[i];
                }
                contacts.add(new Person(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7]));
            }
            inputStream.close();
            personData = contacts;
        } catch (FileNotFoundException e) {
            e.fillInStackTrace();
        }
    }

    /**
     * Метод записи данных из таблицы в основной файл
     * @throws IOException
     */
    public void WriteData() throws IOException {

        try {
            String file = System.getProperty("user.dir") + "/src/ch/makery/address/data/contact.csv";
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Person person : personData) {
                printWriter.print(person.toString());
            }
            printWriter.close();

        } catch (FileNotFoundException e) {
            e.fillInStackTrace();
        }
    }
}