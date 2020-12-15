package creatures.cart;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    Cart cart = new Cart(1, -1);

    @BeforeAll
    static void setUp() {
        System.out.println("Начало тестирования класса \"Cart\"");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("Конец тестирования класса \"Cart\"");
    }

    @Test
    void getX() {
        assertEquals(1, cart.getX());
    }

    @Test
    void setX() {
        cart.setX(-1);
        assertEquals(-1, cart.getX());
    }

    @Test
    void getY() {
        assertEquals(-1, cart.getY());
    }

    @Test
    void setY() {
        cart.setY(1);
        assertEquals(1, cart.getY());
    }


    @Test
    void testToString() {
        String a = "Координаты тележки: (1,00, -1,00)";
        assertEquals(a, cart.toString());
    }
}