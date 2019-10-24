import io.github.ititus.math.base.BaseConverters;
import io.github.ititus.math.base.binary.BinaryNumber;
import io.github.ititus.math.base.binary.QFixedPoint;
import io.github.ititus.math.base.binary.TwosComplement;
import io.github.ititus.math.number.BigRational;

public class BinaryCalc {

    public static void main(String[] args) {
        String hex1 = "12A";
        int dec1 = BaseConverters.HEXADECIMAL.decodeToInt(hex1);
        BinaryNumber bin1 = BaseConverters.BINARY.encodeToBinaryNumber(dec1);
        System.out.printf("%d %s %s%n", dec1, bin1, hex1);

        BinaryNumber bin2 = BinaryNumber.of("11_1000");
        int dec2 = BaseConverters.BINARY.decodeToInt(bin2);
        String hex2 = BaseConverters.HEXADECIMAL.encode(dec2);
        System.out.printf("%d %s %s%n", dec2, bin2, hex2);

        int dec = 1234;
        BinaryNumber bin = BaseConverters.BINARY.encodeToBinaryNumber(dec);
        System.out.printf("%d %s%n", dec, bin);

        QFixedPoint q = new QFixedPoint(4, 3);
        BinaryNumber a = q.encode(BigRational.of("-11.75"));
        BinaryNumber b = q.encode(BigRational.of("5.75"));

        System.out.println(a);
        System.out.println(b);
        System.out.println("done");

        TwosComplement intC = new TwosComplement(32);
        BinaryNumber minInt = intC.encode(Integer.MIN_VALUE);
        BinaryNumber maxInt = intC.encode(Integer.MAX_VALUE);
        int min = intC.decodeToInt(minInt);
        int max = intC.decodeToInt(maxInt);
        System.out.println(minInt + " | " + maxInt);
        System.out.println(min + " | " + max);

        TwosComplement c = new TwosComplement(34);
        System.out.println("expected = " + c.decodeToLong(c.encode((long) Integer.MIN_VALUE)));
        System.out.println("actual   = " + c.decodeToInt(c.encode(Integer.MIN_VALUE)));
    }
}
