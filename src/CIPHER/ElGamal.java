package CIPHER;

import java.util.ArrayList;
import java.util.Arrays;

public class ElGamal {

    private final int minPrimeNumber = 255;
    private final int maxPrimeNumber = 5000;
    private final ArrayList<Integer> PrimeNumber = new ArrayList<>(); // List of Prime Number
    private final ArrayList<Integer> GNumber = new ArrayList<>();
    private boolean[] isPrime; // sieve
    private int p;
    private int g;
    private int x;
    private int y;
    private int a;
    private int b;
    private int m;
    private int k;

    public int getP() {
        return p;
    }

    public void setP(int value) {
        p = value;
    }

    public void generateP() {
        sieveOfEratosthenes(minPrimeNumber, maxPrimeNumber);
        p = PrimeNumber.get((int) (Math.random() * PrimeNumber.size()));
    }

    private void sieveOfEratosthenes(int m, int n) {
        sieveOfEratosthenes(n);
        Arrays.fill(isPrime, 0, m - 1, false);
        for (int i = m; i < n; i++) {
            if (isPrime[i]) {
                PrimeNumber.add(i);
            }
        }
    }

    private void sieveOfEratosthenes(int N) {
        isPrime = new boolean[N];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        for (int i = 2; i * i < N; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < N; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    private int modPower(int a, int z, int m) {
        int a1 = a;
        int z1 = z;
        int x = 1;
        while (z1 != 0) {
            while ((z1 % 2) == 0) {
                z1 = z1 / 2;
                a1 = (a1 * a1) % m;
            }
            z1 = z1 - 1;
            x = (x * a1) % m;
        }
        return x;
    } // x = a^z mod m

    public int funcEuler(int p) {
        return p - 1;
    }

    public void generateG() {
        boolean flag = true;
        int z = 1;
        int i;
        for (i = 2; (i < funcEuler(p)); i++) {
            while ((flag) && (z < funcEuler(p))) {
                if (modPower(i, z, p) == 1) {
                    flag = false;
                }
                z++;
            }
            if (flag) {
                GNumber.add(i);
            }
            flag = true;
            z = 1;
        }
        g = GNumber.get((int) (Math.random() * GNumber.size()));
    }

    public int getG() {
        return g;
    }

    public void setG(int value) {
        g = value;
    }

    public void generateX() {
        x = (int) (Math.random() * maxPrimeNumber);
    }

    public int getX() {
        return x;
    }

    public void setX(int value) {
        x = value;
    }

    public void calculateY() {
        y = modPower(g, x, p);
    }

    public int getY() {
        return y;
    }

    public int getM() {
        return m;
    }

    public void setM(int value) {
        m = value;
    }

    public void setK(int value) {
        k = value;
    }

    private int egcd(int a, int b) {
        if (a == 0)
            return b;

        while (b != 0) {
            if (a > b)
                a = a - b;
            else
                b = b - a;
        }

        return a;
    }

    public void generateK() {
        int number;
        do {
            number = (int) (Math.random() * PrimeNumber.indexOf(p));
        } while (egcd(number, p - 1) != 1);
        k = number;
    }

    public void calculateA() {
        a = modPower(g, k, p);
    }

    public int getA() {
        return a;
    }

    public void setA(int value) {
        a = value;
    }

    public void calculateB() {
        b = (modPower(y, k, p) * (m % p)) % p;
    }

    public int getB() {
        return b;
    }

    public void setB(int value) {
        b = value;
    }

    public void decoding() {
        m = ((b % p) * modPower(a, x * (funcEuler(p) - 1), p)) % p;
    }

    public void coding(String OriginalText) {
        char[] originalTextArr = OriginalText.toCharArray();
        for (char m : originalTextArr) {
            generateK();
            setM(m);
            calculateA();
            calculateB();
            System.out.print("(" + getA() + ", " + getB() + ") ");
        }
        System.out.println();
    }

}
