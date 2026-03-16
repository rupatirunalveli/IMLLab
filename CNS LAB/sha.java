import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class sha{

    public static String sha1(String input) {
        try {
            // Create SHA-1 MessageDigest instance
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // Perform the hash
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String text = "Hello World";
        String hash = sha1(text);

        System.out.println("Text: " + text);
        System.out.println("SHA-1 Digest: " + hash);
    }
}
