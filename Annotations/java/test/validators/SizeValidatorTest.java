package validators;

import additional.ValidateException;
import forms.TestClass;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SizeValidatorTest {

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


        SizeValidator sizeValidator = new SizeValidator("path", 1 , 1);

        List<String> list1 = Arrays.asList(list);
        TestClass testClass1 = new TestClass(list1, "Roman", -100, 2, 3);

        var lst = sizeValidator.validate(testClass1.list);


        for (var el : lst) {
            Assertions.assertEquals("size must be in range 1 and 1", el.getMessage());
        }
    }
}