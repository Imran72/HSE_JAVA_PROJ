package chronicles.martian;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ConservativeTest {

    private Innovator<String> innnovator1 = new Innovator<>(null, "John", false);
    private Innovator<String> innnovator2 = new Innovator<>(innnovator1, "James", false);
    private Innovator<String> innnovator3 = new Innovator<>(innnovator2, "Jo", false);

    @BeforeAll
    static void setUp() {
        System.out.println("Conservative's testing is running...");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("Conservative's testing have finished.");
    }

    @Test
    void hasChildWithValue() {
        innnovator1.AddChild(innnovator2);
        innnovator2.AddChild(innnovator3);
        Conservative<String> conservative1 = new Conservative<>(innnovator1, null);


        assertEquals(true, conservative1.hasChildWithValue("James"));
        assertEquals(false, conservative1.hasChildWithValue("Jo"));
    }

    @Test
    void hasDescadantWithValue() {
        innnovator1.AddChild(innnovator2);
        innnovator1.AddChild(innnovator3);
        Conservative<String> conservative1 = new Conservative<>(innnovator1, null);
        Conservative<String> conservative2 = new Conservative<>(innnovator2, null);
        Conservative<String> conservative3 = new Conservative<>(innnovator3, null);

        assertEquals(true, conservative1.hasDescadantWithValue("Jo") ||
                conservative1.hasDescadantWithValue("James"));
    }

    @Test
    void expressDescadants() {
        innnovator1.AddChild(innnovator2);
        innnovator1.AddChild(innnovator3);
        Conservative<String> conservative1 = new Conservative<>(innnovator1, null);
        Conservative<String> conservative2 = new Conservative<>(innnovator2, null);
        Conservative<String> conservative3 = new Conservative<>(innnovator3, null);

        assertEquals(conservative2.toString(), conservative1.ExpressDescadants().get(0).toString());
        assertEquals(conservative3.toString(), conservative1.ExpressDescadants().get(1).toString());
    }

    @Test
    void makeChildrenConservator() {
        innnovator1.AddChild(innnovator2);
        innnovator2.AddChild(innnovator3);
        Conservative<String> conservative = new Conservative<>(innnovator1, null);


        assertEquals("ConservativeMartian (String:James)", conservative.getChildren().get(0).toString());
    }

    @Test
    void testToString() {
        Innovator n1 = new Innovator(null, "Amanda", false);
        Conservative<String> conservative = new Conservative<>(n1, null);

        assertEquals("ConservativeMartian (String:Amanda)", conservative.toString());
    }

    @Test
    void hasChildren() {
        innnovator1.AddChild(innnovator2);
        innnovator2.AddChild(innnovator3);
        Conservative<String> conservative = new Conservative<>(innnovator1, null);

        assertEquals(true, conservative.hasChildren());
    }

    @Test
    void getValue() {
        Innovator n1 = new Innovator(null, "Amanda", false);
        Conservative<String> conservative = new Conservative<>(n1, null);
        assertEquals("Amanda", conservative.getValue());
    }

    @Test
    void getParent() {
        Innovator n1 = new Innovator(null, "Amanda", false);
        Innovator n2 = new Innovator(n1, "Amandan", false);

        n1.AddChild(n2);
        Conservative<String> conservative1 = new Conservative<>(n1, null);
        Conservative<String> conservative2 = new Conservative<>(n2, conservative1);

        assertEquals(null, conservative1.getParent());
        assertEquals(conservative1, conservative2.getParent());
    }

    @Test
    void getChildren() {
        innnovator1.AddChild(innnovator2);
        Conservative<String> conservative1 = new Conservative<>(innnovator1, null);
        Conservative<String> conservative2 = new Conservative<>(innnovator2, null);

        assertEquals(conservative2.toString(), conservative1.getChildren().get(0).toString());
    }

    @Test
    void getDescadants() {
        innnovator1.AddChild(innnovator2);
        Conservative<String> conservative1 = new Conservative<>(innnovator1, null);
        Conservative<String> conservative2 = new Conservative<>(innnovator2, null);

        assertEquals(conservative2.toString(), conservative1.getDescadants().get(0).toString());
    }
}