import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;
public class Des algorithm {
    public static String encrypt(String plaintext, SecretKey desKey, byte[] ivBytes) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.ENCRYPT_MODE, desKey, iv);
        byte[] ciphertext = cipher.doFinal(plaintext.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(ciphertext);
    }
    public static String decrypt(String base64Ciphertext, SecretKey desKey, byte[] ivBytes) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.DECRYPT_MODE, desKey, iv);
        byte[] plaintextBytes = cipher.doFinal(Base64.getDecoder().decode(base64Ciphertext));
        return new String(plaintextBytes, "UTF-8");
    }
    public static SecretKey generateDESKey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance("DES");
        kg.init(56);
        return kg.generateKey();
    }
    public static String toHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data)
            sb.append(String.format("%02X", b));
        return sb.toString();
    }
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter plaintext: ");
            String plaintext = sc.nextLine();
            SecretKey desKey = generateDESKey();
            byte[] ivBytes = new byte[8];
            SecureRandom sr = new SecureRandom();
            sr.nextBytes(ivBytes);
            String ciphertextB64 = encrypt(plaintext, desKey, ivBytes);
            String decrypted = decrypt(ciphertextB64, desKey, ivBytes);
            System.out.println("\n--- DES Encryption Result ---");
            System.out.println("Plaintext:        " + plaintext);
            System.out.println("Key (hex):        " + toHex(desKey.getEncoded()));
            System.out.println("IV  (hex):        " + toHex(ivBytes));
            System.out.println("Ciphertext (B64): " + ciphertextB64);
            System.out.println("Decrypted Text:   " + decrypted);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}