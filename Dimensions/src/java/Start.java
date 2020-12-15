/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

import creatures.cart.Cart;
import creatures.creature.Creature;

import java.lang.Thread;
import java.util.Timer;

/**
 * класс с main
 */
public class Start {
    public static void main(String[] args) {
        double x = 0;
        double y = 0;
        try {
            if (args.length == 2) {
                x = Double.parseDouble(args[0]);
                y = Double.parseDouble(args[1]);
            } else if (args.length == 1)
                x = Double.parseDouble(args[0]);

            // запустим метод тяги телеги
            StartPull(x, y);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод запуска процесса
     * Создаем объекты, потоки и запускаем их
     *
     * @param x начальная x координата по x
     * @param y начальна y координата по y
     */
    public static void StartPull(double x, double y) {
        Cart cart = new Cart(x, y);  // создаем объект класса Cart (Телега)
        Creature swan = new Creature("Swan", 60, cart); // Создаем объект класса Creature - лебедь
        Creature cancer = new Creature("Сancer", 180, cart); // Создаем объект класса Creature - рак
        Creature pike = new Creature("Pike", 300, cart); // Создаем объект класса Creature - щука

        // создаем поток под каждое животное
        Thread swanTread = new Thread(swan, "Swan");
        Thread cancerTread = new Thread(cancer, "Cancer");
        Thread pikeTread = new Thread(pike, "Pike");


        // запускаем потоки, в которых животные пытаются толкать телегу в свою сторону
        swanTread.start();
        cancerTread.start();
        pikeTread.start();

        // поток-регистратор времени
        Timer timer = new Timer();
        // каждые две секунды возвращает информацию о состоянии телеги
        timer.schedule(cart, 0, 2000);

        // джойним все потоки
        try {
            swanTread.join();
            pikeTread.join();
            cancerTread.join();
            timer.cancel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // выводим информацию о состояни телеги
        System.out.println(String.format("По истечении 25 секунд конечное положение тележки: (%.2f, %.2f)", cart.getX(), cart.getY()));
    }

}
