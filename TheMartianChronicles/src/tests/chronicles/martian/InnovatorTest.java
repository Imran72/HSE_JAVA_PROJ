package chronicles.martian;

import org.junit.jupiter.api.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InnovatorTest {

    private Innovator<String> innnovator1 = new Innovator<>(null, "John", false);
    private Innovator<String> innnovator2 = new Innovator<>(innnovator1, "James", false);
    private Innovator<String> innnovator3 = new Innovator<>(innnovator2, "Jo", false);

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
        innnovator1.AddChild(innnovator2);
        innnovator2.AddChild(innnovator3);

        assertEquals(true, innnovator1.hasChildWithValue("James"));
        assertEquals(false, innnovator1.hasDescadantWithValue("James"));
    }

    @Test
    void hasDescadantWithValue() {
        innnovator1.AddChild(innnovator2);
        innnovator2.AddChild(innnovator3);

        assertEquals(true, innnovator1.hasDescadantWithValue("Jo"));
        assertEquals(false, innnovator1.hasChildWithValue("Jo"));
    }

    @Test
    void expressDescadants() {
        innnovator1.AddChild(innnovator2);
        innnovator2.AddChild(innnovator3);

        assertEquals(innnovator2, innnovator3.getParent());
        assertEquals(innnovator1, innnovator2.getParent());

        List<IMartian<String>> list = new ArrayList<>();
        list.add(innnovator3);
        list.add(innnovator2);

        assertEquals(list, innnovator1.getDescadants());
    }

    @Test
    void setValue() {
        innnovator1.setValue("Alakazam");
        assertEquals("Alakazam", innnovator1.getValue());
    }

    @Test
    void setParent() {
        innnovator1.setParent(innnovator2);
        assertEquals(innnovator2, innnovator1.getParent());
    }

    @Test
    void setDescadants() {
        List<IMartian<String>> list = new ArrayList<>();
        list.add(innnovator1);
        list.add(innnovator2);
        list.add(innnovator3);
        try {
            innnovator1.setDescadants(list);
        } catch (RuntimeException e) {
            assertEquals("Невозможно быть родителем самому себе!", e.getMessage());
        }

        list.remove(innnovator1);

        innnovator1.setDescadants(list);
        assertEquals(list, innnovator1.getDescadants());
        assertEquals(list, innnovator1.getChildren());
    }

    @Test
    void addChild() {
        innnovator1.AddChild(innnovator2);
        innnovator1.AddChild(innnovator3);
        List<IMartian<String>> list = new ArrayList<>();
        list.add(innnovator2);
        list.add(innnovator3);
        assertEquals(list, innnovator1.getChildren());
        assertEquals(list, innnovator1.getDescadants());
    }

    @Test
    void deleteChild() {
        innnovator1.AddChild(innnovator2);
        innnovator1.AddChild(innnovator3);
        innnovator1.DeleteChild(innnovator2);
        List<IMartian<String>> list = new ArrayList<>();
        list.add(innnovator3);
        assertEquals(list, innnovator1.getChildren());
        assertEquals(list, innnovator1.getDescadants());
    }

    @Test
    void testToString() {
        String textPresentation = "InnovatorMartian (String:John)";
        assertEquals(textPresentation, innnovator1.toString());
    }

    @Test
    void InitializeTest() {
        Innovator<Double> innovator1 = new Innovator<>(null, 1.2, false);
        Innovator<Double> innovator2 = new Innovator<>(innovator1, 1.4, false);
        innovator1.AddChild(innovator2);

        assertEquals(1.2, innovator1.getValue());
        assertEquals(1.4, innovator2.getValue());

        assertEquals(null, innovator1.getParent());
        assertEquals(innovator1, innovator2.getParent());

        assertEquals(innovator1.children, innovator1.getChildren());
        assertEquals(null, innovator2.getChildren());

        assertEquals(innovator1.descadants, innovator1.getDescadants());
        assertEquals(null, innovator2.getDescadants());
    }
}