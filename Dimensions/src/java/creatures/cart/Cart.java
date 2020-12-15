/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package creatures.cart;

import java.util.TimerTask;

/**
 *  класс тележки, которую толкают
 */
public class Cart extends TimerTask {
    double x; // координата по оси абцисс
    double y; // координата по оси ординат


    /** Конструктор класса
     *
     * @param x координата по оси абцисс
     * @param y координата по оси ординат
     */
    public Cart(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /** Getter от переменной x
     *
     * @return координату по оси абцисс - x
     */
    public double getX() {
        return x;
    }

    /** Setter от переменной x
     *
     * @return устанавливает новое значение координате по оси абцисс - x
     */
    public void setX(double value) {
        this.x = value;
    }

    /** Getter от переменной y
     *
     * @return координату по оси ординат - y
     */
    public double getY() {
        return y;
    }

    /** Setter от переменной y
     *
     * @return координату по оси ординат - y
     * */
    public void setY(double value) {
        this.y = value;
    }

    /**
     * переопределенный метод toString()
     * @return возвращает информацию о состоянии тележки
     */
    @Override
    public String toString() {
        return String.format("Координаты тележки: (%.2f, %.2f)", x, y);
    }

    /**
     * поскольку класс наследуется от TimerTask
     * у него есть свой поток регистрации времени
     * Каждый 2 секунды выводится информация о состоянии телеги
     */
    @Override
    public void run() {
        System.out.println(toString());
    }
}
