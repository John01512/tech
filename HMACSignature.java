import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
public class HMACSignature {
    public static void main(String[] args) {
        System.out.println("4741-Zafar Merchant");
        System.out.println("****HMAC Signatures****");

        try {
            // Secret key
            String secretKey = "MySecretKey123";
            System.out.println("SecretKey : MySecretKey123");
            // Message to be signed
            String message = "Hello, world!";
            System.out.println("Message : Hello, world");

            // Generate HMAC signature
            String hmacSignature = generateHMAC(message, secretKey);

            // Print HMAC signature
            System.out.println("HMAC Signature: " + hmacSignature);

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    // Function to generate HMAC signature
    public static String generateHMAC(String message, String secretKey)
            throws NoSuchAlgorithmException, InvalidKeyException {
        // Get HMAC-SHA256 algorithm instance
        Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
        // Initialize HMAC with secret key
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        hmacSHA256.init(secretKeySpec);
        // Compute HMAC signature
        byte[] hmacBytes = hmacSHA256.doFinal(message.getBytes());
        // Convert byte array to base64 string
        return Base64.getEncoder().encodeToString(hmacBytes);
    }
}