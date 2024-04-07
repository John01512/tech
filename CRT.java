import java.math.BigInteger;

public class CRT {

    public static BigInteger chineseRemainderTheorem(int[] residues, int[] moduli) {
        if (residues.length != moduli.length) {
            throw new IllegalArgumentException("Number of residues must equal number of moduli");
        }

        int n = residues.length;

        // Compute the product of all moduli
        BigInteger N = BigInteger.ONE;
        for (int i = 0; i < n; i++) {
            N = N.multiply(BigInteger.valueOf(moduli[i]));
        }

        // Compute the sum of the residues
        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < n; i++) {
            BigInteger Ni = N.divide(BigInteger.valueOf(moduli[i]));
            BigInteger Mi = Ni.modInverse(BigInteger.valueOf(moduli[i]));
            sum = sum.add(BigInteger.valueOf(residues[i]).multiply(Ni).multiply(Mi));
        }

        // Return the solution modulo the product of all moduli
        return sum.mod(N);
    }

    public static void main(String[] args) {
       
       int[] residues = {2, 3, 2};
        int[] moduli = {3, 5, 7};
        
        BigInteger solution = chineseRemainderTheorem(residues, moduli);
        System.out.println("Solution by Zafar Merchant - 4741: " + solution);
    }
}