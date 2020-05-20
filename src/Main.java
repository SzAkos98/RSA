
import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner source = new Scanner(System.in);

        BigInteger p;
        BigInteger  q;
        BigInteger n;
        BigInteger  fin;
        BigInteger e;
        BigInteger d;
        BigInteger msg;

        System.out.println("Adjon meg egy prím számot!\n");
        p = source.nextBigInteger();

        System.out.println("\n Adjon meg mégegy prím számot!\n");
        q = source.nextBigInteger();

        n = p.multiply(q);

        fin = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.valueOf(3);

        while (!fin.gcd(e).equals(BigInteger.ONE)) {
            e = e.add(BigInteger.valueOf(2));
        }

        d = e.modInverse(fin);

        System.out.println("\n Adja meg az üzenetet amit titkosítani szeretne! \n");
        msg = source.nextBigInteger();

        System.out.println("p = " + p + "\n");
        System.out.println("q = " + q + "\n");
        System.out.println("n = " + n + "\n");
        System.out.println("fi(n) = " + fin + "\n");
        System.out.println("pk = " + n + ", " + e + "\n");
        System.out.println("sk = " + d + "\n");

        System.out.println("m = " + msg + "\n");

        msg = msg.modPow(e, n);
        System.out.println("c = " + msg + "\n");

        msg = msg.modPow(d, n);
        System.out.println("m= " + msg + "\n");
    }
}