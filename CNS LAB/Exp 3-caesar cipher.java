import java.util.Scanner;
public class Caesar {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter text:");
        String text = sc.nextLine();

        System.out.println("Enter shift:");
        int shift = sc.nextInt();

        String encrypted = "";

        // Encryption
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                encrypted += (char) ((c - base + shift) % 26 + base);
            } else {
                encrypted += c;
            }
        }

        System.out.println("Encrypted: " + encrypted);

        String decrypted = "";

        // Decryption
        for (char c : encrypted.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                decrypted += (char) ((c - base - shift + 26) % 26 + base);
            } else {
                decrypted += c;
            }
        }

        System.out.println("Decrypted: " + decrypted);
        sc.close();
    }
}