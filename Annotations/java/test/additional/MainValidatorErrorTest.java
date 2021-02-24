package additional;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class MainValidatorErrorTest {

    MainValidatorError mainValidatorError = new MainValidatorError("must be positive", "propertyType",
            new StringBuilder("Imran"));

    @BeforeAll
    static void setUp() {
        System.out.println("beginning of testing MainValidatorError Class");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("End of testing MainValidatorError Class");
    }

    @Test
    void getMessage() {
        Assertions.assertEquals("must be positive", mainValidatorError.getMessage());
    }

    @Test
    void getPath() {
        Assertions.assertEquals("propertyType", mainValidatorError.getPath());
    }

    @Test
    void getFailedValue() {
        Assertions.assertEquals("Imran", mainValidatorError.getFailedValue().toString());
    }
}