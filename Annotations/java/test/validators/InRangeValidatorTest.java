package validators;

import additional.ValidateException;
import annotations.InRange;
import forms.TestClass;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InRangeValidatorTest {

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


        InRangeValidator inRangeValidator = new InRangeValidator("path", 1,2);

        List<String> a = Arrays.asList(list);
        TestClass testClass1 = new TestClass(a, "Roman", 1, 2, 3);

        var lst = inRangeValidator.validate(testClass1.a);
        lst.addAll(inRangeValidator.validate(testClass1.b));


        for (var el : lst) {
            Assertions.assertEquals("must be in range between 1 and 2", el.getMessage());
        }
    }
}