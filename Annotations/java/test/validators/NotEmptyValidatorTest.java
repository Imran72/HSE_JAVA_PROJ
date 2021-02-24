package validators;

import additional.ValidateException;
import forms.TestClass;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NotEmptyValidatorTest {

    @BeforeAll
    static void setUp() {
        System.out.println("Beginning of testing");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("End of testing");
    }

    @Test
    void validate() throws ValidateException {
        String[] list = {"TV", "Kitchen"};


        NotEmptyValidator notEmptyValidator = new NotEmptyValidator("path");

        List<String> a = Arrays.asList(list);
        TestClass testClass1 = new TestClass(a, "", 100, 2, 3);

        var lst = notEmptyValidator.validate(testClass1.name);


        for (var el : lst) {
            Assertions.assertEquals("must not be empty", el.getMessage());
        }
    }
}