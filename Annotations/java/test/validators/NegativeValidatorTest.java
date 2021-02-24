package validators;

import additional.ValidateException;
import annotations.Negative;
import forms.TestClass;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NegativeValidatorTest {

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


        NegativeValidator negativeValidator = new NegativeValidator("path");

        List<String> a = Arrays.asList(list);
        TestClass testClass1 = new TestClass(a, "Roman", 100, 2, 3);

        var lst = negativeValidator.validate(testClass1.x);


        for (var el : lst) {
            Assertions.assertEquals("must be negative", el.getMessage());
        }
    }
}