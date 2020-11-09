package io.github.ititus.si;

import io.github.ititus.si.quantity.Quantity;
import io.github.ititus.si.quantity.type.Length;
import io.github.ititus.si.quantity.type.Speed;
import io.github.ititus.si.quantity.type.Time;

import static io.github.ititus.si.unit.Units.*;

public class UnitTest {

    public static void main(String[] args) {
        Quantity<Length> l_km = KILOMETRE.get(15.2);
        System.out.printf("15.2 km = %s%n", l_km);

        Quantity<Length> l_mi = l_km.convertTo(MILE);
        System.out.printf("15.2 km in mi = %s%n", l_mi);

        Quantity<Time> t = MINUTE.get(17.5);
        System.out.printf("17.5 min = %s%n", t);
        System.out.printf("17.5 min in s = %s%n", t.asStandard(Time.TIME));

        Quantity<Speed> v_mph = l_mi.divide(t).as(MILES_PER_HOUR);
        System.out.printf("15.2 km in mi / 17.5 min in mi/h = %s%n", v_mph);
        System.out.printf("15.2 km in mi / 17.5 min in km/h = %s%n", v_mph.convertTo(KILOMETRES_PER_HOUR));
        System.out.printf("15.2 km in mi / 17.5 min in m/s = %s%n", v_mph.convertTo(METRES_PER_SECOND));
    }
}
