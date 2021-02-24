package validators;


import additional.ValidateException;
import forms.TestClass;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


class AnyOfValidatorTest {

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


        AnyOfValidator anyOfValidator = new AnyOfValidator("path", list);

        List<String> a = Arrays.asList(list);
        TestClass testClass1 = new TestClass(a, "Roman", 1, 2, 3);

        var lst = anyOfValidator.validate(testClass1.name);


        for (var el : lst) {
            Assertions.assertEquals("must be one of 'TV' 'Kitchen' ", el.getMessage());
        }

    }
}