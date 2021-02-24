package validators;

import forms.TestClass;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NotNullValidatorTest {

    @BeforeAll
    static void setUp() {
        System.out.println("Beginning of testing");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("End of testing");
    }

    @Test
    void validate() {
        String[] list = {"TV", "Kitchen"};


        NotNullValidator notNullValidator = new NotNullValidator("path");

        List<String> a = Arrays.asList(list);
        TestClass testClass1 = new TestClass(a, null, 100, 2, 3);

        var lst = notNullValidator.validate(testClass1.name);


        for (var el : lst) {
            Assertions.assertEquals("must not be null", el.getMessage());
        }
    }
}