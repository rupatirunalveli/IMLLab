import java.util.Scanner;
import java.math.BigInteger;
public class RSAAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first prime number (p): ");
        BigInteger p = sc.nextBigInteger();
        System.out.print("Enter second prime number (q): ");
        BigInteger q = sc.nextBigInteger();
        BigInteger n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        System.out.print("Enter public key e: ");
        BigInteger e = sc.nextBigInteger();
        BigInteger d = e.modInverse(phi);
        System.out.println("\nPublic Key (e, n): (" + e + ", " + n + ")");
        System.out.println("Private Key (d, n): (" + d + ", " + n + ")");
        System.out.print("\nEnter message (number form): ");
        BigInteger msg = sc.nextBigInteger();
        BigInteger cipher = msg.modPow(e, n);
        System.out.println("Encrypted Message: " + cipher);
        BigInteger decrypted = cipher.modPow(d, n);
        System.out.println("Decrypted Message: " + decrypted);
        sc.close();
    }
}