import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class RSADigitalSignature {

    public static void main(String[] args) throws Exception {

        // 1️⃣ Generate RSA Key Pair (512 bits)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512);
        KeyPair pair = keyGen.generateKeyPair();

        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        System.out.println("----- PUBLIC KEY -----");
        System.out.println(Base64.getEncoder().encodeToString(publicKey.getEncoded()));

        System.out.println("\n----- PRIVATE KEY -----");   
        System.out.println(Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        // 2️⃣ Enter Message
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter message: ");
        String message = sc.nextLine();
        byte[] messageBytes = message.getBytes();

        // 3️⃣ Create Hash (SHA-256)
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(messageBytes);

        System.out.println("\n----- HASH VALUE (SHA-256) -----");
        System.out.println(bytesToHex(hash));

        // 4️⃣ Create Digital Signature
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privateKey);
        sign.update(messageBytes);
        byte[] signatureBytes = sign.sign();

        String signatureBase64 = Base64.getEncoder().encodeToString(signatureBytes);

        System.out.println("\n----- DIGITAL SIGNATURE -----");
        System.out.println(signatureBase64);

        // 5️⃣ Verification
        System.out.print("\nEnter message again for verification: ");
        String verifyMessage = sc.nextLine();
        byte[] verifyBytes = verifyMessage.getBytes();

        Signature verifySign = Signature.getInstance("SHA256withRSA");
        verifySign.initVerify(publicKey);
        verifySign.update(verifyBytes);

        boolean isVerified = verifySign.verify(signatureBytes);

        System.out.println("\n----- VERIFICATION RESULT -----");
        if (isVerified) {
            System.out.println("TRUE CASE ✅ Signature Verified");
        } else {
            System.out.println("FALSE CASE ❌ Verification Failed");
        }

        sc.close();
    }

    // Convert bytes to Hex
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}