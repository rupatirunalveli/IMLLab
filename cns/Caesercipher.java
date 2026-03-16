import java.util.Scanner;

public class Caesercipher {

    // Single method handles both encryption and decryption
    public static String caesar(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch))
                result.append((char) ((ch - 'A' + shift) % 26 + 'A'));
            else if (Character.isLowerCase(ch))
                result.append((char) ((ch - 'a' + shift) % 26 + 'a'));
            else
                result.append(ch);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the text: ");
        String text = sc.nextLine();
        System.out.print("Enter the shift value: ");
        int shift = sc.nextInt();

        String encrypted = caesar(text, shift);
        String decrypted = caesar(encrypted, 26 - (shift % 26));

        System.out.println("\nEncrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);
        sc.close();
    }
}