package main;
import com.sun.deploy.security.SelectableSecurityManager;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static BigInteger temp;
    static boolean canWeStop = false;

    public static void main(String[] args) {
        Scanner source = new Scanner(System.in);

        BigInteger p;
        BigInteger  q;
        BigInteger n;
        BigInteger  fin;
        BigInteger e;
        BigInteger d;
        BigInteger msg;
        millerRabin(randNum());
        p = temp;
        millerRabin(p);
        q = temp;

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

    public static void millerRabin(BigInteger n) {
        BigInteger ranBig = randomBig(n);
        BigInteger div = BigInteger.valueOf(2);
        while(ranBig.mod(div).equals(BigInteger.ZERO)) {
            ranBig = randomBig(n);
        }
        BigInteger nMinusOne = ranBig.subtract(BigInteger.ONE);

        int s = 0;
        BigInteger d = BigInteger.ZERO;
        while (!canWeStop) {
            BigInteger bi[] = nMinusOne.divideAndRemainder(div);
            System.out.println(nMinusOne);

            //System.out.println(bi[0]);
            if (bi[1].equals(BigInteger.ZERO)) {
                nMinusOne = nMinusOne.divide(div);
                d = bi[1];
                s++;
            } else {
                d = bi[1];
                s++;
                canWeStop = true;
            }
        }

        boolean firstTest = false;
        BigInteger a = BigInteger.valueOf(2);
        if (a.pow(d.intValue()).mod(ranBig).equals(BigInteger.ONE))
            firstTest = true;
        if(!firstTest)
        for (int r = 0; r < s; r++) {
            if (a.pow(d.intValue()*r).mod(ranBig).equals(BigInteger.valueOf(-1)))
                firstTest=true;
        }

        boolean secondTest = false;
        a = BigInteger.valueOf(3);
        if (a.pow(d.intValue()).mod(ranBig).equals(BigInteger.ONE))
            secondTest = true;
        if(!secondTest)
        for (int r = 0; r < s; r++) {
            if (a.pow(d.intValue()*r).mod(ranBig).equals(BigInteger.valueOf(-1)))
                secondTest = true;
        }

        boolean thirdTest = false;
        a = BigInteger.valueOf(5);
        if (a.pow(d.intValue()).mod(ranBig).equals(BigInteger.ONE))
            thirdTest = true;
        if(!thirdTest)
        for (int r = 0; r < s; r++) {
            if (a.pow(d.intValue()*r).mod(ranBig).equals(BigInteger.valueOf(-1)))
                thirdTest = true;
        }

        if (firstTest && secondTest && thirdTest)
            temp = ranBig;
        else
            millerRabin(n);
    }

    public static BigInteger randomBig(BigInteger n ) {
        n = n.add(BigInteger.ONE).nextProbablePrime();
        return n;
    }

    public static BigInteger randNum() {
        BigInteger maxLimit = new BigInteger("500000");
        BigInteger minLimit = new BigInteger("250000");
        BigInteger bigInteger = maxLimit.subtract(minLimit);
        Random randNum = new Random();
        int len = maxLimit.bitLength();
        BigInteger res = new BigInteger(len, randNum);
        if (res.compareTo(minLimit) < 0)
            res = res.add(minLimit);
        if (res.compareTo(bigInteger) >= 0)
            res = res.mod(bigInteger).add(minLimit);
        return res;
    }
}