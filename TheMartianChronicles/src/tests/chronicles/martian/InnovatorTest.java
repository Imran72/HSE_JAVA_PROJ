package chronicles.martian;

import org.junit.jupiter.api.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InnovatorTest {

    @BeforeAll
    static void setUp() {
        System.out.println("Innovator's testing is running...");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("Innovator's testing have finished.");
    }

    @Test
    void hasChildWithValue() {
    }

    @Test
    void hasDescadantWithValue() {
    }

    @Test
    void expressDescadants() {
    }

    @Test
    void setValue() {
    }

    @Test
    void setParent() {
    }

    @Test
    void setDescadants() {
    }

    @Test
    void addChild() {
    }

    @Test
    void deleteChild() {
    }

    @Test
    void testToString() {
    }

    @Test
    void InitializeTest()
    {
        Innovator<Double> innovator1 = new Innovator<>(null, 1.2, false);
        Innovator<Double> innovator2 = new Innovator<>(innovator1, 1.4, false);

        assertEquals(1.2, innovator1.getValue());
        assertEquals(1.4, innovator2.getValue());

        assertEquals(null, innovator1.getParent());
        assertEquals(innovator1, innovator2.getParent());

//        assertEquals(innovator1.children, innovator1.getChildren());
//        assertEquals(null, innovator2.getChildren());

//        assertEquals(list, innovator1.getDescadants());
//        assertEquals(null, innovator2.getChildren());
    }
}