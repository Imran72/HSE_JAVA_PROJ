package creatures.creature;

import creatures.cart.Cart;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {
    Cart cart = new Cart(0, 0);
    Creature creature1 = new Creature("creature1", 60, cart);
    Creature creature2 = new Creature("creature2", 180, cart);

    @BeforeAll
    static void setUp() {
        System.out.println("Начало тестирования класса \"Creature\"");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("Конец тестирования класса \"Creature\"");
    }

    @Test
    void getName() {
        assertEquals("creature1", creature1.getName());
        assertEquals("creature2", creature2.getName());
    }

    @Test
    void getAngle() {
        assertEquals(60, creature1.getAngle());
        assertEquals(180, creature2.getAngle());
    }

    @Test
    void getS_coeff() {
        assertTrue(1 <= creature1.getS_coeff() && creature1.getS_coeff() < 10);
        assertTrue(1 <= creature2.getS_coeff() && creature2.getS_coeff() < 10);
    }


    /**
     * проверяем укладывается ли по скорости работа с потоками в 25 секунд
     * эмулируем реальные потоки под это дело
     */
    @Test
    void pull() {
        // создаем поток под каждое животное
        Thread t1 = new Thread(creature1, creature1.getName());
        Thread t2 = new Thread(creature2, creature2.getName());

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();

        try {
            t1.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(25000 <= System.currentTimeMillis() - start);
    }
}