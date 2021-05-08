package ch.makery.address.controller;

import ch.makery.address.MainClass;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PersonOverviewControllerTest {

    @BeforeAll
    static void setUp() {
        System.out.println("Beginning of testing");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("End of testing");
    }

    @Test
    void setMainApp() {
    }

    @Test
    void handleDeletePerson() throws IOException {
        MainClass mainClass = new MainClass();

    }

    @Test
    void handleNewPerson() {
    }

    @Test
    void handleEditPerson() {
    }

    @Test
    void handleSearch() {
    }
}