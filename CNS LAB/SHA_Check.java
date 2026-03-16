import java.security.MessageDigest;
import java.util.Scanner;
public class SHA_Check {
    static String sha(String s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        
        byte[] h = md.digest(s.getBytes());
        String r = "";
        for (byte b : h) r += String.format("%02x", b);
        return r;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter original message: ");
        String m1 = sc.nextLine();
        String h1 = sha(m1);
        System.out.println("\nSHA Hash:");
        System.out.println(h1);
        System.out.println("\nOriginal verification: true");
        System.out.print("\nEnter message for verification: ");
        String m2 = sc.nextLine();
        String h2 = sha(m2);
        System.out.println("\nModified verification: " + h1.equals(h2));
        sc.close();
    }
}