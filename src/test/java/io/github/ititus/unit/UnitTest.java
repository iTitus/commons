package io.github.ititus.unit;

public class UnitTest {

    public static void main(String[] args) {
        Quantity<Length> l_km = Length.KILOMETRE.get(15.2);
        System.out.println(l_km);

        Quantity<Length> l_mi = l_km.convertTo(Length.MILE);
        System.out.println(l_mi);

        Quantity<Time> t = Time.HOUR.get(1.7);
        Quantity<Speed> v = l_mi.divide(t).as(Speed.SPEED).convertTo(Speed.METRES_PER_SECOND);
        System.out.println(v);

    }
}
