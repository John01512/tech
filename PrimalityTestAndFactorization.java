import java.util.Random;

public class PrimalityTestAndFactorization {

    public static void main(String[] args) {
        System.out.println("4741-Zafar Merchant");
        System.out.println("****Miller-Rabin & Pollard p-1****");

        long n = 561; // Example number for demonstration

        // Miller-Rabin Test
        int k = 5; // Number of iterations for accuracy
        boolean isPrime = millerRabinTest(n, k);
        System.out.println("Miller-Rabin Test Result:");
        System.out.println(n + " is " + (isPrime ? "probably prime." : "composite."));

        // Pollard p-1 Factorization
        long factor = pollardPMinus1Factorization(n);
        System.out.println("\nPollard p-1 Factorization Result:");
        System.out.println("One factor of " + n + " is: " + factor);
    }

    // Miller-Rabin Primality Test
    public static boolean millerRabinTest(long n, int k) {
        if (n <= 1 || n == 4)
            return false;
        if (n <= 3)
            return true;

        long d = n - 1;
        while (d % 2 == 0)
            d /= 2;

        for (int i = 0; i < k; i++) {
            long a = 2 + (long) (Math.random() % (n - 3));
            long x = modularExponentiation(a, d, n);
            if (x == 1 || x == n - 1)
                continue;

            boolean isProbablePrime = false;
            while (d != n - 1) {
                x = (x * x) % n;
                d *= 2;
                if (x == 1)
                    return false;
                if (x == n - 1) {
                    isProbablePrime = true;
                    break;
                }
            }
            if (!isProbablePrime)
                return false;
        }
        return true;
    }

    // Pollard p-1 Factorization Algorithm
    public static long pollardPMinus1Factorization(long n) {
        long x = 2;
        long y = 2;
        long d = 1;
        long c = 1;
        Random random = new Random();

        while (d == 1) {
            x = (modularExponentiation(x, c, n) + n) % n;
            y = (modularExponentiation(modularExponentiation(y, 2, n) + n, c, n) + n) % n;
            d = gcd(Math.abs(x - y), n);
            c++;
            if (random.nextInt(100) == 0) { // Occasionally reset to minimize loop size
                x = 2;
                y = 2;
                d = 1;
                c = 1;
            }
        }
        return d;
    }

    // Modular Exponentiation
    public static long modularExponentiation(long base, long exponent, long mod) {
        long result = 1;
        base = base % mod;
        while (exponent > 0) {
            if (exponent % 2 == 1)
                result = (result * base) % mod;
            exponent >>= 1;
            base = (base * base) % mod;
        }
        return result;
    }

    // Greatest Common Divisor
    public static long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}