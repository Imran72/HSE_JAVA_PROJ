package creatures.creature;

import java.util.Random;


public class Creature {
    static Random rnd = new Random();
    String name;
    double angle;
    double s_coeff;

    public Creature(String name, double angle) {
        this.name = name;
        this.angle = angle;
        this.s_coeff = rnd.nextDouble() ;
    }


}
