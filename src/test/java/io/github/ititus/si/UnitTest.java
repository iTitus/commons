package io.github.ititus.si;

import io.github.ititus.si.quantity.Quantity;
import io.github.ititus.si.quantity.type.Length;
import io.github.ititus.si.quantity.type.Speed;
import io.github.ititus.si.quantity.type.Time;

public class UnitTest {

    public static void main(String[] args) {
        Quantity<Length> l_km = Length.KILOMETRE.get(15.2);
        System.out.printf("15.2 km = %s%n", l_km);

        Quantity<Length> l_mi = l_km.convertTo(Length.MILE);
        System.out.printf("15.2 km in mi = %s%n", l_mi);

        Quantity<Time> t = Time.HOUR.get(1.7);
        System.out.printf("1.7 h = %s%n", t);
        System.out.printf("1.7 h in s = %s%n", t.asStandard(Time.TIME));

        Quantity<Speed> v_mph = l_mi.divide(t).as(Speed.SPEED);
        System.out.printf("15.2 km in mi / 1.7 h in mi/h = %s%n", v_mph);
        System.out.printf("15.2 km in mi / 1.7 h in km/h = %s%n", v_mph.convertTo(Speed.KILOMETRES_PER_HOUR));
    }
}
