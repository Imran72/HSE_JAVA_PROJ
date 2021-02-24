package additional;

import forms.BookingForm;
import forms.GuestForm;
import interfaces.ValidationError;
import org.junit.jupiter.api.*;

import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;

class MainValidatorTest {

    @BeforeAll
    static void setUp() {
        System.out.println("beginning of testing MainValidator Class");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("End of testing MainValidator Class");
    }

    @Test
    void validate() throws ValidateException {
        List<GuestForm> guests = List.of(
                new GuestForm("Imran", "Timkanov", 19),
                new GuestForm(/*firstName*/ "Nadir", /*lastName*/ "Timkanov", /*age*/ -3)
        );

        BookingForm bookingForm = new BookingForm(
                guests,
                List.of("TV", "Piano"),
                "Apartment"
        );

        MainValidator validator = new MainValidator();

        Set<ValidationError> validationErrors = validator.validate(bookingForm);

        validationErrors.size();

        Assertions.assertEquals(4, validationErrors.size());
        List<String> messages = new ArrayList<String>();
        messages.add("must be one of 'TV' 'Kitchen' ");
        messages.add("must be positive");
        messages.add("must be in range between 0 and 100");
        messages.add("must be one of 'House' 'Hostel' ");


        for (var el: validationErrors) {
            Assertions.assertTrue(messages.contains(el.getMessage()));
        }

        List<String> paths = new ArrayList<String>();

        paths.add("amenities[1]");
        paths.add("guests[1].age");
        paths.add("propertyType");


        for (var el: validationErrors) {
            Assertions.assertTrue(paths.contains(el.getPath()));
        }

        validationErrors = validator.validate(new StringBuilder());

        Assertions.assertEquals(0, validationErrors.size());


    }

    @Test
    void validateList() throws IllegalAccessException, ValidateException {
        MainValidator validator = new MainValidator();
        List<GuestForm> guests = List.of(
                new GuestForm("Imran", "Timkanov", 19),
                new GuestForm(/*firstName*/ "Nadir", /*lastName*/ "Timkanov", /*age*/ -3)
        );

        BookingForm bookingForm = new BookingForm(
                guests,
                List.of("TV", "Piano"),
                "Apartment"
        );


        Class<?> classObj = requireNonNull(bookingForm).getClass();

        Field[] fields = classObj.getDeclaredFields();
        fields[0].setAccessible(true);
        var list = validator.validateList(bookingForm, fields[0]);

        Assertions.assertEquals(2, list.size());
        List<String> messages = new ArrayList<String>();
        messages.add("must be positive");
        messages.add("must be in range between 0 and 100");


        for (var el: list) {
            Assertions.assertTrue(messages.contains(el.getMessage()));
        }

        List<String> paths = new ArrayList<String>();

        paths.add("guests[1].age");


        for (var el: list) {
            Assertions.assertTrue(paths.contains(el.getPath()));
        }
    }

    @Test
    void validateElement() throws IllegalAccessException, ValidateException {
        MainValidator validator = new MainValidator();
        List<GuestForm> guests = List.of(
                new GuestForm("Imran", "Timkanov", 19),
                new GuestForm(/*firstName*/ "Nadir", /*lastName*/ "Timkanov", /*age*/ -3)
        );

        BookingForm bookingForm = new BookingForm(
                guests,
                null,
                "Apartment"
        );


        Class<?> classObj = requireNonNull(bookingForm).getClass();

        Field[] fields = classObj.getDeclaredFields();
        fields[0].setAccessible(true);

        var list = validator.validateElement(bookingForm, fields[0]);

        Assertions.assertEquals(0, list.size());


         guests = List.of(
        );

        bookingForm = new BookingForm(
                guests,
                null,
                "Apartment"
        );


        classObj = requireNonNull(bookingForm).getClass();

        fields = classObj.getDeclaredFields();
        fields[0].setAccessible(true);
        fields[1].setAccessible(true);

        list = validator.validateElement(bookingForm, fields[0]);
        var list2 = validator.validateElement(bookingForm, fields[1]);

        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(1, list2.size());


    }

    @Test
    void validateInList() throws ValidateException, IllegalAccessException {

        MainValidator validator = new MainValidator();
        List<GuestForm> guests = List.of(
                new GuestForm("Imran", "Timkanov", 19),
                new GuestForm(/*firstName*/ "Nadir", /*lastName*/ "Timkanov", /*age*/ -3)
        );

        BookingForm bookingForm = new BookingForm(
                guests,
                List.of("TV", "Piano", "df", "sdas"),
                "Apartment"
        );


        Class<?> classObj = requireNonNull(bookingForm).getClass();

        Field[] fields = classObj.getDeclaredFields();
        fields[1].setAccessible(true);

        var list = validator.validateInList(bookingForm, fields[1], 0);
        list.addAll(validator.validateInList(bookingForm, fields[1], 1));

        List<String> messages = new ArrayList<String>();
        messages.add("must be one of 'TV' 'Kitchen' ");


        for (var el: list) {
            System.out.println(el.getMessage());
            Assertions.assertTrue(messages.contains(el.getMessage()));
        }

        List<String> paths = new ArrayList<String>();

        paths.add("[1]");
        paths.add("[2]");
        paths.add("[3]");


        for (var el: list) {
            Assertions.assertTrue(paths.contains(el.getPath()));
        }




    }

    @Test
    void validateAnnotation() throws IllegalAccessException, ValidateException {
        MainValidator validator = new MainValidator();
        List<GuestForm> guests = List.of(
                new GuestForm("Imran", "Timkanov", 19),
                new GuestForm(/*firstName*/ "Nadir", /*lastName*/ "Timkanov", /*age*/ -3)
        );

        BookingForm bookingForm = new BookingForm(
                guests,
                List.of("TV", "Piano", "df"),
                "Apartment"
        );

        Class<?> classObj = requireNonNull(bookingForm).getClass();

        Field[] fields = classObj.getDeclaredFields();
        fields[1].setAccessible(true);

        AnnotatedType annotatedType = ((AnnotatedParameterizedType) fields[1].getAnnotatedType()).getAnnotatedActualTypeArguments()[0];

        var list = validator.validateAnnotation(((List<?>) fields[1].get(bookingForm)).get(0), annotatedType, "[0]");
        list.addAll(validator.validateAnnotation(((List<?>) fields[1].get(bookingForm)).get(1), annotatedType, "[1]"));
        list.addAll(validator.validateAnnotation(((List<?>) fields[1].get(bookingForm)).get(2), annotatedType, "[2]"));

        List<String> messages = new ArrayList<String>();
        messages.add("must be one of 'TV' 'Kitchen' ");

        List<String> paths = new ArrayList<String>();

        paths.add("[1]");
        paths.add("[2]");


        for (var el: list) {
            Assertions.assertTrue(messages.contains(el.getMessage()));
        }


        for (var el: list) {
            Assertions.assertTrue(paths.contains(el.getPath()));
        }

    }
}