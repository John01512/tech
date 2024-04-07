import java.util.Scanner;

public class CiphersDemo {
    
    public static void main(String[] args) {
        System.out.println("4741-Zafar Merchant");
        System.out.println("****Ciphers****");
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter plaintext: ");
        String plaintext = scanner.nextLine().toUpperCase();
        
        // Affine Cipher
        int a = 5;
        int b = 8;
        String affineCipher = affineEncrypt(plaintext, a, b);
        System.out.println("Affine Cipher: " + affineCipher);
        
        // Rail Fence Cipher
        int rails = 3;
        String railFenceCipher = railFenceEncrypt(plaintext, rails);
        System.out.println("Rail Fence Cipher: " + railFenceCipher);
        
        // Simple Columnar Cipher
        String key = "231";
        String simpleColumnarCipher = simpleColumnarEncrypt(plaintext, key);
        System.out.println("Simple Columnar Cipher: " + simpleColumnarCipher);
        
        // Vermin Cipher
        int shift = 3;
        String verminCipher = verminEncrypt(plaintext, shift);
        System.out.println("Vermin Cipher: " + verminCipher);
        
        // Hill Cipher
        int[][] keyMatrix = {{6, 24, 1}, {13, 16, 10}, {20, 17, 15}};
        String hillCipher = hillEncrypt(plaintext, keyMatrix);
        System.out.println("Hill Cipher: " + hillCipher);
        
        scanner.close();
    }
    
    // Affine Cipher
    public static String affineEncrypt(String text, int a, int b) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                int x = (int) ch - 'A';
                x = (a * x + b) % 26;
                result.append((char) (x + 'A'));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
    
    // Rail Fence Cipher
    public static String railFenceEncrypt(String text, int rails) {
        StringBuilder result = new StringBuilder();
        char[][] grid = new char[rails][text.length()];
        int row = 0;
        boolean down = false;
        for (int i = 0; i < text.length(); i++) {
            grid[row][i] = text.charAt(i);
            if (row == 0 || row == rails - 1) {
                down = !down;
            }
            row += down ? 1 : -1;
        }
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < text.length(); j++) {
                if (grid[i][j] != 0) {
                    result.append(grid[i][j]);
                }
            }
        }
        return result.toString();
    }
    
    // Simple Columnar Cipher
    public static String simpleColumnarEncrypt(String text, String key) {
        int[] indexes = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            indexes[i] = key.indexOf(i + '1');
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            for (int j = indexes[i]; j < text.length(); j += key.length()) {
                result.append(text.charAt(j));
            }
        }
        return result.toString();
    }
    
    // Vermin Cipher
    public static String verminEncrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                int x = (int) ch + shift;
                if (Character.isUpperCase(ch) && x > 'Z') {
                    x -= 26;
                } else if (Character.isLowerCase(ch) && x > 'z') {
                    x -= 26;
                }
                result.append((char) x);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
    // Hill Cipher
    public static String hillEncrypt(String text, int[][] key) {
        StringBuilder result = new StringBuilder();
        int n = key.length;
        int[] vec = new int[n];
        for (int i = 0; i < text.length(); i += n) {
            for (int j = 0; j < n; j++) {
                vec[j] = (int) text.charAt(i + j) - 'A';
            }
            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += key[j][k] * vec[k];
                }
                result.append((char) ((sum % 26) + 'A'));
            }
        }
        return result.toString();
    }
}