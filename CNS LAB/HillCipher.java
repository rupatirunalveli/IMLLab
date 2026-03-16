import java.util.Scanner;

public class HillCipher {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String text = sc.nextLine().toUpperCase();

        // Make length even
        if (text.length() % 2 != 0) {
            text += "X";
        }

        int[][] key = new int[2][2];

        System.out.println("Enter 2x2 key matrix:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key[i][j] = sc.nextInt();
            }
        }

        String cipher = "";

        // Encryption
        for (int i = 0; i < text.length(); i += 2) {

            int a = text.charAt(i) - 'A';
            int b = text.charAt(i + 1) - 'A';

            int c1 = (key[0][0] * a + key[0][1] * b) % 26;
            int c2 = (key[1][0] * a + key[1][1] * b) % 26;

            cipher += (char) (c1 + 'A');
            cipher += (char) (c2 + 'A');
        }

        System.out.println("Cipher text: " + cipher);
        sc.close();
    }
}