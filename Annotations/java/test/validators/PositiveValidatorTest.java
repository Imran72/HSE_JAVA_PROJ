package validators;

import additional.ValidateException;
import forms.TestClass;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PositiveValidatorTest {

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


        PositiveValidator positiveValidator = new PositiveValidator("path");

        List<String> a = Arrays.asList(list);
        TestClass testClass1 = new TestClass(a, "Roman", -100, 2, 3);

        var lst = positiveValidator.validate(testClass1.x);


        for (var el : lst) {
            Assertions.assertEquals("must be positive", el.getMessage());
        }
    }
}