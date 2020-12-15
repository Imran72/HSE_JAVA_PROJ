/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package creatures.creature;


import creatures.cart.Cart;

/**
 * класс существа лебедь/рак/щука
 */
public class Creature implements Runnable {
    String name; // имя существа лебедь/рак/щука
    double angle; // угол наклона
    double s_coeff; // коэффициент
    Cart cart; // телега, которую тащат все существа

    /**
     * getter имени (name)
     *
     * @return имя существа лебедь/рак/щука
     */
    public String getName() {
        return name;
    }

    /**
     * getter угла (angle)
     *
     * @return угол наклона
     */
    public double getAngle() {
        return angle;
    }

    /**
     * getter коэффициента
     *
     * @return коэффициент
     */
    public double getS_coeff() {
        return s_coeff;
    }

    /**
     * Конструктор класса
     *
     * @param name  имя существа
     * @param angle угол поворота
     * @param cart  ссылка на телегу
     */
    public Creature(String name, double angle, Cart cart) {
        this.name = name;
        this.angle = angle;
        this.s_coeff = Math.random() * (10 - 1) + 1; // генерируем коэфф. [1;10)
        this.cart = cart;
    }

    /**
     * Метод pull() - это эмуляция попыток существа в течение 25 секунд
     * толкать тележку в своем направлении.
     * Стоит заметить, что процесс изменения координат синхронизирован, то бишь
     * единовременно это может сделать только один объект типа Creature
     * После чего этот объект уходит в спячку на какое-то сгенерированное время
     * После пробуждения вновь пытается толкнуть тележку
     * Если метод не работает ни с одним потоком, то объект вновь изменяет координаты, иначе
     * ждет свою условную "очередь"
     */
    public void Pull() {
        // 25 секунда от текущего времени
        long end = System.currentTimeMillis() + 25000;

        // до тех пор пока не прошло 25 секунд
        while (System.currentTimeMillis() < end) {
            synchronized (cart) {
                cart.setX(cart.getX() + s_coeff * Math.cos(Math.toRadians(angle))); // изменение х-коэффициента
                cart.setY(cart.getY() + s_coeff * Math.sin(Math.toRadians(angle))); // изменение y-коэффициента
                //System.out.println(String.format("%s сдвинул телегу. ", name) + cart.toString());
            }
            // засыпаем
            try {
                // генерируем время сна
                long sontime = (long) (Math.random() * (5000 - 1000 + 1) + 1000);
                Thread.sleep(sontime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * реализация метода интерфейса Runnable для использованя потоков
     * В потоке просто запускается метод pull()
     */
    @Override
    public void run() {
        Pull();
    }
}
